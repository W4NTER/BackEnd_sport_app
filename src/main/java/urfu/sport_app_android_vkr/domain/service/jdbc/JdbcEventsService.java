package urfu.sport_app_android_vkr.domain.service.jdbc;

import org.springframework.stereotype.Repository;
import urfu.sport_app_android_vkr.domain.dto.request.EventRequest;
import urfu.sport_app_android_vkr.domain.dto.response.EventResponse;
import urfu.sport_app_android_vkr.domain.repository.jdbc.JdbcEventsRepository;
import urfu.sport_app_android_vkr.domain.repository.jdbc.JdbcEventsToUsersRepository;
import urfu.sport_app_android_vkr.domain.service.EventsService;

import java.util.List;

@Repository
public class JdbcEventsService implements EventsService {
    private final JdbcEventsRepository jdbcEventsRepository;
    private final JdbcEventsToUsersRepository jdbcEventsToUsersRepository;


    public JdbcEventsService(JdbcEventsRepository jdbcEventsRepository, JdbcEventsToUsersRepository jdbcEventsToUsersRepository) {
        this.jdbcEventsRepository = jdbcEventsRepository;
        this.jdbcEventsToUsersRepository = jdbcEventsToUsersRepository;
    }

    @Override
    public void subscribe(long userId, long eventId) {
        jdbcEventsToUsersRepository.add(userId, eventId);
    }

    @Override
    public void removeSub(long userId, long eventId) {
        jdbcEventsToUsersRepository.delete(userId, eventId);
    }

    @Override
    public EventResponse addOrEdit(EventRequest request) {
        try {
            jdbcEventsRepository.getEvent(request.eventId());
            return jdbcEventsRepository.editEvent(request);
        } catch (Exception e) {
            jdbcEventsRepository.add(request);
            return jdbcEventsRepository.getEvent(request.eventId());
        }
    }

    @Override
    public void delete(long eventId) {
        jdbcEventsRepository.delete(eventId);
    }

    @Override
    public EventResponse getEvent(long eventId) {
        return jdbcEventsRepository.getEvent(eventId);
    }

    @Override
    public List<EventResponse> findAll() {
        return jdbcEventsRepository.findAll();
    }
}
