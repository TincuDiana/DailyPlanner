package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import project.dao.TaskDao;
import project.model.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {
    private final TaskDao taskDao;
    @Autowired
    public TaskService(@Qualifier("postgres/task") TaskDao taskDao) {this.taskDao = taskDao;}

    public int addTask(Task task) {
        return taskDao.addTask(task.getIdTask(), task.getFirstName(), task.getLastName(), task.getData(), task.getDescription(), task.getLocation());
    }

    public List<Task> getTasks() {
        return  taskDao.getTasks();
    }

    public List<Task> getTasksByUserAndDate(String firstName, String lastName, String date){

        return taskDao.selectTasksByUserAndDate(firstName, lastName, date);
    }

    public Optional<Task> getTaskByID(UUID id){
        return taskDao.selectTaskByID(id);
    }

    public int deleteTask(UUID idTask){
        return taskDao.deleteTaskById(idTask);
    }

    public int updateTask(UUID id, Task newTask){
        return taskDao.updateTaskByID(id, newTask);
    }

    public int populateTaskEventTable(UUID idTask, UUID idEvent){
        return taskDao.populateTaskEventTable(idTask,idEvent);
    }
}
