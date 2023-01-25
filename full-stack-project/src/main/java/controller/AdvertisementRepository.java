package controller;

import model.Advertisement;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;
import java.util.List;

public class AdvertisementRepository {

    private JdbcTemplate jdbcTemplate;

    public AdvertisementRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Advertisement> listAdvertisementsByUser(long userId) {
        return jdbcTemplate.query("select * from advertisements where user_id = ?",
                ((rs, rowNum) -> new Advertisement(rs.getLong("id"),
                        rs.getLong("user_id"), rs.getString("title"))), userId);
    }
}
