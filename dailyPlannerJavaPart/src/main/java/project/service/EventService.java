package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import project.dao.EventDao;
import project.dao.UserDao;
import project.model.Event;
import project.model.Task;
import project.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventService {
    private final EventDao eventDao;

    @Autowired
    public EventService(@Qualifier("postgres/event") EventDao eventDao) {
        this.eventDao = eventDao;
    }

    public int addEvent(Event event) {
        return eventDao.addEvent(event.getIdEvent(),event.getTaskList(),event.getEventName(), event.getUsersAttending(),event.getDescription(), event.getData(), event.getLocation());
    }

    public List<Event> getEvents(){
        return eventDao.getEvents();
    }


    public Optional<Event> getEventById(UUID id){
        return eventDao.selectEventByID(id);
    }

    public int updateEvent(UUID id, Event newEvent){
        return eventDao.updateEventByID(id, newEvent);
    }
}
