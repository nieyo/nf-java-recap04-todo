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
    void saveTask(){
        Task expected = new Task("Neue Aufgabe", "OPEN");
        when(mockTaskRepository.save(expected)).thenReturn(expected);

        Task actual = taskService.save(expected);

        verify(mockTaskRepository).save(expected);
        assertEquals(expected.description(), actual.description());
        assertEquals(expected.status(), actual.status());
        assertNotNull(actual.id());
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