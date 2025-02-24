package org.example.nfjavarecap04;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    /* REQUESTS FROM BROWSER DEV TOOLS
    METHOD      PATH                        PARAMETER
    GET:        /api/todo                   -
    POST:       /api/todo                   {"description":"Aufgabe","status":"OPEN"}
    GET:        /api/todo/{id}              -
    PUT:        /api/todo/{id}              {"id":"67bca3a4c1bcd22fea24d346","description":"Aufgabe","status":"IN_PROGRESS"}

    /api/todo/undefined = /api/todo/{id} --> Implement ID
    STATUS ENUM?

     */

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("todo")
    public Task saveTask(@RequestBody Task newTask) {
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

}
