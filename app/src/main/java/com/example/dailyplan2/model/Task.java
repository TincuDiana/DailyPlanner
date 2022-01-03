package com.example.dailyplan2.model;

import android.os.Build;

import com.example.dailyplan2.activity.ToDoListActivity;

import java.time.DayOfWeek;
import java.util.UUID;

public class Task {
    private final UUID idTask;
    //private final UUID idUser;
    private String firstname;
    private String lastname;
    private final String data;
    private final String description;
    private final String location;

    public Task(String firstName, String lastName, String date,  String description, String location) {
        this.idTask = UUID.randomUUID();
        //this.idUser = idUser;
        this.firstname=firstName;
        this.lastname=lastName;
        this.data = date;
        this.description = description;
        this.location = location;
    }

    public Task(String date, String description, String location) {
        this.idTask= null;
        //this.idUser= null;
        this.firstname="";
        this.lastname="";
        this.data = date;
        this.description = description;
        this.location = location;
    }

    public UUID getIdTask() {return idTask;}

    /*public UUID getIdUser() {
    }*/


    public String getFirstName() {
        return firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public String getDate() {return data;}

    public String getDescription() {return description;}

    public String getLocation() {return location;}
}
