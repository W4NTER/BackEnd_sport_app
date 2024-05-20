package urfu.sport_app_android_vkr.domain.repository.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import urfu.sport_app_android_vkr.controllers.dto.UserResponse;
import urfu.sport_app_android_vkr.domain.repository.UsersRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcUsersRepository implements UsersRepository {

    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder encoder;

    public JdbcUsersRepository(
            JdbcTemplate jdbcTemplate,
            PasswordEncoder encoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.encoder = encoder;
    }

    @Override
    @Transactional
    public void addUser(String username, String password) {
        jdbcTemplate.update("insert into users (username, password, enabled) values (?, ?, ?)",
                username, encoder.encode(password), true);
    }

    @Override
    @Transactional
    public void editPassword(Long userId, String password) {
        jdbcTemplate.update("update users set password = ? where user_id = ?",
                password, userId);
    }

    @Override
    @Transactional
    public UserResponse getUser(String username) {
        return jdbcTemplate.queryForObject(
                "select user_id, username, password, enabled from users where username = ?",
                (rs, rowNum) -> setUserResponse(rs), username
        );
    }

    @Override
    public UserResponse getUser(Long id) {
        return jdbcTemplate.queryForObject(
                "select user_id, username, password, enabled from users where username = ?",
                (rs, rowNum) -> setUserResponse(rs), id
        );
    }

    @Override
    @Transactional
    public void remove() {

    }

    @Override
    @Transactional
    public List<UserResponse> findAll() {
        return jdbcTemplate.query(
                "select user_id, username, password, enabled from users",
        (rs, rowNum) -> setUserResponse(rs));
    }

    private UserResponse setUserResponse(ResultSet rowSet) throws SQLException {
        return new UserResponse(
                rowSet.getLong("user_id"),
                rowSet.getString("username"),
                rowSet.getString("password"),
                rowSet.getString("enabled")
        );
    }
}
