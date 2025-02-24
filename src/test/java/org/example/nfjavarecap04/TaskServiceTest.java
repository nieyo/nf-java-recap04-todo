package org.example.nfjavarecap04;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    // MOCKING REPO AND SET UP SERVICE
    private final TaskRepository mockTaskRepository = mock(TaskRepository.class);
    TaskService taskService = new TaskService(mockTaskRepository);

    // UNIT TESTS
    @Test
    void saveTask(){
        Task expected = new Task("Neue Aufgabe", "OPEN");
        when(mockTaskRepository.save(expected)).thenReturn(expected);

        Task actual = taskService.save(expected);

        verify(mockTaskRepository).save(expected);
        assertEquals(expected, actual);
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

}