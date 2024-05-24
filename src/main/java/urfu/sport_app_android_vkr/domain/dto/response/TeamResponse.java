package urfu.sport_app_android_vkr.domain.dto.response;

public record TeamResponse(
        Long teamId,
        String sport,
        Long count_teammates,
        String team_level,
        String title,
        String description,
        Long authorId
) {
}
