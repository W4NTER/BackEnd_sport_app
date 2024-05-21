package urfu.sport_app_android_vkr.domain.repository.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import urfu.sport_app_android_vkr.domain.dto.request.EventRequest;
import urfu.sport_app_android_vkr.domain.dto.response.EventResponse;
import urfu.sport_app_android_vkr.domain.repository.EventsRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneOffset;
import java.util.List;

@Repository
public class JdbcEventsRepository implements EventsRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcEventsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void add(EventRequest event) {
        jdbcTemplate.update("insert into events " +
                "(title, body, number_of_participants, city, rating, participants_level, start_time, end_time, price, playground_id) " +
                "values (?,?,?,?,?,?,?,?,?,?)",
                event.title(), event.body(), event.numberOfParticipants(), event.city(), event.rating(),
                event.participantsLevel(), event.startTime(), event.endTime(), event.price(), event.playgroundId());
    }

    @Override
    @Transactional
    public void delete(long eventId) {
        jdbcTemplate.update("delete from events where event_id = ?", eventId);
    }

    @Override
    @Transactional
    public List<EventResponse> findAll() {
        return jdbcTemplate.query("select " +
                "event_id, " +
                "title, " +
                "body, " +
                "number_of_participants," +
                " city, rating, " +
                "participants_level, " +
                "start_time, end_time, " +
                "price, " +
                "playground_id from events",
                (rs, rowNum) -> createResponse(rs));
    }

    @Override
    @Transactional
    public EventResponse getEvent(long eventId) {
        return jdbcTemplate.queryForObject("select " +
                "event_id, " +
                "title, " +
                "body, " +
                "number_of_participants, " +
                "city, rating, " +
                "participants_level, " +
                "start_time, " +
                "end_time, " +
                "price, " +
                "playground_id from events where event_id = ?",
                (rs, rowNum) -> createResponse(rs), eventId);
    }

    private EventResponse createResponse(ResultSet resultSet) throws SQLException {
        return  new EventResponse(
                resultSet.getLong("event_id"),
                resultSet.getString("title"),
                resultSet.getString("body"),
                resultSet.getLong("number_of_participants"),
                resultSet.getString("city"),
                resultSet.getLong("rating"),
                resultSet.getString("participants_level"),
                resultSet.getTimestamp("start_time")
                        .toInstant().atZone(ZoneOffset.systemDefault())
                        .toOffsetDateTime(),
                resultSet.getTimestamp("end_time")
                        .toInstant().atZone(ZoneOffset.systemDefault())
                        .toOffsetDateTime(),
                resultSet.getLong("price"),
                resultSet.getLong("playground_id")
        );
    }
}
