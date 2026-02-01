package urfu.sport_app_android_vkr.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import urfu.sport_app_android_vkr.domain.dto.request.PlaygroundRequest;
import urfu.sport_app_android_vkr.domain.dto.response.PlaygroundResponse;
import urfu.sport_app_android_vkr.service.PlaygroundService;

import java.util.List;

@RestController
@RequestMapping("playgrounds")
public class PlaygroundController {
    private final PlaygroundService playgroundService;
    private final static Logger LOGGER = LogManager.getLogger();

    public PlaygroundController(PlaygroundService playgroundService) {
        this.playgroundService = playgroundService;
    }

    @GetMapping("")
    public ResponseEntity<List<PlaygroundResponse>> findAllPlaygrounds() {
        return new ResponseEntity<>(playgroundService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{playgroundId}")
    public ResponseEntity<PlaygroundResponse> getPlayground(
            @PathVariable Long playgroundId
    ) {
        return new ResponseEntity<>(playgroundService.getPlayground(playgroundId), HttpStatus.OK);
    }

    @GetMapping("/{latitude}/{longitude}")
    public ResponseEntity<PlaygroundResponse> getPlayground(
            @PathVariable Double latitude,
            @PathVariable Double longitude
    ) {
        return new ResponseEntity<>(playgroundService.getPlayground(latitude, longitude),
                HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Long> addPlayground(@RequestBody PlaygroundRequest request) {
        return new ResponseEntity<>(playgroundService.add(request), HttpStatus.OK);
    }

    @PutMapping("/update/{playgroundId}")
    public ResponseEntity<PlaygroundResponse> updatePlayground(
            @PathVariable long playgroundId,
            @RequestBody PlaygroundRequest request
    ) {
        return new ResponseEntity<>(playgroundService.editPlayground(request, playgroundId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{playgroundId}")
    public ResponseEntity<Void> deletePlayground(
            @PathVariable long playgroundId
    ) {
        playgroundService.delete(playgroundId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
