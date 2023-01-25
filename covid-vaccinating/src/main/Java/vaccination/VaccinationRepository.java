package vaccination;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.List;

public class VaccinationRepository {

    JdbcTemplate jdbcTemplate;

    public VaccinationRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void saveVaccination(Vaccination vaccination) {
        jdbcTemplate.update("INSERT INTO Vaccinations (citizen_id, vaccination_date, status) VALUES (?, ?, ?)",
                vaccination.getCitizenId(),
                vaccination.getDate(),
                vaccination.getStatus().toString());
    }

    public List<Vaccination> listVaccinationsByCitizen(long citizenId) {
        return jdbcTemplate.query("SELECT * FROM Vaccinations WHERE citizen_id = ?",
                (resultSet, rowNum) -> new Vaccination(
                        resultSet.getLong("id"),
                        resultSet.getLong("citizen_id"),
                        resultSet.getDate("vaccination_date") == null ? null : resultSet.getDate("vaccination_date").toLocalDate(),
                        resultSet.getString("vaccine_type") == null ? null : VaccineType.valueOf(resultSet.getString("vaccine_type")),
                        Status.valueOf(resultSet.getString("status")),
                        resultSet.getString("note")
                ), citizenId);
    }

    public void updateVaccination(Vaccination vaccination) {
        jdbcTemplate.update("UPDATE Vaccinations SET vaccination_date = ?, vaccine_type = ?, status = ?, note = ? WHERE id = ?;",
                vaccination.getDate() == null ? null : Date.valueOf(vaccination.getDate()),
                vaccination.getType() == null ? null : vaccination.getType().toString(),
                vaccination.getStatus().toString(),
                vaccination.getNote(),
                vaccination.getId());
    }
}
