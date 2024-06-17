package urfu.sport_app_android_vkr.service.jdbc;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import urfu.sport_app_android_vkr.controllers.dto.UserResponse;
import urfu.sport_app_android_vkr.domain.repository.AuthoritiesRepository;
import urfu.sport_app_android_vkr.domain.repository.UsersRepository;
import urfu.sport_app_android_vkr.service.UsersService;

import java.util.List;

@Service
public class JdbcUsersService implements UsersService {

    private final AuthoritiesRepository authoritiesRepository;
    private final UsersRepository usersRepository;

    public JdbcUsersService(
            AuthoritiesRepository authoritiesRepository,
            UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        this.authoritiesRepository = authoritiesRepository;
    }


    @Override
    @Transactional
    public void addUser(String username, String password) {
        usersRepository.addUser(username, password);
        authoritiesRepository.addAuthority(username, "USER");
    }

    @Override
    public void editPassword(Long userId, String password) {
        usersRepository.editPassword(userId, password);
    }

    @Override
    public List<UserResponse> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public UserResponse getUser(String username) {
        return usersRepository.getUser(username);
    }

    @Override
    public UserResponse getUser(Long id) {
        return usersRepository.getUser(id);
    }
}
