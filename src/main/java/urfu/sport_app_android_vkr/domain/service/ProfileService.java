package urfu.sport_app_android_vkr.domain.service;

import urfu.sport_app_android_vkr.domain.dto.response.ProfileResponse;

public interface ProfileService {
    void add(long userId, long height, long weight, String city);
    ProfileResponse getProfile(long user_id);
}
