package urfu.sport_app_android_vkr.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import urfu.sport_app_android_vkr.domain.repository.jdbc.JdbcUsersRepository;
import urfu.sport_app_android_vkr.domain.service.UsersService;
import urfu.sport_app_android_vkr.functions.Token;

import java.util.Map;

@RestController
public class AuthController {
    private final UsersService usersService;
    private final JdbcUsersRepository jdbcUsersRepository;

    public AuthController(UsersService usersService, JdbcUsersRepository jdbcUsersRepository) {
        this.usersService = usersService;
        this.jdbcUsersRepository = jdbcUsersRepository;
    }

    @GetMapping("/login")
    public ModelAndView getLogin() {
        String tokenFromController = Token.generateToken();
        ModelAndView loginView = new ModelAndView("login");
        loginView.addObject("PageTitle", "Login");
        loginView.addObject("tokenFromController", tokenFromController);
        System.out.println("In Login Controller / Page");
        return loginView;
    }

    @PostMapping("/registration")
    public ResponseEntity<String> addUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password
//            Map<String, Object> model
    ) {
        try {
            jdbcUsersRepository.getUser(username);
//            model.put("message", "User exists!");
            return new ResponseEntity<>("User exists!", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            jdbcUsersRepository.addUser(username, password);
            return new ResponseEntity<>("User registered!", HttpStatus.OK);
        }
    }
}
