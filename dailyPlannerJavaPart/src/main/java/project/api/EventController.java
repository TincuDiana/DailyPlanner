package project.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.model.Event;
import project.model.Task;
import project.model.User;
import project.service.EventService;
import project.service.UserService;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/event/")
@RestController
public class EventController {
    private final EventService eventService;
    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("addEvent/")
    public void addEvent(@RequestBody Event event) {
        eventService.addEvent(event);
    }

    @GetMapping("getEvents/")
    public List<Event> getEvents(){
        return eventService.getEvents();
    }

    @PutMapping(path = "update/{idevent}/")
    public void updateEvent(@PathVariable("idevent") UUID idEvent, @RequestBody Event eventToUpdate){
        eventService.updateEvent(idEvent, eventToUpdate);
    }
    @GetMapping(path = "{idevent}/")
    public Event getEventById(@PathVariable("idevent") UUID idEvent) throws Exception {
        return eventService.getEventById(idEvent)
                .orElse(null);
    }
}
