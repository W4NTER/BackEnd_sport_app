package urfu.sport_app_android_vkr.domain.repository;

import urfu.sport_app_android_vkr.domain.dto.request.EventRequest;
import urfu.sport_app_android_vkr.domain.dto.response.EventResponse;

import java.util.List;

public interface EventsRepository {
    void add(EventRequest event);
    void delete(long eventId);
    List<EventResponse> findAll();
    EventResponse getEvent(long eventId);

}
