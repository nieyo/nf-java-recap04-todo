package org.example.nfjavarecap04;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

    /* REQUESTS FROM BROWSER DEV TOOLS
    METHOD      PATH            PARAMETER
    GET:        /api/todo       -
    POST:       /api/todo       {"description":"neue Aufgabe","status":"OPEN"}
     */

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
//    private final TaskMapper taskMapper;

}
