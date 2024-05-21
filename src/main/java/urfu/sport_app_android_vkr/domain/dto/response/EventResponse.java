package urfu.sport_app_android_vkr.domain.dto.response;

import java.time.OffsetDateTime;

public record EventResponse(
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
