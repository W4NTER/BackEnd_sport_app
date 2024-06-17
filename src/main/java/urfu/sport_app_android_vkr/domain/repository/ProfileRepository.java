package urfu.sport_app_android_vkr.domain.repository;

import urfu.sport_app_android_vkr.domain.dto.request.ProfileRequest;
import urfu.sport_app_android_vkr.domain.dto.response.ProfileResponse;

public interface ProfileRepository {
    Long add(ProfileRequest request, long userId);
    ProfileResponse getProfile(long userId);
    void delete(Long user_id);
    ProfileResponse editProfile(ProfileRequest request, long userId);
}
