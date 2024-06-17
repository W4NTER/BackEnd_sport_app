package urfu.sport_app_android_vkr.domain.dto.response;

public record ProfileResponse(
        long height,
        long weight,
        String city,
        String name,
        String surname,
        String sex,
        String imagePath
) {
}
