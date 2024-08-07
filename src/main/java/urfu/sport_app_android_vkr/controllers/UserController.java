package urfu.sport_app_android_vkr.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import urfu.sport_app_android_vkr.domain.dto.response.ProfileResponse;
import urfu.sport_app_android_vkr.domain.service.ProfileService;
import urfu.sport_app_android_vkr.domain.service.UsersService;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UsersService usersService;
    private final ProfileService profileService;
    private final static Logger LOGGER = LogManager.getLogger();

    public UserController(UsersService usersService, ProfileService profileService) {
        this.usersService = usersService;
        this.profileService = profileService;
    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileResponse> profile() {
        ProfileResponse profile = profileService.getProfile(getUserId()); //не забыть переписать на текущего юзера
        LOGGER.info("Отдал данные");
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    private long getUserId() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return usersService.getUser(username).user_id();
    }
}
