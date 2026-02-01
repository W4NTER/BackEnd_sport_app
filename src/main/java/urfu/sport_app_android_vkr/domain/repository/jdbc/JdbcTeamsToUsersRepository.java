package urfu.sport_app_android_vkr.domain.repository.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import urfu.sport_app_android_vkr.domain.repository.TeamsToUsersRepository;

@Repository
public class JdbcTeamsToUsersRepository implements TeamsToUsersRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTeamsToUsersRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void add(long user_id, long team_id) {
        jdbcTemplate.update("insert into teams_to_users values (?, ?)",
                user_id, team_id);
    }

    @Override
    @Transactional
    public void delete(long user_id, long team_id) {
        jdbcTemplate.update("delete from teams_to_users where user_id = ? and team_id = ?",
                user_id, team_id);
    }
}
