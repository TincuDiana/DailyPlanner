package project.dao;

import project.model.Event;
import project.model.Task;
import project.model.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventDao {
    default int addEvent(UUID idEvent, List<Task> taskList, String eventName, List<User> usersAttending, String description,String data, String location){
        Event newEvent = new Event(idEvent,taskList,eventName, usersAttending, description, data, location);
        if(newEvent != null)
            return 1;
        return 0;
    }

    List<Event> getEvents();
    Optional<Event> selectEventByID(UUID idEvent);
    int updateEventByID(UUID idEvent, Event newEvent);


}
