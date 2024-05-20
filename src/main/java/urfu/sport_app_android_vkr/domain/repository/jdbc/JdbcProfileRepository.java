package urfu.sport_app_android_vkr.domain.repository.jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public void add(long userId, long height, long weight, String city, String name, String surname) {
        jdbcTemplate.update("insert into profile (user_id, height, weight, city, name, surname)" +
                        " values (?,?,?,?,?,?)",
                userId, height, weight, city, name, surname);
    }


    @Override
    @Transactional
    public ProfileResponse getProfile(long user_id) {
        return jdbcTemplate.queryForObject(
                "select user_id, height, weight, city, name, surname from profile where user_id = ?",
                (rs, rowNum) -> createResponse(rs), user_id);
    }

    @Override
    @Transactional
    public void delete(Long user_id) {

    }

    @Override
    @Transactional
    public ProfileResponse editProfile(long userId, long height, long weight, String city, String name, String surname) {
        jdbcTemplate.update("update profile set height = ?, weight = ?, city = ?, name = ?, surname = ? where user_id = ?",
                height, weight, city, name, surname, userId);
        return getProfile(userId);
    }

    private ProfileResponse createResponse(ResultSet resultSet) throws SQLException {
        return new ProfileResponse(
                resultSet.getLong("user_id"),
                resultSet.getLong("height"),
                resultSet.getLong("weight"),
                resultSet.getString("city"),
                resultSet.getString("name"),
                resultSet.getString("surname")
        );
    }
}
