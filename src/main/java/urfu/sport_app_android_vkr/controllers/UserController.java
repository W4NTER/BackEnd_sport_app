package urfu.sport_app_android_vkr.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
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
        ProfileResponse profile = profileService.getProfile(getUserId());
        LOGGER.info("Отдал данные");
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @PostMapping("/profile")
    public ResponseEntity<ProfileResponse> addProfileInfo(
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String height,
            @RequestParam String weight,
            @RequestParam String city
    ) {
        try {
            profileService.addOrEdit(getUserId(),Integer.parseInt(height), Integer.parseInt(weight), city, name, surname);
            LOGGER.info("Добавил профиль");
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            LOGGER.info("неверно введенные данные");
        }
        var profile = profileService.getProfile(getUserId());
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    private long getUserId() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return usersService.getUser(username).user_id();
    }
}
