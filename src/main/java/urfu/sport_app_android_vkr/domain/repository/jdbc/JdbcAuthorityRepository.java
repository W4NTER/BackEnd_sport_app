package urfu.sport_app_android_vkr.domain.repository.jdbc;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import urfu.sport_app_android_vkr.domain.repository.AuthoritiesRepository;

@Repository
public class JdbcAuthorityRepository implements AuthoritiesRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void addAuthority(String username, String authority) {
        jdbcTemplate.update("insert into authorities(username, authority) values (?, ?)",
                username, authority);
    }
}
