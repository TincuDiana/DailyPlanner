package project.model;

import java.util.UUID;

public abstract class AbstractUser {
    private  UUID id;
    private  String firstname;
    private String lastname;
    private String email;
    private String password;
    public abstract boolean isNil();


    public abstract UUID getId();

    public abstract String getFirstName();

    public abstract String getLastname();

    public abstract String getEmail();

    public abstract String getPassword();


}

