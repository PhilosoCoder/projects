package vaccination.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import vaccination.model.Citizen;
import vaccination.model.City;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Objects;

public class CitizenRepository {

    private JdbcTemplate jdbcTemplate;

    public CitizenRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public long saveNewCitizen(Citizen citizen) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Citizens (citizen_name, city_id, age, email, ssn) VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            processCitizen(citizen, ps);
            return ps;
        }, holder);
        return Objects.requireNonNull(holder.getKey()).longValue();
    }

    public List<Citizen> listCitizensByCity(City city, VaccinationRepository vr) {
        return jdbcTemplate.query("SELECT * FROM Citizens INNER JOIN Cities ON Cities.id = Citizens.city_id WHERE city_id = ?;",
                (resultSet, rowNum) ->
                        getCitizen(vr, resultSet)
                , city.getId());
    }

    public Citizen findCitizenBySsn(String ssn, VaccinationRepository vr) {
        return jdbcTemplate.query("SELECT * FROM Citizens INNER JOIN Cities ON Citizens.city_id = Cities.id WHERE ssn = ?",
                (resultSet, rowNum) ->
                        getCitizen(vr, resultSet),
                ssn).get(0);
    }

    private void processCitizen(Citizen citizen, PreparedStatement ps) throws SQLException {
        ps.setString(1, citizen.getName());
        ps.setLong(2, citizen.getCity().getId());
        ps.setInt(3, citizen.getAge());
        ps.setString(4, citizen.getEmail());
        ps.setString(5, citizen.getSsn());
    }

    private Citizen getCitizen(VaccinationRepository vr, ResultSet resultSet) throws SQLException {
        return new Citizen(
                resultSet.getLong(1),
                resultSet.getString("citizen_name"),
                createCity(resultSet),
                resultSet.getInt("age"),
                resultSet.getString("email"),
                resultSet.getString("SSN"),
                vr.listVaccinationsByCitizen(resultSet.getLong(1))
        );
    }

    private City createCity(ResultSet resultSet) throws SQLException {
        return new City(
                resultSet.getLong("city_id"),
                resultSet.getString("city"),
                resultSet.getString("district"),
                resultSet.getString("zip")
        );
    }
}