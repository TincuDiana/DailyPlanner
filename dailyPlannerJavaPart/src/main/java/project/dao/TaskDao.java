package project.dao;
import org.springframework.context.annotation.Bean;
import project.model.Task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskDao {
    default int addTask(String firstName, String lastName, String date, String description, String location){
        UUID id = UUID.randomUUID();
        Task newTask = new Task(id, firstName, lastName, date, description, location);
        if(newTask != null)
            return 1;

        return 0;
    }
    default int addTask(UUID idTask, String firstName, String lastName, String date, String description, String location){
        Task newTask = new Task(idTask, firstName, lastName, date, description, location);
        if(newTask != null)
            return 1;

        return 0;
    }
    int insertTask(Task task);
    List<Task> getTasks ();
    Optional<Task> selectTaskByID(UUID idTask);
    List<Task> selectTasksByUserAndDate(String firstName, String lastName,String date);
    int deleteTaskById(UUID id);
    int updateTaskByID(UUID idTask, Task newTask);
    int populateTaskEventTable(UUID idTask, UUID idEvent);

}
