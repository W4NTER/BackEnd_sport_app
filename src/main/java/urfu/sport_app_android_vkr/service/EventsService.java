package urfu.sport_app_android_vkr.service;

import urfu.sport_app_android_vkr.domain.dto.request.EventRequest;
import urfu.sport_app_android_vkr.domain.dto.response.EventResponse;

import java.util.List;

public interface EventsService {
    EventResponse add(EventRequest request);
    EventResponse editEvent(EventRequest request, long eventId);
    void delete(long eventId);
    EventResponse getEvent(long eventId);
    List<EventResponse> findAll();
    void subscribe(long userId, long eventId);
    void unsubscribe(long userId, long eventId);
}
