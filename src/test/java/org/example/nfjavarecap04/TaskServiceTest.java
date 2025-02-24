package org.example.nfjavarecap04;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    // MOCKING REPO AND SET UP SERVICE
    private final TaskRepository mockTaskRepository = mock(TaskRepository.class);
    TaskService taskService = new TaskService(mockTaskRepository);

    // UNIT TESTS
    @Test
    void saveTask_whenNew(){
        String generatedId = "123";
        Task inputTask = new Task(null, "Aufgabe", TaskStatus.OPEN);
        Task expectedTask = new Task(generatedId, "Aufgabe", TaskStatus.OPEN);

        when(mockTaskRepository.save(inputTask)).thenReturn(expectedTask);

        Task actual = taskService.save(inputTask);

        verify(mockTaskRepository).save(inputTask);
        assertEquals(expectedTask.description(), actual.description());
        assertEquals(expectedTask.status(), actual.status());
        assertNotNull(actual.id());
        assertEquals(generatedId, actual.id());
    }

    @Test
    void saveTask_whenUpdate(){
        String existingId = "456";
        Task existingTask = new Task(existingId, "Alte Aufgabe", TaskStatus.OPEN);
        Task updatedTask = new Task(existingId, "Aktualisierte Aufgabe", TaskStatus.IN_PROGRESS);

        when(mockTaskRepository.findById(existingId)).thenReturn(Optional.of(existingTask));
        when(mockTaskRepository.save(updatedTask)).thenReturn(updatedTask);

        Task actual = taskService.save(updatedTask);

        verify(mockTaskRepository).findById(existingId);
        verify(mockTaskRepository).save(updatedTask);
        assertEquals(existingId, actual.id());
        assertEquals(updatedTask.description(), actual.description());
        assertEquals(updatedTask.status(), actual.status());
    }

    @Test
    void findAll_ReturnsList() {
        List<Task> expected = new ArrayList<>(Arrays.asList(
                new Task("1", "OPEN"),
                new Task("2", "OPEN"),
                new Task("3", "OPEN")
        ));
        when(mockTaskRepository.findAll()).thenReturn(expected);

        List<Task> actual = taskService.findAll();

        verify(mockTaskRepository).findAll();
        assertEquals(expected, actual);
    }

    @Test
    void findById(){
        String id = "123";
        Task expected = new Task(id,"Neue Aufgabe", TaskStatus.OPEN);
        when(mockTaskRepository.findById(id)).thenReturn(Optional.of(expected));

        Task actual = taskService.findById(id);

        verify(mockTaskRepository).findById(id);
        assertEquals(expected, actual);
    }

}