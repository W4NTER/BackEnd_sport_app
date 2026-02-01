package urfu.sport_app_android_vkr.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import urfu.sport_app_android_vkr.domain.dto.request.EventRequest;
import urfu.sport_app_android_vkr.domain.dto.response.EventResponse;
import urfu.sport_app_android_vkr.service.EventsService;
import urfu.sport_app_android_vkr.service.UsersService;

import java.util.List;

@RestController
@RequestMapping("events")
public class EventController {
    private final static Logger LOGGER = LogManager.getLogger();
    private final EventsService eventsService;
    private final UsersService usersService;

    public EventController(EventsService eventsService, UsersService usersService) {
        this.eventsService = eventsService;
        this.usersService = usersService;
    }


    @GetMapping("")
    public ResponseEntity<List<EventResponse>> findAllEvents() {
        return new ResponseEntity<>(eventsService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<EventResponse> addEvent(@RequestBody EventRequest request) {
        LOGGER.info(request);
        return new ResponseEntity<>(eventsService.add(request), HttpStatus.OK);
    }

    @PutMapping("/update/{eventId}")
    public ResponseEntity<EventResponse> updateEvent(
            @PathVariable long eventId,
            @RequestBody EventRequest request
    ) {
        return new ResponseEntity<>(eventsService.editEvent(request, eventId), HttpStatus.OK);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventResponse> event(@PathVariable long eventId) {
        LOGGER.info("event with id: {}", eventId);
        return new ResponseEntity<>(eventsService.getEvent(eventId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable long eventId) {
        eventsService.delete(eventId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("sub/{eventId}")
    public ResponseEntity<Void> subscribe(
            @PathVariable long eventId
    ) {
        eventsService.subscribe(getUserId(), eventId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/unsubscribe/{teamId}")
    public ResponseEntity<Void> unsubscribe(
            @PathVariable long teamId
    ) {
        eventsService.unsubscribe(getUserId(), teamId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private long getUserId() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return usersService.getUser(username).user_id();
    }
}
