-- filepath: /task_manager/src/main/resources/data.sql
INSERT INTO task (title, description, completed, created_at) 
VALUES 
('Complete Spring Boot Tutorial', 'Learn about Spring Boot basics and create a sample application', false, CURRENT_TIMESTAMP),
('Write Unit Tests', 'Create unit tests for the TaskManager application', false, CURRENT_TIMESTAMP),
('Setup Development Environment', 'Install required tools and configure IDE', true, CURRENT_TIMESTAMP - 1);