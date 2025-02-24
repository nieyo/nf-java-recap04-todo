package org.example.nfjavarecap04;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    /* REQUESTS FROM BROWSER DEV TOOLS
    METHOD      PATH                        PARAMETER
    GET:        /api/todo                   -
    POST:       /api/todo                   {"description":"neue Aufgabe","status":"OPEN"}
    GET:        /api/todo/{id}              -
    PUT:        /api/todo/{id}              {"description":"Testing","status":"IN_PROGRESS"}

    /api/todo/undefined = /api/todo/{id} --> Implement ID
    STATUS ENUM?

     */

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
//    private final TaskMapper taskMapper;

    @PostMapping("todo")
    public Task saveTask(@RequestBody Task newTask) {
        return taskService.save(newTask);
    }

    @GetMapping("todo")
    public List<Task> findAll() {
        return taskService.findAll();
    }
}
