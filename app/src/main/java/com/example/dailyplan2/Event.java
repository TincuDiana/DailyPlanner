package com.example.dailyplan2;

import com.google.gson.annotations.SerializedName;


public class Event {
    private String eventName;
    private String description;
    private String data;
    private String location;

    @SerializedName("body")
    String text;

    public Event(String eventName, String description, String data, String location) {
        this.eventName = eventName;
        this.description = description;
        this.data = data;
        this.location = location;
    }

    public String getEventName() {
        return eventName;
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
}
