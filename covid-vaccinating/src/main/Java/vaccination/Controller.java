package vaccination;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Controller {

    private final Scanner scan = new Scanner(System.in);

    private final Service service = new Service();

    public static void main(String[] args) {
        new Controller().run();
    }

    private void run() {
        menu();
    }

    private void menu() {
        printHeader();
        boolean loopCondition = true;
        while (loopCondition) {
            printMenu();
            switch (Integer.parseInt(scan.nextLine())) {
                case 1 -> loopCondition = registration();
                case 2 -> loopCondition = massRegistration();
                case 3 -> loopCondition = generateOrder();
                case 4 -> loopCondition = vaccinate();
                case 5 -> loopCondition = cancellation();
                case 6 -> loopCondition = printReport();
                case 7 -> {         //kilépés
                    loopCondition = false;
                    System.out.println("Viszontlátásra!");
                }
                default -> System.out.println("Nincs ilyen opció. Próbálja meg újra!");
            }
        }
    }

    private void printHeader() {
        System.out.print("COVID-19 oltáskezelő program\n----------------------------");
    }

    private void printMenu() {
        System.out.print("""           
                Kérem válasszon az alábbi menüpontok közül:
                    1. Regisztráció
                    2. Tömeges regisztráció
                    3. Sorrend generálása
                    4. Oltás
                    5. Oltás meghiúsulás
                    6. Riport
                    7. Kilépés
                                Válasz:\040""");
    }

    private boolean registration() {
        System.out.print("Adja meg a következő adatokat:\n\tNév: ");
        String name = scan.nextLine();
        checkName(name);

        System.out.print("\tIrányítószám: ");
        City city = selectCityName(service.getListOfCitiesByZipCode(scan.nextLine()));

        System.out.print("\tÉletkor: ");
        int age = Integer.parseInt(scan.nextLine());
        checkAge(age);

        System.out.print("\tE-mail cím: ");
        String email = scan.nextLine();
        checkEmail(email);

        System.out.print("\tTAJ: ");
        String ssn = scan.nextLine();
        checkSsn(ssn);

        service.registration(name, city, age, email, ssn);
        System.out.println("Sikeres regisztráció!");
        return sideMenu();
    }

    private boolean massRegistration() {
        System.out.print("Adja meg a lista elérési útját: ");
        Path path = Path.of(scan.nextLine());
        if (isCorrectPath(path)) {
            service.massRegistration(path);
            System.out.println("Sikeres regisztráció!");
        }
        return sideMenu();
    }

    private boolean generateOrder() {
        System.out.print("Adja meg a következő adatokat!\n\tIrányítószám: ");
        City city = selectCityName(service.getListOfCitiesByZipCode(scan.nextLine()));

        System.out.print("\tDátum(ÉÉÉÉ-HH-NN): ");
        LocalDate date = LocalDate.parse(scan.nextLine());

        System.out.print("Adja meg a generálandó fájl elérési útját: ");
        Path path = avoidOverwriting(Path.of(scan.nextLine()));

        service.generateVaccinationOrder(path, city, date);
        System.out.println("Sikeres generálás: " + path);
        return sideMenu();
    }

    private boolean vaccinate() {
        System.out.print("Adja meg a TAJ számot: ");
        String ssn = scan.nextLine();
        checkSsn(ssn);
        Citizen citizen = service.findCitizenBySsn(ssn);
        if (printLastVaccination(citizen)) {
            System.out.print("Adja meg az új oltás dátumát(ÉÉÉÉ-HH-NN): ");
            LocalDate date = LocalDate.parse(scan.nextLine());
            if (citizen.getLastVaccination().getDate().equals(date)) {
                VaccineType newType = getNewType(citizen);
                service.vaccination(citizen, date, newType);
                System.out.println("Az oltás rögzítésre került.");
            } else {
                System.out.print("A(z) " + ssn + " TAJ számú páciens nem oltható a megadott napon!");
            }
        } else {
            System.out.print("A(z) " + ssn + " TAJ számú páciens nem oltható: nincs időpontja, lemondta, vagy megkapta az oltásait!");
        }
        return sideMenu();
    }

    private boolean cancellation() {
        System.out.print("Adja meg a TAJ számot: ");
        String ssn = scan.nextLine();
        checkSsn(ssn);

        System.out.print("Megjegyzés a lemondáshoz: ");
        String note = scan.nextLine();
        service.cancellation(service.findCitizenBySsn(ssn), note);
        System.out.println("Sikeres lemondás!");
        return sideMenu();
    }

    private boolean printReport() {
        System.out.print("Adja meg az irányítószámot: ");
        City city = selectCityName(service.getListOfCitiesByZipCode(scan.nextLine()));
        Map<Integer, Long> report = service.report(city);
        Long unvaccinated = report.get(1),
                oneDose = report.get(2),
                twoDose = report.get(3);
        System.out.println("\tOltatlan: " + (unvaccinated == null ? 0 : unvaccinated) + " fő\n" +
                "\tEgy dózist kapott " + (oneDose == null ? 0 : oneDose) + " fő\n" +
                "\tKét dózist kapott " + (twoDose == null ? 0 : twoDose) + " fő\n" +
                "\t--------------------------\n" +
                "\tÖsszes regisztrált: " + ((unvaccinated == null ? 0 : unvaccinated) + (oneDose == null ? 0 : oneDose) + (twoDose == null ? 0 : twoDose)) + " fő");
        return sideMenu();
    }

    private boolean sideMenu() {
        System.out.print("""      
                Mit szeretne tenni?
                   1.Vissza a menübe
                   2.Kilépés
                               Válasz:\040""");
        int menuItem = Integer.parseInt(scan.nextLine());
        if (menuItem == 1) {
            return true;
        } else if (menuItem == 2) {
            System.out.println("Viszontlátásra!");
            return false;
        } else {
            System.out.println("Nincs ilyen opció. Próbálja meg újra!");
            return sideMenu();
        }
    }

    private City selectCityName(List<City> cities) {
        if (cities.isEmpty()) {
            throw new IllegalArgumentException("No such ZIP code.");
        }
        System.out.println("Válasszon települést!");

        for (int i = 0; i < cities.size(); i++) {
            City actual = cities.get(i);
            System.out.print("\t" + (i + 1) + ". " + actual.getName() + (actual.getDistrict() == null ? "" : ":" + actual.getDistrict()));
        }
        System.out.print("\n\t\tVálasz: ");

        try {
            return cities.get(Integer.parseInt(scan.nextLine()) - 1);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("Nincs ilyen opció.");
            throw new IllegalArgumentException("No such option.", e);
        }
    }

    private boolean isCorrectPath(Path path) {
        if (!Files.exists(path)) {
            System.out.println("Sikertelen regisztráció: a megadott fájl nem létezik!");
            return false;
        }
        return true;
    }

    private VaccineType selectVaccineType() {
        while (true) {
            System.out.print("""
                    Válasszon oltóanyagot:
                        1. Pfizer-BioNTech
                        2. Moderna
                        3. AstraZeneca
                        4. Gamaleya-SputnikV
                        5. Sinopharm
                                \tVálasz:\040""");
            int answer = Integer.parseInt(scan.nextLine());
            switch (answer) {
                case 1 -> {
                    return VaccineType.PFIZER_BIONTECH;
                }
                case 2 -> {
                    return VaccineType.MODERNA;
                }
                case 3 -> {
                    return VaccineType.ASTRAZENECA;
                }
                case 4 -> {
                    return VaccineType.GAMALEYA_SPUTNIK_V;
                }
                case 5 -> {
                    return VaccineType.SINOPHARM;
                }
                default -> System.out.println("\nNincs ilyen opció.");
            }
        }
    }

    private Path avoidOverwriting(Path path) {
        while (Files.exists(path)) {
            System.out.print("FIGYELEM! A megadott fájl már létezik. Mit kíván tenni?\n\t 1. Felülírás\t2. Új útvonal megadása\n\t\tVálasz: ");
            String answer = scan.nextLine();
            if (answer.equals("1")) {
                break;
            } else if (answer.equals("2")) {
                System.out.print("\tÚt: ");
                path = Path.of(scan.nextLine());
            } else {
                System.out.println("Nincs ilyen opció!");
            }
        }
        return path;
    }

    //itt jártam!!!!!!
    private boolean printLastVaccination(Citizen citizen) {
        Vaccination last = citizen.getLastVaccination();
        if (!last.getStatus().equals(Status.CANCELLED) || !last.getStatus().equals(Status.ADMINISTRATED) || !last.getStatus().equals(Status.PLANNED)) {
            System.out.print("\tUtolsó oltás: ");
            if (citizen.getVaccinations().size() == 1) {
                System.out.println("(oltatlan)");
            } else {
                System.out.println("\n\t\tTípusa: " + citizen.getVaccinations().get(0).getType().toString() +
                        "\n\t\tDátum: " + citizen.getVaccinations().get(0).getDate());
            }
            return true;
        }
        return false;
    }

    private VaccineType getNewType(Citizen citizen) {
        if (citizen.getVaccinations().size() == 1) {
            return selectVaccineType();
        } else {
            return citizen.getVaccinations().get(0).getType();
        }
    }

    private void checkName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name can not be null, empty or blank.");
        }
    }

    private void checkAge(int age) {
        if (age < 10 || age > 150) {
            throw new IllegalArgumentException("Age can not less than 10 years or more than 150 years.");
        }
    }

    private void checkEmail(String email) {
        if (email == null || email.length() < 3 || !email.contains("@")) {
            throw new IllegalArgumentException("Email address can not be null and it must contain '@' and at least 3 character.");
        }
    }

    private void checkSsn(String ssn) {
        if (!isCorrectSsn(ssn)) {
            throw new IllegalArgumentException("Incorrect SSN: " + ssn);
        }
    }

    private boolean isCorrectSsn(String ssn) {
        if (ssn != null && ssn.length() == 9) {
            int cdv = Integer.parseInt(Character.toString(ssn.charAt(8))),
                    odds = 0,
                    evens = 0;
            for (int i = 0; i < 8; i++) {
                if (!Character.isDigit(ssn.charAt(i))) {
                    return false;
                }
                int digitValue = Integer.parseInt(Character.toString(ssn.charAt(i)));
                if (i % 2 != 0) {
                    evens += digitValue * 7;
                } else {
                    odds += digitValue * 3;
                }
            }
            return (evens + odds) % 10 == cdv;
        }
        return false;
    }
}
