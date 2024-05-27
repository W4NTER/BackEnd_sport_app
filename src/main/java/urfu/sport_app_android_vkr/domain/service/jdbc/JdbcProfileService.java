package urfu.sport_app_android_vkr.domain.service.jdbc;

import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;
import urfu.sport_app_android_vkr.domain.dto.request.ProfileRequest;
import urfu.sport_app_android_vkr.domain.dto.response.ProfileResponse;
import urfu.sport_app_android_vkr.domain.repository.ProfileRepository;
import urfu.sport_app_android_vkr.domain.service.ProfileService;

import javax.xml.transform.sax.SAXResult;

@Service
public class JdbcProfileService implements ProfileService {

    private final ProfileRepository profileRepository;

    public JdbcProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public ProfileResponse addOrEdit(ProfileRequest request) {
        if (profileRepository.getProfile(request.userId()) != null) {
            return profileRepository.editProfile(request);
        } else {
            profileRepository.add(request);
            return profileRepository.getProfile(request.userId());
        }
    }

    @Override
    public ProfileResponse getProfile(long user_id) {
        return profileRepository.getProfile(user_id);
    }
}
