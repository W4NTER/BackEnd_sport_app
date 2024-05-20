package urfu.sport_app_android_vkr.domain.repository;

import urfu.sport_app_android_vkr.domain.dto.response.ProfileResponse;

public interface ProfileRepository {
    void add(long userId, long height, long weight, String city, String name, String surname);
    ProfileResponse getProfile(long user_id);
    void delete(Long user_id);
    ProfileResponse editProfile(long userId, long height, long weight, String city, String name, String surname);
}
