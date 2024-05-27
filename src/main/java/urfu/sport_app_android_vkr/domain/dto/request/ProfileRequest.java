package urfu.sport_app_android_vkr.domain.dto.request;

public record ProfileRequest(
        long userId,
        long height,
        long weight,
        String city,
        String name,
        String surname,
        String sex
) {
}
