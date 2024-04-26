package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TaskTest")
class TaskTest {
    @Test
    @DisplayName("should be the same when id is the same")
    public void shouldEqualsWithSameId() {
        Task task1 = new Task(1, "task", "descr");
        Task task2 = new Task(1, "task", "descr");
        assertEquals(task1, task2);
    }
}