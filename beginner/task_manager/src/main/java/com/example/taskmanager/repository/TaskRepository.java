package com.example.taskmanager.repository;

import com.example.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByCompletedOrderByCreatedAtDesc(boolean completed);
    List<Task> findAllByOrderByCreatedAtDesc();
    
    // Advanced query methods
    List<Task> findByCategory(Category category);
    List<Task> findByPriority(Priority priority);
    List<Task> findByDueDateBefore(LocalDateTime date);
    List<Task> findByDueDateBetween(LocalDateTime start, LocalDateTime end);
    
    @Query("SELECT t FROM Task t WHERE t.title LIKE %:query% OR t.description LIKE %:query%")
    List<Task> searchTasks(@Param("query") String query);
    
    List<Task> findByCompletedAndCategory(boolean completed, Category category);
    List<Task> findByPriorityAndDueDateBefore(Priority priority, LocalDateTime date);
    
    @Query("SELECT t FROM Task t WHERE t.dueDate < CURRENT_TIMESTAMP AND t.completed = false")
    List<Task> findOverdueTasks();
    
    @Query("SELECT t FROM Task t WHERE t.tags LIKE %:tag%")
    List<Task> findByTag(@Param("tag") String tag);
}
