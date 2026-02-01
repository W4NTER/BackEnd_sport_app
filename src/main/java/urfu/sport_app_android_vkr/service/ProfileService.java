package urfu.sport_app_android_vkr.service;

import urfu.sport_app_android_vkr.domain.dto.request.ProfileRequest;
import urfu.sport_app_android_vkr.domain.dto.response.ProfileResponse;

public interface ProfileService {
    ProfileResponse add(ProfileRequest request, long userId);
    ProfileResponse getProfile(long userId);
    ProfileResponse editProfile(ProfileRequest request, long userId);
    void delete(long userId);
}
