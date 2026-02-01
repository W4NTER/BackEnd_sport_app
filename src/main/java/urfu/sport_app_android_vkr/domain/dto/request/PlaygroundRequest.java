package urfu.sport_app_android_vkr.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PlaygroundRequest(
        @JsonProperty("latitude")
        Double latitude,
        @JsonProperty("longitude")
        Double longitude,
        @JsonProperty("path_to_image")
        String pathToImage,
        @JsonProperty("price")
        Integer price,
        @JsonProperty("sport")
        String sport,
        @JsonProperty("city")
        String city,
        @JsonProperty("address")
        String address
) {
}
