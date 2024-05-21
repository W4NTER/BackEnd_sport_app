package urfu.sport_app_android_vkr.domain.repository.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import urfu.sport_app_android_vkr.domain.dto.response.EventResponse;
import urfu.sport_app_android_vkr.domain.repository.EventsToUsersRepository;

import java.util.List;

@Repository
public class JdbcEventsToUsersRepository implements EventsToUsersRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcEventsToUsersRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void add(long userId, long eventId) {
        jdbcTemplate.update("insert into events_to_users (user_id, event_id) values (?,?)", userId, eventId);
    }

    @Override
    @Transactional
    public void delete(long userId, long eventId) {
        jdbcTemplate.update("delete from events_to_users where user_id = ? and event_id = ?",
                userId, eventId);
    }

    @Override
    public List<EventResponse> getAllByUser(long userId) {
        return List.of();
    }

    @Override
    public List<EventResponse> getAllByEvent(long eventID) {
        return List.of();
    }

}
