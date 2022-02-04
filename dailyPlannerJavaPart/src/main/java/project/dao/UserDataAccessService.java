package project.dao;
import org.mapstruct.BeanMapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.model.User;
import project.model.UserFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class UserDataAccessService implements UserDao {

    private final JdbcTemplate jdbcTemplate;
    UserFactory userFactory = new UserFactory();
    private static List<User> users = new ArrayList<>();
    @Autowired
    public UserDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addUser(UUID id, String firstname, String lastname, String email, String password) {
        String query = "" +
                "INSERT INTO users (" +
                " id, " +
                " firstname, " +
                " lastname, " +
                " email, " +
                " password) " +
                "VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
                query,
                id,
                firstname,
                lastname,
                email,
                password
        );

    }

    @Override
    public List<User> getUsers() {
        final String query = "SELECT * FROM users;";
        return jdbcTemplate.query(query,(resultSet, i)-> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String firstname = resultSet.getString("firstname");
            String lastname = resultSet.getString("lastname");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            User userX = new User(id,firstname,lastname,email,password);
            return userX;
        });
    }

    @Override
    public Optional<User> getUserByID(UUID id) {
        /*
        final String query = "SELECT * FROM users WHERE users = '" + id + "';";
        jdbcTemplate.query(query,(result) -> {});
        */
        users = getUsers();
        return users.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    public Optional<User> getUserByNameAndPassword(String firstname, String password){
        System.out.println(firstname);
        System.out.println(password);
        users = getUsers();
        return users.stream()
                .filter(person -> person.getfirstname().equals(firstname))
                .filter(person -> person.getPassword().equals(password))
                .findFirst();
    }

    public Optional<User> getUserByName(String firstname, String lastname){
        users = getUsers();
        //System.out.println("users ==== " + users.get(0).getLastname());
        return users.stream()
                .filter(person -> person.getfirstname().equals(firstname))
                .filter(person -> person.getLastname().equals(lastname))
                .findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        if(getUserByID(id).isEmpty()) {
            return 0;
        }
        else {
            String query = "DELETE FROM users " +
                    "WHERE id = '" + id + "';";
            jdbcTemplate.execute(query);
            return 1;
        }
    }

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public int updatePersonByID(UUID id, String firstname, String lastName, String email, String password) {
        if(getUserByID(id).isPresent()) {
            User person = getUserByID(id).get();
            if(person != null) {
                if (firstname.equals(""))
                    firstname = person.getfirstname();
                if (lastName.equals(""))
                    lastName = person.getLastname();
                if (email.equals(""))
                    email = person.getEmail();
                if (password.equals(""))
                    password = person.getPassword();
                String query = "UPDATE users\n" +
                        "SET firstname = '" + firstname + "',\n" +
                        "    lastname = '" + lastName + "',\n" +
                        "    email = '" + email + "',\n" +
                        "    password = '" + password + "'\n" +
                        "WHERE id = '" + id + "';";
                System.out.println(query);
                jdbcTemplate.execute(query);
                return 1;
            }
            else return 0;
        }
        return 0;
    }

    @Override
    public boolean searchForUserEvent(UUID idUser, UUID idEvent){
        final String query = "SELECT iduser FROM userseventrel WHERE  iduser = '" + idUser +"'" + " AND idEvent = "+ "'" + idEvent + "';";
        Object users = DataAccessUtils.singleResult(jdbcTemplate.queryForList(query, new Object[]{}/*, (Object) (rs, rowNum) ->
                new User ( UUID.fromString(rs.getString("iduser")),"","","","")*/));
        if(users!=null)
            return true;
        else return false;
    }

    @Override
    public int populateUserEventTable(UUID idUser, UUID idEvent){
        UUID idUserEvent = UUID.randomUUID();
        System.out.println("SEARCH ]==" + searchForUserEvent(idUser,idEvent));
        System.out.println("id = " + idEvent + "id user  = " + idUser);
        if(searchForUserEvent(idUser,idEvent)==false){
        final String query = "INSERT INTO userseventrel (" + " iduserevent, " + " iduser," + "idEvent) " + "VALUES (?,?,?)";
        return jdbcTemplate.update(query,
                idUserEvent,
                idUser,
                idEvent);}
        else return 0;
    }
}
