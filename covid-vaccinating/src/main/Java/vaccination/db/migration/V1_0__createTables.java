package vaccination.db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class V1_0__createTables extends BaseJavaMigration {
    public void migrate(Context context) {
        try (
                Statement citiesCreate = context.getConnection().createStatement();
                Statement citizensCreate = context.getConnection().createStatement();
                Statement vaccinationCreate = context.getConnection().createStatement();
        ) {
            createAndExecuteTables(citiesCreate, citizensCreate, vaccinationCreate);
        } catch (SQLException se) {
            throw new IllegalStateException("Can not create tables.", se);
        }
        processZipCodes(context);
    }

    private void createAndExecuteTables(Statement citiesCreate, Statement citizensCreate, Statement vaccinationCreate) throws SQLException {
        citiesCreate.execute("""
                CREATE TABLE Cities (
                    id BIGINT NOT NULL AUTO_INCREMENT,
                    zip VARCHAR(4) NOT NULL, 
                    city VARCHAR(40) NOT NULL, 
                    district VARCHAR(40), 
                    CONSTRAINT PRIMARY KEY(id)
                );""");
        citizensCreate.execute("""
                CREATE TABLE Citizens (
                    id BIGINT NOT NULL AUTO_INCREMENT, 
                    citizen_name VARCHAR(200) NOT NULL, 
                    city_id BIGINT NOT NULL, 
                    age BIGINT NOT NULL, 
                    email VARCHAR(200), 
                    ssn VARCHAR(10),
                    CONSTRAINT PRIMARY KEY(id), 
                    CONSTRAINT FOREIGN KEY(city_id) REFERENCES Cities(id)
                );""");
        vaccinationCreate.execute("""
                CREATE TABLE Vaccinations (
                    id BIGINT NOT NULL AUTO_INCREMENT,
                    citizen_id BIGINT NOT NULL,
                    vaccination_date DATE,
                    vaccine_type VARCHAR(20),
                    status VARCHAR(50) NOT NULL,
                    note VARCHAR(200),
                    CONSTRAINT PRIMARY KEY(id),
                    CONSTRAINT FOREIGN KEY(citizen_id) REFERENCES Citizens(id)
                );""");
    }

    private void processZipCodes(Context context) {
        try (
                Scanner scan = new Scanner(Path.of("src/main/resources/zipCodes.csv"));
                PreparedStatement ps = context.getConnection().prepareStatement("INSERT INTO Cities (zip, city, district) VALUES (?, ?, ?)")
        ) {
            scan.nextLine();
            while (scan.hasNext()) {
                processLines(scan, ps);
            }
        } catch(IOException | SQLException e) {
            throw new IllegalStateException("File or database access error", e);
        }
    }

    private void processLines(Scanner scan, PreparedStatement ps) throws SQLException {
        String[] lineParts = scan.nextLine().split(";");
        ps.setString(1, lineParts[0]);
        ps.setString(2, lineParts[1]);
        if (lineParts.length == 2) {
            ps.setString(3, null);
        } else {
            ps.setString(3, lineParts[2]);
        }
        ps.executeUpdate();
    }
}