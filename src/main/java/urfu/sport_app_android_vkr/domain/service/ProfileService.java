package urfu.sport_app_android_vkr.domain.service;

import urfu.sport_app_android_vkr.domain.dto.request.ProfileRequest;
import urfu.sport_app_android_vkr.domain.dto.response.ProfileResponse;

public interface ProfileService {
    ProfileResponse addOrEdit(ProfileRequest request);
    ProfileResponse getProfile(long userId);
}
