package project.dao;

import project.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDao {

    default int addUser(UUID id, String firstName, String lastName, String email, String password) {
        User newUser = new User(id, firstName, lastName, email, password);
        if (newUser != null)
            return 1;

        return 0;
    }

    List<User> getUsers ();
    Optional<User> getUserByID(UUID id);
    Optional<User> getUserByNameAndPassword(String firstname, String password);
    int deletePersonById(UUID id);
    int updatePersonByID(UUID id, String firstName, String lastName, String email, String password);
    Optional<User> getUserByName(String firstname, String lastname);
    boolean searchForUserEvent(UUID idUser, UUID idEvent);
    int populateUserEventTable(UUID idUser, UUID idEvent);
}
