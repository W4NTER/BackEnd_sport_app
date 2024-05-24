package urfu.sport_app_android_vkr.domain.service;

import urfu.sport_app_android_vkr.domain.dto.response.ProfileResponse;

public interface ProfileService {
    ProfileResponse addOrEdit(long userId, long height, long weight, String city, String name, String surname);
    ProfileResponse getProfile(long user_id);
}
