package urfu.sport_app_android_vkr.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TeamRequest(
        @JsonProperty("team_id")
        Long teamId,
        @JsonProperty("sport")
        String sport,
        @JsonProperty("count_teammates")
        Long countTeammates,
        @JsonProperty("team_level")
        String teamLevel,
        @JsonProperty("title")
        String title,
        @JsonProperty("description")
        String description,
        @JsonProperty("author_id")
        Long authorId
        ) {
}
