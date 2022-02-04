package project.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.model.Task;
import project.service.TaskService;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/task/")
@RestController
public class TaskController {
    private final TaskService taskService;
    @Autowired
    public TaskController(TaskService taskService) {this.taskService = taskService;}

    @PostMapping("addTask/")
    public void addTask(@RequestBody Task task){
        taskService.addTask(task);
        //task.printActivityInfo();
    }

    @GetMapping(path = "getTasks/")
    public List<Task> getTasks(){
        System.out.println("Getting tasks!");
        return taskService.getTasks();
    }

    @GetMapping(path = "{idTask}/")
    public Task getTaskByID(@PathVariable("idTask") UUID idTask) throws Exception {
        return taskService.getTaskByID(idTask)
                .orElse(null);
    }

    @GetMapping(path= "getTaskByUserAndDate/{firstname}/{lastname}/{data}/")
    public List<Task> getTasksByUserAndDate(@PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname,@PathVariable("data") String date){
        return taskService.getTasksByUserAndDate(firstname, lastname,date);
    }

    @DeleteMapping(path= "{idTask}")
    public void  deleteTaskByID(@PathVariable("idTask") UUID idTask){
        taskService.deleteTask(idTask);
    }

    @PutMapping(path = "update/{idTask}/")
    public void updateTask(@PathVariable("idTask") UUID idTask, @RequestBody Task taskToUpdate){
        taskService.updateTask(idTask, taskToUpdate);
    }
    @PostMapping("addTaskEvent/")
    public int populateTaskEventTable( @RequestParam UUID idTask,@RequestParam UUID idEvent){
        return taskService.populateTaskEventTable(idTask,idEvent);
    }
}
