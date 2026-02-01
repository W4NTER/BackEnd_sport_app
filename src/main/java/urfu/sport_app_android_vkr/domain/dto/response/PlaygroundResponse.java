package urfu.sport_app_android_vkr.domain.dto.response;

public record PlaygroundResponse(
        Long playgroundId,
        Double latitude,
        Double longitude,
        String pathToImage,
        Integer price,
        String sport,
        String city,
        Float rating,
        String address
) {
}
