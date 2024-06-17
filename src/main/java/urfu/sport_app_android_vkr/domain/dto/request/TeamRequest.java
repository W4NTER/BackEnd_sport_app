package urfu.sport_app_android_vkr.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public record TeamRequest(
        @JsonProperty("sport")
        String sport,
        @JsonProperty("count_teammates")
        Long countTeammates,
        @JsonProperty("team_level")
        String teamLevel,
        @JsonProperty("title")
        String title,
        @JsonProperty("description")
        String description
        ) {
} 
