package project.model;

import java.util.UUID;

public class NullUser extends AbstractUser{

    @Override
    public boolean isNil() {
        return true;
    }

    @Override
    public UUID getId() {
        return null;
    }

    @Override
    public String getFirstName() {
        return "not available";
    }

    @Override
    public String getLastname() {
        return "not available";
    }

    @Override
    public String getEmail() {
        return "not available";
    }

    @Override
    public String getPassword() {
        return "not available";
    }
}
