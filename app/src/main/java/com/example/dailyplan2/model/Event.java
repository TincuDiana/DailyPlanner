package com.example.dailyplan2.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.UUID;


public class Event {
    private final UUID idEvent;
    private List<Task> taskList;
    private final String eventName;
    private List<User> usersAttending;
    private final String description;
    private final String data;
    private final String location;

    public Event(List<Task> taskList,String eventname, List<User> usersAttending, String description, String data,String location){
        this.idEvent = UUID.randomUUID();
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
