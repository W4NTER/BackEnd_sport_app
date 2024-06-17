package urfu.sport_app_android_vkr.domain.repository.jdbc;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import urfu.sport_app_android_vkr.domain.repository.AuthoritiesRepository;

@Repository
public class JdbcAuthorityRepository implements AuthoritiesRepository {
    final JdbcTemplate jdbcTemplate;

    public JdbcAuthorityRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addAuthority(String username, String authority) {
        jdbcTemplate.update("insert into authorities (username, authority) values (?, ?)",
                username, authority);
    }

    @Override
    public void delete(long userId) {
//        jdbcTemplate.update("delete from authorities where user_id = ?", userId)
    }
}
