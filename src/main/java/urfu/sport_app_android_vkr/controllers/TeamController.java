package urfu.sport_app_android_vkr.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import urfu.sport_app_android_vkr.domain.dto.request.TeamRequest;
import urfu.sport_app_android_vkr.domain.dto.response.TeamResponse;
import urfu.sport_app_android_vkr.service.TeamsService;
import urfu.sport_app_android_vkr.service.UsersService;

import java.util.List;

@RestController
@RequestMapping("teams")
public class TeamController {
    private final static Logger LOGGER = LogManager.getLogger();
    private final TeamsService teamsService;
    private final UsersService usersService;

    public TeamController(TeamsService teamsService, UsersService usersService) {
        this.teamsService = teamsService;
        this.usersService = usersService;
    }

    @PostMapping("/add")
    public ResponseEntity<Long> addTeam(@RequestBody TeamRequest request) {
        LOGGER.info(request);
        return new ResponseEntity<>(teamsService.add(request, getUserId()), HttpStatus.OK);
    }

    @PutMapping("/update/{teamId}")
    public ResponseEntity<TeamResponse> editTeam(
            @PathVariable Long teamId,
            @RequestBody TeamRequest request) {
        return new ResponseEntity<>(teamsService.editTeam(request, teamId), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<TeamResponse>> findAll() {
        LOGGER.info("teams data is out");
        return new ResponseEntity<>(teamsService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{teamId}")
    public ResponseEntity<Void> delete(@PathVariable long teamId) {
        LOGGER.info("delete team with id: {}", teamId);
        teamsService.delete(teamId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<TeamResponse> team(@PathVariable long teamId) {
        LOGGER.info("get team with id: {}", teamId);
        return new ResponseEntity<>(teamsService.getTeam(teamId), HttpStatus.OK);
    }

    private long getUserId() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return usersService.getUser(username).user_id();
    }

    @PostMapping("/sub/{teamId}")
    public ResponseEntity<Void> subscribe(
            @PathVariable long teamId
    ) {
        teamsService.subscribe(getUserId(), teamId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/unsubscribe/{teamId}")
    public ResponseEntity<Void> unsubscribe(
            @PathVariable long teamId
    ) {
        teamsService.unsubscribe(getUserId(), teamId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<TeamResponse>> findBySport(
            @RequestParam String sport
    ) {
        return new ResponseEntity<>(teamsService.findBySport(sport), HttpStatus.OK);
    }
}
