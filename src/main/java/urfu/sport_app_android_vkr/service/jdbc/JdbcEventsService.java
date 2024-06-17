package urfu.sport_app_android_vkr.service.jdbc;

import org.apache.tomcat.util.net.SocketWrapperBase;
import org.springframework.data.relational.core.sql.LockMode;
import org.springframework.data.relational.repository.Lock;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import urfu.sport_app_android_vkr.domain.dto.request.EventRequest;
import urfu.sport_app_android_vkr.domain.dto.response.EventResponse;
import urfu.sport_app_android_vkr.domain.repository.EventsRepository;
import urfu.sport_app_android_vkr.domain.repository.EventsToUsersRepository;
import urfu.sport_app_android_vkr.service.EventsService;

import java.util.List;

@Repository
public class JdbcEventsService implements EventsService {
    private final EventsRepository eventsRepository;
    private final EventsToUsersRepository eventsToUsersRepository;


    public JdbcEventsService(
            EventsRepository eventsRepository,
            EventsToUsersRepository eventsToUsersRepository) {
        this.eventsRepository = eventsRepository;
        this.eventsToUsersRepository = eventsToUsersRepository;
    }

    @Override
    @Transactional(timeout = 10)
    @Lock(LockMode.PESSIMISTIC_WRITE)
    public void subscribe(long userId, long eventId) {
        if (eventsRepository.getEvent(eventId).numberOfParticipants() > 3) {
            throw new IllegalArgumentException();
        }
        eventsToUsersRepository.add(userId, eventId);
    }

    @Override
    @Transactional(timeout = 10)
    @Lock(LockMode.PESSIMISTIC_WRITE)
    public void unsubscribe(long userId, long eventId) {
        eventsToUsersRepository.delete(userId, eventId);
    }

    @Override
    @Transactional
    public EventResponse add(EventRequest request) {
        Long id = eventsRepository.add(request);
        return eventsRepository.getEvent(id);
    }

    @Override
    public EventResponse editEvent(EventRequest request, long eventId) {
        return eventsRepository.editEvent(request, eventId);
    }

    @Override
    public void delete(long eventId) {
        eventsRepository.delete(eventId);
    }

    @Override
    public EventResponse getEvent(long eventId) {
        return eventsRepository.getEvent(eventId);
    }

    @Override
    public List<EventResponse> findAll() {
        return eventsRepository.findAll();
    }
}
