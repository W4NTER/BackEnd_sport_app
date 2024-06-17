package urfu.sport_app_android_vkr.domain.repository;

import urfu.sport_app_android_vkr.controllers.dto.UserResponse;
import java.util.List;

public interface UsersRepository {
    void addUser(String username, String password);
    void editPassword(Long userId, String Password);
    void delete();
    List<UserResponse> findAll();
    UserResponse getUser(String username);
    UserResponse getUser(Long id);
}
