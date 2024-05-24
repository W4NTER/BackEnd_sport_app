package urfu.sport_app_android_vkr.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import urfu.sport_app_android_vkr.domain.dto.request.EventRequest;
import urfu.sport_app_android_vkr.domain.dto.response.EventResponse;
import urfu.sport_app_android_vkr.domain.service.EventsService;

import java.util.List;

@RestController
@RequestMapping("events")
public class EventController {
    private final static Logger LOGGER = LogManager.getLogger();
    private final EventsService eventsService;

    public EventController(EventsService eventsService) {
        this.eventsService = eventsService;
    }


    @GetMapping("")
    public ResponseEntity<List<EventResponse>> findAllEvents() {
        return new ResponseEntity<>(eventsService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<EventResponse> addEvent(@RequestBody EventRequest request) {
        LOGGER.info(request);
        return new ResponseEntity<>(eventsService.addOrEdit(request), HttpStatus.OK);
    }

    @PostMapping("/delete/{eventId}")
    public void delete(@PathVariable long eventId) {
        LOGGER.info("deleted event with id: {}", eventId);
        eventsService.delete(eventId);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventResponse> event(@PathVariable long eventId) {
        LOGGER.info("event with id: {}", eventId);
        return new ResponseEntity<>(eventsService.getEvent(eventId), HttpStatus.OK);
    }

    //не прописаны эндпоинты на подписки потльзователей

}
