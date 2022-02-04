package project.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;


public class User {
    private final UUID id;
    private final String firstname;
    private final String lastname;
    private final String email;
    private String password;

    public User(@JsonProperty("id") UUID id, @JsonProperty("firstname") String firstname,@JsonProperty("lastname") String lastname,@JsonProperty("email") String email,@JsonProperty("password") String password) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


    public UUID getId() {
        return id;
    }

    public String getfirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    @Override
    public String toString() {
        return this.firstname + " " + this.lastname;
    }
}
