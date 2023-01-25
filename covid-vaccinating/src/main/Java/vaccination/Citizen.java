package vaccination;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Citizen {

    private final Long id;

    private final String name;

    private final City city;

    private final int age;

    private final String email;

    private final String ssn;

    private List<Vaccination> vaccinations;

    public Citizen(Long id, String name, City city, int age, String email, String ssn) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.age = age;
        this.email = email;
        this.ssn = ssn;
        this.vaccinations = new ArrayList<>();
    }

    public Citizen(Long id, String name, City city, int age, String email, String ssn, List<Vaccination> vaccinations) {
        this(id, name, city, age, email, ssn);
        this.vaccinations = vaccinations;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public City getCity() {
        return city;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getSsn() {
        return ssn;
    }

    public List<Vaccination> getVaccinations() {
        return new ArrayList<>(vaccinations);
    }

    public Vaccination getLastVaccination() {
        return vaccinations.get(vaccinations.size() - 1);
    }

    public boolean canBeVaccinated(LocalDate date) {
        Vaccination last = getLastVaccination();
        if (last.getStatus().equals(Status.CANCELLED)) {
            return false;
        }
        if (last.getStatus().equals(Status.ADMINISTRATED)) {
            return false;
        }
        if (last.getStatus().equals(Status.PREPARED)) {
            return last.getDate().equals(date);
        }
        return true;
    }
}
