package urfu.sport_app_android_vkr.domain.service.jdbc;

import org.springframework.stereotype.Service;
import urfu.sport_app_android_vkr.domain.dto.response.ProfileResponse;
import urfu.sport_app_android_vkr.domain.repository.ProfileRepository;
import urfu.sport_app_android_vkr.domain.service.ProfileService;

@Service
public class JdbcProfileService implements ProfileService {

    private final ProfileRepository profileRepository;

    public JdbcProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public ProfileResponse addOrEdit(long userId, long height, long weight, String city, String name, String surname) {
        if (profileRepository.getProfile(userId) != null) {
            return profileRepository.editProfile(userId, height, weight, city, name, surname);
        } else {
            profileRepository.add(userId, height, weight, city, name, surname);
            return profileRepository.getProfile(userId);
        }
    }

    @Override
    public ProfileResponse getProfile(long user_id) {
        return profileRepository.getProfile(user_id);
    }
}
