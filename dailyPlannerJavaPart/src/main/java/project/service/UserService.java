package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import project.dao.UserDao;
import project.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserDao userDao;
    @Autowired
    public UserService(@Qualifier("postgres") UserDao userDao) {
        this.userDao = userDao;
    }

    public int addUser(User user) {
        return userDao.addUser(user.getId(),user.getfirstname(),user.getLastname(), user.getEmail(), user.getPassword());
    }

    public Optional<User> getUserById(UUID id){
        return userDao.getUserByID(id);
    }

    public Optional<User> getUserByNameAndPassword(String firstname, String password){
        return userDao.getUserByNameAndPassword(firstname,password);
    }
    public Optional<User> getUserByName(String firstname, String lastname){
        return userDao.getUserByName(firstname,lastname);
    }

    public boolean searchForUserEvent(UUID idUser, UUID idEvent){
        return userDao.searchForUserEvent(idUser,idEvent);
    }

    public int populateUserEventTable(UUID idUser, UUID idEvent){
        return userDao.populateUserEventTable(idUser,idEvent);
    }
    public List<User> getUsers() {
        return  userDao.getUsers();
    }

    public int deleteUserById(UUID id){
        return userDao.deletePersonById(id);
    }

    public int updatePersonById( User user){
        return userDao.updatePersonByID(user.getId(),user.getfirstname(), user.getLastname(), user.getEmail(), user.getPassword());
    }

}
