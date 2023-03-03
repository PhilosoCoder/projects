package controller;

import model.User;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class UserRepository {

    private JdbcTemplate jdbcTemplate;

    public UserRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<User> listUsers() {
        return jdbcTemplate.query(
                "select * " +
                        "from users",
                (rs, rowNum) ->
                        new User(
                                rs.getLong("id"),
                                rs.getString("name")));
    }
}