package urfu.sport_app_android_vkr.domain.repository;

import urfu.sport_app_android_vkr.domain.dto.response.EventResponse;

import java.util.List;

public interface EventsToUsersRepository {
    void add(long userId, long eventId);
    void delete(long userId, long eventId);
    Long getEventByUserId(long userId);
    List<EventResponse> getAllByUser(long userId);
    List<EventResponse> getAllByEvent(long eventID);
}
