package vaccination.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import vaccination.model.City;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CityRepository {

    private JdbcTemplate jdbcTemplate;

    public CityRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<City> listCitiesByZipCode(String zipCode) {
        return jdbcTemplate.query("SELECT * FROM Cities WHERE zip = ?",
                (resultSet, rowNum) -> getCity(resultSet), zipCode);
    }

    private City getCity(ResultSet resultSet) throws SQLException {
        return new City(
                resultSet.getLong("id"),
                resultSet.getString("city"),
                resultSet.getString("district"),
                resultSet.getString("zip")
        );
    }
}
