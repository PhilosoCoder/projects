package vaccination;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class CityRepository {

    private JdbcTemplate jdbcTemplate;

    public CityRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<City> listCitiesByZipCode(String zipCode) {
        return jdbcTemplate.query("SELECT * FROM Cities WHERE zip = ?",
                (resultSet, rowNum) -> new City(
                        resultSet.getLong("id"),
                        resultSet.getString("city"),
                        resultSet.getString("district"),
                        resultSet.getString("zip")
                ), zipCode);
    }
}
