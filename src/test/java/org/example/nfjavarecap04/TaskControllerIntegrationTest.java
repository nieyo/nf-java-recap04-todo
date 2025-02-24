package org.example.nfjavarecap04;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        taskRepository.deleteAll();

        Task existingTask = new Task("456", "Aufgabe", TaskStatus.OPEN);
        taskRepository.save(existingTask);
    }

    @Test
    @DirtiesContext
    void saveTask_whenNew() throws Exception {
        String expected = """
        {
            "description":"Aufgabe",
            "status":"OPEN"
        }
        """;

        mockMvc.perform(post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expected))
                .andExpect(status().isOk())
                .andExpect(content().json(expected))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").isNotEmpty());
    }

    @Test
    @DirtiesContext
    void saveTask_whenUpdate() throws Exception {
        String existingId = "456";
        String existingTask = """
        {
            "id":"456",
            "description":"Aufgabe",
            "status":"OPEN"
        }
        """;


        mockMvc.perform(put("/api/todo/{id}", existingId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(existingTask))
                .andExpect(status().isOk())
                .andExpect(content().json(existingTask))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").isNotEmpty());
    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                [
                    {
                        "id": "456",
                        "description": "Aufgabe",
                        "status": "OPEN"
                    }
                ]
                """));
    }

    @Test
    @DirtiesContext
    void findById() throws Exception {
        String id = "123";
        taskRepository.save(new Task(id, "Aufgabe", TaskStatus.OPEN));

        mockMvc.perform(get("/api/todo/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                {
                    "id":"123",
                    "description":"Aufgabe",
                    "status":"OPEN"
                }
                """));

    }

    @Test
    @DirtiesContext
    void deleteById() throws Exception {
        String id = "123";
        taskRepository.save(new Task(id, "Aufgabe", TaskStatus.OPEN));

        mockMvc.perform(delete("/api/todo/{id}", id))
                .andExpect(status().isOk());

    }
}