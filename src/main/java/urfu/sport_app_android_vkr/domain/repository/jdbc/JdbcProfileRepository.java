package urfu.sport_app_android_vkr.domain.repository.jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import urfu.sport_app_android_vkr.domain.dto.response.ProfileResponse;
import urfu.sport_app_android_vkr.domain.repository.ProfileRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcProfileRepository implements ProfileRepository {
    private final static Logger LOGGER = LogManager.getLogger();
    private final JdbcTemplate jdbcTemplate;

    public JdbcProfileRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void add(long userId, long height, long weight, String city) {
        jdbcTemplate.update("insert into profile (user_id, height, weight, city) values (?,?,?,?)",
                userId, height, weight, city);
    }

    @Override
    public ProfileResponse getProfile(long user_id) {
        return jdbcTemplate.queryForObject(
                "select user_id, height, weight, city from profile where user_id = ?",
                ((rs, rowNum) -> createResponse(rs)), user_id);
    }

    private ProfileResponse createResponse(ResultSet resultSet) throws SQLException {
        return new ProfileResponse(
                resultSet.getLong("user_id"),
                resultSet.getLong("height"),
                resultSet.getLong("weight"),
                resultSet.getString("city")
        );
    }
}
