package org.example.nfjavarecap04;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("todo")
    public Task saveTask(@RequestBody Task newTask) {
        return taskService.save(newTask);
    }

    @PutMapping("todo/{id}")
    public Task updateTask(@RequestBody Task newTask) {
        return taskService.save(newTask);
    }

    @GetMapping("todo")
    public List<Task> findAll() {
        return taskService.findAll();
    }

    @GetMapping("todo/{id}")
    public Task findAll(@PathVariable String id) {
        return taskService.findById(id);
    }

    @DeleteMapping("todo/{id}")
    public void deleteById(@PathVariable String id) {
        taskService.deleteById(id);
    }


}
