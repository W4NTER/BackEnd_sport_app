package urfu.sport_app_android_vkr.domain.service;

import urfu.sport_app_android_vkr.controllers.dto.UserResponse;

import java.util.List;

public interface UsersService {
    void addUser(String username, String password);
    List<UserResponse> findAll();
    UserResponse getUser(String username);
    UserResponse getUser(Long id);
}
