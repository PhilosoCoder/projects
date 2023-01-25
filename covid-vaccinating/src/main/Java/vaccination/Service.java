package vaccination;

import org.flywaydb.core.Flyway;
import org.mariadb.jdbc.MariaDbDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class Service {

    private final CitizenRepository citizenRepository;

    private final CityRepository cityRepository;

    private final VaccinationRepository vaccinationRepository;

    public Service() {
        DataSource dataSource = createDataSource();
        Flyway flyway = Flyway.configure().cleanDisabled(false).locations("vaccination/db/migration").dataSource(dataSource).load();
        flyway.clean();
        flyway.migrate();
        this.citizenRepository = new CitizenRepository(dataSource);
        this.cityRepository = new CityRepository(dataSource);
        this.vaccinationRepository = new VaccinationRepository(dataSource);
    }

    public List<City> getListOfCitiesByZipCode(String zipCode) {
        if (zipCode.isBlank()) {
            throw new IllegalArgumentException("ZIP code can not be empty or blank.");
        }
        return cityRepository.listCitiesByZipCode(zipCode);
    }

    public void registration(String name, City city, int age, String email, String ssn) {
        long citizenId = citizenRepository.saveNewCitizen(new Citizen(null, name, city, age, email, ssn));
        vaccinationRepository.saveVaccination(new Vaccination(citizenId, null, Status.PLANNED));
    }

    public void massRegistration(Path path) {
        try (Scanner scan = new Scanner(path)) {
            scan.nextLine();
            while (scan.hasNext()) {
                String[] citizenData = scan.nextLine().split(";");
                registration(citizenData[0], cityRepository.listCitiesByZipCode(citizenData[1]).get(0), Integer.parseInt(citizenData[2]), citizenData[3], citizenData[4]);
            }
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Unreadable file or wrong path: " + path, ioe);
        }
    }

    public void generateVaccinationOrder(Path path, City city, LocalDate date) {
        LocalTime time = LocalTime.of(8, 0);
        List<String> toFile = new ArrayList<>(List.of("Időpont(" + date + ");Név;Irányítószám;Életkor;E-mail cím;TAJ"));
        List<Citizen> citizens = createCitizensDataList(city, date);

        updateVaccinations(citizens, date);
        citizens.stream()
                .map(c -> String.join(";", c.getName(), c.getCity().getZipCode(), String.valueOf(c.getAge()), c.getEmail(), c.getSsn()))
                .collect(Collectors.toCollection(() -> toFile));

        for (int i = 1; i < toFile.size(); i++) {
            toFile.set(i, String.format("%02d:%02d;%s", time.getHour(), time.getMinute(), toFile.get(i)));
            time = time.plusMinutes(30L);
        }

        try {
            Files.write(path, toFile);
        } catch (IOException ioe) {
            throw new IllegalStateException("Can not create file: " + path, ioe);
        }
    }

    public void vaccination(Citizen citizen, LocalDate date, VaccineType type) {
        Vaccination updating = citizen.getLastVaccination();
        if (citizen.getVaccinations().size() == 1) {
            updating.setStatus(Status.ADMINISTRATED);
            updating.setType(type);
            vaccinationRepository.updateVaccination(updating);
            vaccinationRepository.saveVaccination(new Vaccination(citizen.getId(), date.plusDays(16L), Status.PREPARED));
        } else {
            updating.setStatus(Status.ADMINISTRATED);
            vaccinationRepository.updateVaccination(updating);
        }
    }

    public Citizen findCitizenBySsn(String ssn) {
        return citizenRepository.findCitizenBySsn(ssn, vaccinationRepository);
    }

    public void cancellation(Citizen citizen, String note) {
        Vaccination updating = citizen.getLastVaccination();
        updating.setStatus(Status.CANCELLED);
        updating.setNote(note);
        vaccinationRepository.updateVaccination(updating);
    }

    public Map<Integer, Long> report(City city) {
        return citizenRepository.listCitizensByCity(city, vaccinationRepository)
                .stream()
                .map(c -> {
                    Status lastStatus = c.getLastVaccination().getStatus();
                    if (c.getVaccinations().size() == 1) {
                        return 1;
                    }
                    if (c.getVaccinations().size() == 2 && (lastStatus.equals(Status.PREPARED) || lastStatus.equals(Status.CANCELLED))) {
                        return 2;
                    }
                    return 3;
                })
                .collect(Collectors.groupingBy(k -> k, Collectors.counting())
                );
    }

    private DataSource createDataSource() {
        MariaDbDataSource dataSource = new MariaDbDataSource();
        try {
            dataSource.setUrl("jdbc:mariadb://localhost:3306/vaccination?useUnicode=true");
            dataSource.setUser("root");
            dataSource.setPassword("root");
        } catch (SQLException se) {
            throw new IllegalStateException("Can not reach database.", se);
        }
        return dataSource;
    }

    private List<Citizen> createCitizensDataList(City city, LocalDate date) {

        return citizenRepository.listCitizensByCity(city, vaccinationRepository)
                .stream()
                .filter(c -> c.canBeVaccinated(date))
                .sorted(Comparator.comparing(Citizen::getAge, Comparator.reverseOrder())
                        .thenComparing(Citizen::getName))
                .limit(16L)
                .toList();
    }

    private void updateVaccinations(List<Citizen> citizens, LocalDate date) {
        citizens.stream()
                .filter(c -> c.getLastVaccination().getStatus().equals(Status.PLANNED))
                .forEach(c -> {
                    Vaccination last = c.getLastVaccination();
                    last.setDate(date);
                    last.setStatus(Status.PREPARED);
                    vaccinationRepository.updateVaccination(last);
                });
    }

    public CitizenRepository getCitizenRepository() {
        return citizenRepository;
    }

    public CityRepository getCityRepository() {
        return cityRepository;
    }

    public VaccinationRepository getVaccinationRepository() {
        return vaccinationRepository;
    }
}
