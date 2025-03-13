package com.example.taskmanager.config;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepository;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void run(String... args) throws Exception {
        if (taskRepository.count() == 0) {
            // Add sample tasks
            Task task1 = new Task();
            task1.setTitle("Complete Spring Boot Tutorial");
            task1.setDescription("Learn about Spring Boot basics and create a sample application");
            task1.setCompleted(false);
            task1.setCreatedAt(LocalDateTime.now());

            Task task2 = new Task();
            task2.setTitle("Write Unit Tests");
            task2.setDescription("Create unit tests for the TaskManager application");
            task2.setCompleted(false);
            task2.setCreatedAt(LocalDateTime.now());

            Task task3 = new Task();
            task3.setTitle("Setup Development Environment");
            task3.setDescription("Install required tools and configure IDE");
            task3.setCompleted(true);
            task3.setCreatedAt(LocalDateTime.now().minusDays(1));

            // Save tasks to database
            taskRepository.save(task1);
            taskRepository.save(task2);
            taskRepository.save(task3);
        }
    }
}
