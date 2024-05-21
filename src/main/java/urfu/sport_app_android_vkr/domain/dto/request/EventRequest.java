package urfu.sport_app_android_vkr.domain.dto.request;

import java.time.OffsetDateTime;

public record EventRequest(
        Long eventId,
        String title,
        String body,
        Long numberOfParticipants,
        String city,
        Long rating,
        String participantsLevel,
        OffsetDateTime startTime,
        OffsetDateTime endTime,
        Long price,
        Long playgroundId
) {
}
