package project.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.model.User;
import project.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/v1/user/")
@RestController
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("addUser/")
    public void addUser(@RequestBody User user){
        System.out.println(user.getfirstname());
        userService.addUser(user);
    }
    @GetMapping(path ="getUser/{id}/")
    public User getUserById(@PathVariable("id") UUID id){
        return userService.getUserById(id)
                .orElse(null);
    }

    @GetMapping(path = "getUserName/{firstname}/{password}/")
    public Optional<User> getUserByNameAndPassword(@PathVariable("firstname") String firstname, @PathVariable("password") String password){
        Optional<User> user = userService.getUserByNameAndPassword(firstname,password);
        user.toString();
        System.out.println("FIRST NAME " + firstname + " " + password);
        return userService.getUserByNameAndPassword(firstname,password);
    }

    @GetMapping(path = "getUserByName/{firstname}/{lastname}/")
    public Optional<User> getUserByName(@PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname){
        Optional<User> user = userService.getUserByName(firstname,lastname);
        user.toString();
        return userService.getUserByName(firstname,lastname);
    }

    @PostMapping("addUserEvent/")
    public int populateUserEventTable( @RequestParam UUID idUser,@RequestParam UUID idEvent){
        return userService.populateUserEventTable(idUser,idEvent);
    }

    @GetMapping("getAllUsers/")
    public List<User> getUsers(){
        return userService.getUsers();
    }


    @DeleteMapping(path = "deleteId/{id}")
    public void deleteUserById (@PathVariable("id") UUID id)
    {
        userService.deleteUserById(id);
    }

    @PutMapping(path = "updateUser/")
    public void updatePersonById (@RequestBody User user)
    {
        userService.updatePersonById(user);
    }
}
