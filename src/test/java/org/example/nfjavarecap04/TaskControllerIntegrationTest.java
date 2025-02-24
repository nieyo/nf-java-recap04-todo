package org.example.nfjavarecap04;

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

    @Test
    @DirtiesContext
    void saveTask() throws Exception {
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
    void findAll() throws Exception {
        mockMvc.perform(get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
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
}