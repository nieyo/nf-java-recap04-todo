package org.example.nfjavarecap04;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Task save(Task newTask) {
        return taskRepository.save(newTask);
    }
}
