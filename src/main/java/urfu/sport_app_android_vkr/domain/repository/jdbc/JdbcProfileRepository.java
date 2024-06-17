package urfu.sport_app_android_vkr.domain.repository.jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import urfu.sport_app_android_vkr.domain.dto.request.ProfileRequest;
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
    public Long add(ProfileRequest request, long userId) {
        jdbcTemplate.update("insert into profile (user_id, height, weight, city, name, surname, sex, image_path)" +
                        " values (?,?,?,?,?,?,?,?)",
                userId, request.height(), request.weight(),
                request.city(), request.name(), request.surname(), request.sex(), null);
        return userId;
    }


    @Override
    @Transactional
    public ProfileResponse getProfile(long userId) {
        return jdbcTemplate.queryForObject(
                "select user_id, height, weight, city, name, surname, sex, image_path from profile where user_id = ?",
                (rs, rowNum) -> createResponse(rs), userId);
    }

    @Override
    @Transactional
    public void delete(Long user_id) {

    }

    @Override
    @Transactional
    public ProfileResponse editProfile(ProfileRequest request, long userId) {
        jdbcTemplate.update("update profile set height = ?, weight = ?, " +
                        "city = ?, name = ?, surname = ?, sex = ? where user_id = ?",
                request.height(), request.weight(), request.city(),
                request.name(), request.surname(), request.sex(), userId);
        return getProfile(userId);
    }

    private ProfileResponse createResponse(ResultSet resultSet) throws SQLException {
        return new ProfileResponse(
                resultSet.getLong("height"),
                resultSet.getLong("weight"),
                resultSet.getString("city"),
                resultSet.getString("name"),
                resultSet.getString("surname"),
                resultSet.getString("sex"),
                resultSet.getString("image_path")
        );
    }
}
