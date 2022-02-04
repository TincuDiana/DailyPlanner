package project.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

public class Event extends ActivityComponent{
    private UUID idEvent;
    private List<Task> taskList;
    private String eventName;
    private List<User> usersAttending;
    private String description;
    private String data;
    private String location;
    public Event(@JsonProperty("idevent") UUID idevent, @JsonProperty("taskList") List<Task> taskList,@JsonProperty("eventname") String eventname,@JsonProperty("usersAttending") List<User> usersAttending,@JsonProperty("description") String description, @JsonProperty("data") String data,@JsonProperty("location") String location){
        this.idEvent = idevent;
        this.taskList = taskList;
        this.eventName = eventname;
        this.usersAttending = usersAttending;
        this.description = description;
        this.data = data;
        this.location = location;
    }

    public Event(UUID idEvent, String eventName, String description, String data, String location) {
        this.idEvent = idEvent;
        this.eventName = eventName;
        this.description = description;
        this.data = data;
        this.location = location;
        taskList = null;
        usersAttending = null;
    }
    public Event(){

    }
//    public Event(UUID idEvent, String eventName, String description, String data, String location) {
//        this.idEvent = idEvent;
//        this.eventName = eventName;
//        this.description = description;
//        this.data = data;
//    }

    public UUID getIdEvent() {
        return idEvent;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public String getEventName() {
        return eventName;
    }

    public List<User> getUsersAttending() {
        return usersAttending;
    }

    public String getDescription() {
        return description;
    }

    public String getData() {
        return data;
    }

    public String getLocation() {
        return location;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void setUsersAttending(List<User> usersAttending) {
        this.usersAttending = usersAttending;
    }
}
