package urfu.sport_app_android_vkr.domain.service.jabc;

import org.springframework.stereotype.Service;
import urfu.sport_app_android_vkr.controllers.dto.UserResponse;
import urfu.sport_app_android_vkr.domain.repository.jdbc.JdbcAuthorityRepository;
import urfu.sport_app_android_vkr.domain.repository.jdbc.JdbcUsersRepository;
import urfu.sport_app_android_vkr.domain.service.UsersService;

import java.util.List;

@Service
public class JdbcUsersService implements UsersService {

    private final JdbcAuthorityRepository jdbcAuthorityRepository;
    private final JdbcUsersRepository jdbcUserRepository;

    public JdbcUsersService(
            JdbcAuthorityRepository jdbcAuthorityRepository,
            JdbcUsersRepository jdbcUserRepository) {
        this.jdbcAuthorityRepository = jdbcAuthorityRepository;
        this.jdbcUserRepository = jdbcUserRepository;
    }


    @Override
    public void addUser(String username, String password) {
        jdbcUserRepository.addUser(username, password);
        jdbcAuthorityRepository.addAuthority(username, "USER");
    }

    @Override
    public List<UserResponse> findAll() {
        return jdbcUserRepository.findAll();
    }

    @Override
    public UserResponse getUser(String username) {
        return jdbcUserRepository.getUser(username);
    }

    @Override
    public UserResponse getUser(Long id) {
        return jdbcUserRepository.getUser(id);
    }
}
