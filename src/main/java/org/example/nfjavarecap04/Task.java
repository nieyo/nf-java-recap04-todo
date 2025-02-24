package org.example.nfjavarecap04;

import org.springframework.data.annotation.Id;

import java.util.UUID;

public record Task(
        @Id String id,
        String description,
        TaskStatus status
) {
    public Task(String description, String status) {
        this(UUID.randomUUID().toString(), description, TaskStatus.valueOf(status));
    }
}
