package org.example.nfjavarecap04;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Task save(Task newTask) {
        if (newTask.id() == null || taskRepository.findById(newTask.id()).isPresent()) {
            return taskRepository.save(newTask);
        }

        throw new IllegalArgumentException("Task with ID " + newTask.id() + " does not exists");
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(String id) {
        return taskRepository.findById(id)
                .orElseThrow();
    }

    public void deleteById(String id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
        }
        else {
            throw new NoSuchElementException("Product with id: " + id + " not found!");
        }
    }
}
