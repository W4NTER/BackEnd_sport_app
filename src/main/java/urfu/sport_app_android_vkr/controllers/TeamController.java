package urfu.sport_app_android_vkr.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import urfu.sport_app_android_vkr.domain.dto.request.TeamRequest;
import urfu.sport_app_android_vkr.domain.dto.response.TeamResponse;
import urfu.sport_app_android_vkr.domain.service.TeamsService;

import java.util.List;

@RestController
@RequestMapping("teams")
public class TeamController {
    private final static Logger LOGGER = LogManager.getLogger();
    private final TeamsService teamsService;

    public TeamController(TeamsService teamsService) {
        this.teamsService = teamsService;
    }

    @PostMapping("/add")
    public ResponseEntity<TeamResponse> addTeam(@RequestBody TeamRequest request) {
        LOGGER.info(request);
        return new ResponseEntity<>(teamsService.addOrEdit(request), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<TeamResponse>> findAll() {
        return new ResponseEntity<>(teamsService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/delete/{teamId}")
    public void delete(@PathVariable long teamId) {
        LOGGER.info("delete team with id: {}", teamId);
        teamsService.delete(teamId);
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<TeamResponse> team(@PathVariable long teamId) {
        LOGGER.info("get team with id: {}", teamId);
        return new ResponseEntity<>(teamsService.getTeam(teamId), HttpStatus.OK);
    }

    //Не реализованны контроллеры для записи в команды
}
