package project.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.stereotype.Repository;
import project.model.Task;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository("postgres/task")
public class TaskDataAccessService implements TaskDao{
    @Autowired
    private final JdbcTemplate jdbcTemplate;
    private static List<Task> db = new ArrayList<>();
    @Autowired
    public TaskDataAccessService(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    @Override
    public int populateTaskEventTable(UUID idTask, UUID idEvent){
        UUID idTaskEvent = UUID.randomUUID();

        final String query = "INSERT INTO taskeventrel (" + " idtaskevent, " + " idtask," + "idevent) " + "VALUES (?,?,?)";
        return jdbcTemplate.update(query,
                idTaskEvent,
                idTask,
                idEvent);
    }

    @Override
    public int addTask(UUID idTask, String firstName, String lastName, String data, String description, String location) {
        String query = "" +
                "INSERT INTO tasks (" +
                " idTask, " +
                " firstname, " +
                " lastname, " +
                " data, " +
                " description, " +
                " location) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
                query,
                idTask,
                firstName,
                lastName,
                data,
                description,
                location
        );
    }

    @Override
    public int insertTask(Task task) {
        db.add(task);
        return 0;
    }

    @Override
    public List<Task> getTasks() {
        final String query = "SELECT * FROM tasks;";
        return jdbcTemplate.query(query,(resultSet, i)-> {
            UUID idTask = UUID.fromString(resultSet.getString("idTask"));
            String firstName = resultSet.getString("firstname");
            String lastName = resultSet.getString("lastname");
            String date = resultSet.getString("data");
            String description = resultSet.getString("description");
            String location = resultSet.getString("location");
            return new Task(idTask, firstName, lastName, date, description, location);
        });
    }

    @Override
    public Optional<Task> selectTaskByID(UUID idTask) {
        final String query = "SELECT * FROM tasks WHERE idTask = ?;";
        try{
            @Deprecated
            Task task= jdbcTemplate.queryForObject(
                    query,
                    new Object[]{idTask},
                    (resultSet, i)-> {
                        UUID id = UUID.fromString(resultSet.getString("idTask"));
                        String firstName = resultSet.getString("firstname");
                        String lastName = resultSet.getString("lastname");
                        String date = resultSet.getString("data");
                        String description = resultSet.getString("description");
                        String location = resultSet.getString("location");
                        return new Task(id, firstName, lastName, date, description, location);
                    });
            return Optional.ofNullable(task);
        }catch(EmptyResultDataAccessException e) {
            throw new RuntimeException("[selectTaskByID]: Task ID does not exist "+ idTask);
        }catch(IncorrectResultSizeDataAccessException e) {
            throw new RuntimeException("[selectTaskByID]: More than one tasks with the same Id .......");
        }
    }

    public List<Task> selectTasksByUserAndDate(String firstName, String lastName, String myDate) {
        final String query = "SELECT * FROM tasks WHERE firstname = '" + firstName + "' and lastname ='" + lastName + "' and data = '" + myDate + "';";
        return jdbcTemplate.query(query,(resultSet, i)-> {
            UUID idTask = UUID.fromString(resultSet.getString("idTask"));
            String firstname = resultSet.getString("firstname");
            String lastname = resultSet.getString("lastname");
            String date = String.valueOf(resultSet.getDate("data"));
            String description = resultSet.getString("description");
            String location = resultSet.getString("location");
            return new Task(idTask, firstname, lastname, date, description, location);
        });
    }

    @Override
    public int deleteTaskById(UUID id) {
        final String query = "DELETE FROM tasks WHERE idTask = ?;";
        try{
            Object[] args = new Object[] {id};
            if(selectTaskByID(id)!=null){
                jdbcTemplate.update(query, args);
                return 1;
            }
            return 0;
        }catch(EmptyResultDataAccessException e) {
            throw new RuntimeException("[deleteTaskByID]: Task ID does not exist "+ id);
        }catch(IncorrectResultSizeDataAccessException e) {
            throw new RuntimeException("[deleteTaskByID]: More than one tasks with the same Id .......");
        }
    }

    @Override
    public int updateTaskByID(UUID idTask, Task newTask) {
        final String query = "UPDATE tasks SET firstname = ?, lastname = ?, data= ?, description=?, location=? WHERE idTask = ?;";
        try{
            Object[] args = new Object[] {idTask};
            if(selectTaskByID(idTask)!=null){
                jdbcTemplate.update(query, newTask.getFirstName(), newTask.getLastName(), newTask.getData(), newTask.getDescription(), newTask.getLocation(), idTask);
                return 1;
            }
            return 0;
        }catch(EmptyResultDataAccessException e) {
            throw new RuntimeException("[updateTaskByID]: Task ID does not exist "+ idTask);
        }catch(IncorrectResultSizeDataAccessException e) {
            throw new RuntimeException("[updateTaskByID]: More than one tasks with the same Id .......");
        }
    }
}
