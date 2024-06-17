package urfu.sport_app_android_vkr.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

public record EventRequest(
        @JsonProperty("title")
        String title,
        @JsonProperty("body")
        String body,
        @JsonProperty("number_of_participants")
        Long numberOfParticipants,
        @JsonProperty("city")
        String city,
        @JsonProperty("rating")
        Long rating,
        @JsonProperty("participants_level")
        String participantsLevel,
        @JsonProperty("start_time")
        OffsetDateTime startTime,
        @JsonProperty("end_time")
        OffsetDateTime endTime,
        @JsonProperty("price")
        Long price,
        @JsonProperty("playground_id")
        Long playgroundId,
        @JsonProperty("author_id")
        Long authorId,
        @JsonProperty("sport")
        String sport
) {
}
