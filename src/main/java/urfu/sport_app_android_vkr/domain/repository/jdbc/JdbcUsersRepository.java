package urfu.sport_app_android_vkr.domain.repository.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import urfu.sport_app_android_vkr.domain.repository.UsersRepository;
import urfu.sport_app_android_vkr.controllers.dto.UserResponse;

import javax.sql.RowSet;
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
    public UserResponse getUser(String username) {
        return jdbcTemplate.queryForObject(
                "select username, password, enabled, user_id from users where username = ?",
                (rs, rowNum) -> setUserResponse(rs)
        );
    }

    @Override
    public UserResponse getUser(Long id) {
        return jdbcTemplate.queryForObject(
                "select username, password, enabled, user_id from users where user_id = ?",
                (rs, rowNum) -> setUserResponse(rs)
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
