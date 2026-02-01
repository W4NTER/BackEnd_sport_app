package urfu.sport_app_android_vkr.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import urfu.sport_app_android_vkr.service.UsersService;

@RestController
public class AuthController {
    private final static Logger LOGGER = LogManager.getLogger();
    private final UsersService usersService;

    public AuthController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/registration")
    public ResponseEntity<String> addUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        try {
            usersService.getUser(username);
            return new ResponseEntity<>("User exists!", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            usersService.addUser(username, password);
            return new ResponseEntity<>("User registered!", HttpStatus.OK);
        }
    }
}
