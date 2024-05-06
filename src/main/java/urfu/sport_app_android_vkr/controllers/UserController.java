package urfu.sport_app_android_vkr.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import urfu.sport_app_android_vkr.controllers.dto.UserResponse;
import urfu.sport_app_android_vkr.domain.service.UsersService;

@RestController
@RequestMapping("user")
public class UserController {
    private final UsersService usersService;

    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/profile")
    public ResponseEntity<UserResponse> profile() {
        UserResponse user = usersService.getUser(getUserId());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    private long getUserId() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return usersService.getUser(username).user_id();
    }

}
