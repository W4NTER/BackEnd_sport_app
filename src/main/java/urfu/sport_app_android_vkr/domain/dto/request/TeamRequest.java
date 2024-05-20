package urfu.sport_app_android_vkr.domain.dto.request;

public record TeamRequest(
        String sport,
        Long count_teammates,
        String team_level,
        String title,
        String description
        ) {
}
