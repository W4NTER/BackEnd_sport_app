package urfu.sport_app_android_vkr.service.jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import urfu.sport_app_android_vkr.domain.dto.request.ProfileRequest;
import urfu.sport_app_android_vkr.domain.dto.response.ProfileResponse;
import urfu.sport_app_android_vkr.domain.repository.ProfileRepository;
import urfu.sport_app_android_vkr.service.ProfileService;

@Service
public class JdbcProfileService implements ProfileService {
    private final static Logger LOGGER = LogManager.getLogger();
    private final ProfileRepository profileRepository;

    public JdbcProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    @Transactional
    public ProfileResponse add(ProfileRequest request, long userId) {
        profileRepository.add(request, userId);
        return profileRepository.getProfile(userId);
    }

    @Override
    public ProfileResponse getProfile(long user_id) {
        return profileRepository.getProfile(user_id);
    }

    @Override
    public ProfileResponse editProfile(ProfileRequest request, long userId) {
        return profileRepository.editProfile(request, userId);
    }

    @Override
    public void delete(long userId) {
        profileRepository.delete(userId);
    }
}
