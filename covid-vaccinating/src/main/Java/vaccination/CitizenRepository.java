package vaccination;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

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
            ps.setString(1, citizen.getName());
            ps.setLong(2, citizen.getCity().getId());
            ps.setInt(3, citizen.getAge());
            ps.setString(4, citizen.getEmail());
            ps.setString(5, citizen.getSsn());
            return ps;
        }, holder);
        return holder.getKey().longValue();
    }

    public List<Citizen> listCitizensByCity(City city, VaccinationRepository vr) {
        return jdbcTemplate.query("SELECT * FROM Citizens INNER JOIN Cities ON Cities.id = Citizens.city_id WHERE city_id = ?;",
                (resultSet, rowNum) ->
                        new Citizen(
                                resultSet.getLong(1),
                                resultSet.getString("citizen_name"),
                                createCity(resultSet),
                                resultSet.getInt("age"),
                                resultSet.getString("email"),
                                resultSet.getString("SSN"),
                                vr.listVaccinationsByCitizen(resultSet.getLong(1))
                        )
                , city.getId());
    }

    public Citizen findCitizenBySsn(String ssn, VaccinationRepository vr) {
        return jdbcTemplate.query("SELECT * FROM Citizens INNER JOIN Cities ON Citizens.city_id = Cities.id WHERE ssn = ?",
                (resultSet, rowNum) ->
                        new Citizen(
                                resultSet.getLong(1),
                                resultSet.getString("citizen_name"),
                                createCity(resultSet),
                                resultSet.getInt("age"),
                                resultSet.getString("email"),
                                resultSet.getString("SSN"),
                                vr.listVaccinationsByCitizen(resultSet.getLong(1))
                        ), ssn).get(0);
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
