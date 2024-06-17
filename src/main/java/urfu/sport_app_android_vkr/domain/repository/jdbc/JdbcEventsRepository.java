package urfu.sport_app_android_vkr.domain.repository.jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import urfu.sport_app_android_vkr.domain.dto.request.EventRequest;
import urfu.sport_app_android_vkr.domain.dto.response.EventResponse;
import urfu.sport_app_android_vkr.domain.repository.EventsRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneOffset;
import java.util.List;

@Repository
public class JdbcEventsRepository implements EventsRepository {
    private final static Logger LOGGER = LogManager.getLogger();
    private final JdbcTemplate jdbcTemplate;

    public JdbcEventsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long add(EventRequest event) {
        KeyHolder keyHolder =  new GeneratedKeyHolder();

        String sql = "insert into events " +
                "(title, body, number_of_participants, city, rating, participants_level," +
                        " start_time, end_time, price, playground_id, author_id, sport) " +
                "values (?,?,?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, event.title());
            ps.setString(2, event.body());
            ps.setLong(3, event.numberOfParticipants());
            ps.setString(4, event.city());
            ps.setLong(5, event.rating());
            ps.setString(6, event.participantsLevel());
            ps.setObject(7, event.startTime());
            ps.setObject(8, event.endTime());
            ps.setLong(9, event.price());
            ps.setLong(10, event.playgroundId());
            ps.setLong(11, event.authorId());
            ps.setString(12, event.sport());
            return ps;
        }, keyHolder);
        Long eventId = (Long) keyHolder.getKeys().get("event_id");
        LOGGER.info("event_id = " + eventId);
        return eventId;
    }

    @Override
    @Transactional
    public void delete(long eventId) {
        jdbcTemplate.update(
                "delete from events where event_id = ?", eventId);
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
                "playground_id, " +
                        "author_id, sport from events",
                (rs, rowNum) -> createResponse(rs));
    }

    @Override
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
                "playground_id, " +
                        "author_id, sport from events where event_id = ?",
                (rs, rowNum) -> createResponse(rs), eventId);
    }

    @Override
    @Transactional
    public EventResponse editEvent(EventRequest request, long eventId) {
        jdbcTemplate.update("update events set " +
                "title = ?, " +
                "body = ?, " +
                "number_of_participants = ?, " +
                "city = ?, " +
                "rating = ?, " +
                "participants_level = ?, " +
                "start_time = ?, " +
                "end_time = ?, " +
                "price = ?, " +
                "playground_id = ?, " +
                "author_id = ?, " +
                        "sport = ? " +
                "where event_id = ?",
                request.title(), request.body(), request.numberOfParticipants(),
                request.city(), request.rating(), request.participantsLevel(), request.startTime(),
                request.endTime(), request.price(), request.playgroundId(), request.authorId(), eventId);
        return getEvent(eventId);
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
                resultSet.getLong("playground_id"),
                resultSet.getLong("author_id"),
                resultSet.getString("sport")
        );
    }
}
