package project.model;

import java.util.UUID;

public class UserFactory {

    public static AbstractUser createUser(UUID id,String firstname,String lastname,String email,String password){
        if(id != null && !firstname.equals("") && !lastname.equals("") && !email.equals("") && !password.equals("")){
            //return new User(id, firstname, lastname, email, password);
        }
        return new NullUser();
    }
}
