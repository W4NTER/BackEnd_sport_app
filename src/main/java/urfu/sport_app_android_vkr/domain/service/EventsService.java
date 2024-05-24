package urfu.sport_app_android_vkr.domain.service;

import urfu.sport_app_android_vkr.domain.dto.request.EventRequest;
import urfu.sport_app_android_vkr.domain.dto.response.EventResponse;

import java.util.List;

public interface EventsService {
    EventResponse addOrEdit(EventRequest request);
    void delete(long eventId);
    EventResponse getEvent(long eventId);
    List<EventResponse> findAll();
    void subscribe(long userId, long eventId);
    void removeSub(long userId, long eventId);
}
