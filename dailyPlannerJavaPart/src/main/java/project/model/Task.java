package project.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Task extends ActivityComponent{
    @Id
    private UUID idTask;
    private String firstname;
    private String lastname;
    private String description;
    private String location;
    private String data;

    public Task(@JsonProperty("idTask") UUID idTask, @JsonProperty("firstname") String firstName, @JsonProperty("lastname") String lastName, @JsonProperty("data") String date, @JsonProperty("description") String description, @JsonProperty("location") String location) {
        //super();
//        this.idTask = UUID.randomUUID();
        this.idTask = idTask;
        this.firstname = firstName;
        this.lastname = lastName;
        this.description = description;
        this.location= location;
        this.data=date;

    }

    public Task(UUID idTask, UUID idUser, String date, String description, String location) {
    }
    public Task(){

    }
    public UUID getIdTask() {return idTask;}

    public String getFirstName() {
        return firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    /*public UUID getIdUser() {return idUser;}

    public String getDate() {return date;}

    public String getDescription() {return description;}

    public String getLocation() {return location;}*/

    /*@Override
    public void printActivityInfo() {
        //super.printActivityInfo();
        System.out.println("Task ID = " + getIdTask() + "User Name = " + getFirstName() + " " + getLastName() +  " Date = " + getData() + " Description = " + getDescription() + " Location = " + getLocation());
    }*/
}
