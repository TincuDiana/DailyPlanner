package com.example.dailyplan2.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Event {
    private List<Task> taskList;
    private String eventName;
    private List<User> usersAttending;
    private String description;
    private String data;
    private String location;
    private UUID idEvent;

    @SerializedName("body")
    String text;


    public Event(List<Task> taskList,String eventname, List<User> usersAttending, String description, String data,String location){

        this.taskList = taskList;
        this.eventName = eventname;
        this.usersAttending = usersAttending;
        this.description = description;
        this.data = data;
        this.location = location;
    }

    public Event(String eventName, String description, String data, String location) {
        this.idEvent = UUID.randomUUID();
        this.eventName = eventName;
        this.description = description;
        this.data = data;
        this.location = location;
        taskList = new ArrayList<>();
        usersAttending = new ArrayList<>();
    }
    public Event(){}

    public UUID getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(UUID idEvent) {
        this.idEvent = idEvent;
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

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
