package project.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.stereotype.Repository;
import project.model.Event;
import project.model.Task;
import project.model.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository("postgres/event")
public class EventDataAccessService implements EventDao{
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public EventDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public int addEvent(UUID idevent, List<Task> taskList, String eventname, List<User> usersAttending, String description, String data, String location) {
        try {
            String query = "" +
                    "INSERT INTO event (" +
                    " idevent, " +
                    " eventname, " +
                    " description, " +
                    " data, " +
                    " location) " +
                    "VALUES (?, ?, ?, ?, ?)";
            return jdbcTemplate.update(
                    query,
                    idevent,
                    eventname,
                    description,
                    data,
                    location
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Event> getEvents() {
        System.out.println("Searching events!");
        final String query = "SELECT * FROM event;";

        return jdbcTemplate.query(query,(resultSet, i)-> {
            UUID idEvent = UUID.fromString(resultSet.getString("idEvent"));
            String eventName = resultSet.getString("eventName");
            String description = resultSet.getString("description");
            String data = resultSet.getString("data");
            String location = resultSet.getString("location");
            Event eventX = new Event(idEvent, eventName, description,data, location);
            eventX.setTaskList(setTasksForEvent(idEvent));
            eventX.setUsersAttending(setUsersForEvent(idEvent));
            return eventX;
        });

    }


    public List<Task> setTasksForEvent(UUID id){
        final String query = "SELECT * FROM tasks,taskeventrel,event \n" +
                "WHERE tasks.idTask = taskeventrel.idTask\n" +
                "AND taskeventrel.idEvent = event.idEvent\n" +
                "AND event.idEvent = '" + id + "'";
        return  jdbcTemplate.query(query,(resultSet,i)->{
            UUID idTask = UUID.fromString(resultSet.getString("idTask"));
            String firstname = resultSet.getString("firstname");
            String lastname = resultSet.getString("lastname");
            String date = resultSet.getString("data");
            String description = resultSet.getString("description");
            String location = resultSet.getString("location");
            return new Task(idTask, firstname,lastname, date, description, location);
        });
    }
    public List<User> setUsersForEvent(UUID eventId){
        final String query = "SELECT users.id, users.firstname, users.lastname,users.email, users.password FROM users,userseventrel,event \n" +
                "WHERE users.id = userseventrel.iduser\n" +
                "AND userseventrel.idevent = event.idevent\n" +
                "AND event.idevent = '" + eventId + "'";
        return  jdbcTemplate.query(query,(resultSet,i)->{
            UUID id = UUID.fromString(resultSet.getString("id"));
            String firstname = resultSet.getString("firstname");
            System.out.println(firstname);
            String lastname = resultSet.getString("lastname");
            System.out.println(lastname);
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            return new User(id, firstname, lastname, email, password);
        });
    }
    @Override
    public Optional<Event> selectEventByID(UUID idEvent) {
        final String query = "SELECT * FROM event WHERE idevent = ?;";
        try{
            @Deprecated
            Event event = jdbcTemplate.queryForObject(
                    query,
                    new Object[]{idEvent},
                    (resultSet, i)-> {
                        UUID id = UUID.fromString(resultSet.getString("idevent"));
                        String eventname = resultSet.getString("eventname");
                        String description = resultSet.getString("description");
                        String date = resultSet.getString("data");
                        String location = resultSet.getString("location");
                        return new Event(id, eventname, description, date, location);
                    });
            return Optional.ofNullable(event);
        }catch(EmptyResultDataAccessException e) {
            throw new RuntimeException("[selectEventByID]: Event ID does not exist "+ idEvent);
        }catch(IncorrectResultSizeDataAccessException e) {
            throw new RuntimeException("[selectEventByID]: More than one event with the same Id .......");
        }
    }
    @Override
    public int updateEventByID(UUID idEvent,Event newEvent) {
        final String query = "UPDATE event SET eventname = ?, description = ?, data= ?,  location=? WHERE idEvent = ?;";
        try{
            Object[] args = new Object[] {idEvent};
            jdbcTemplate.update(query,newEvent.getEventName(), newEvent.getDescription(), newEvent.getData(), newEvent.getLocation(), idEvent);
//            if(selectEventByID(idEvent)!=null){
//                jdbcTemplate.update(query,newEvent.getEventName(), newEvent.getDescription(), newEvent.getData(), newEvent.getLocation(), idEvent);
//                return 1;
//            }
            return 1;
        }catch(EmptyResultDataAccessException e) {
            throw new RuntimeException("[updateEventByID]: Event ID does not exist "+ idEvent);
        }catch(IncorrectResultSizeDataAccessException e) {
            throw new RuntimeException("[updateEventByID]: More than one event with the same Id .......");
        }
    }

}
