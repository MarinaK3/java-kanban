package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("EpicTest")
class EpicTest {
    @Test
    @DisplayName("should be the same its with copy")
    public void shouldEqualsWithCopy() {
        Epic epic = new Epic("name", "descr");
        Epic epic1 = new Epic("name", "descr");
        assertEquals(epic1.getName(), epic.getName(), "name should be the same");
        assertEquals(epic1.getDescription(), epic.getDescription(), "decr should be the same");
    }

    @Test
    @DisplayName("subTasks should be added")
    public void addSubTaskTest() {
        Epic epic = new Epic("Epic name", "Epic descr");
        SubTask subTask = new SubTask("subTask", "SubTask descr", epic);

        epic.addSubTask(subTask);

        assertTrue(!epic.getSubTasks().isEmpty());
        assertEquals("subTask", epic.getSubTasks().get(0).getName());
        assertEquals("SubTask descr", epic.getSubTasks().get(0).getDescription());
    }

    @Test
    @DisplayName("change status logic works correct ")
    public void updateStatusTest() {
        Epic epic = new Epic("Epic name", "Epic descr");
        SubTask subTask1 = new SubTask("subTask1", "SubTask descr1", epic);
        SubTask subTask2 = new SubTask("subTask2", "SubTask descr2", epic);

        epic.addSubTask(subTask1);
        epic.addSubTask(subTask2);
        epic.updateStatus();

        assertEquals(Status.NEW, epic.getStatus());

        subTask2.setStatus(Status.DONE);
        epic.updateStatus();
        assertEquals(Status.IN_PROGRESS, epic.getStatus());

        subTask1.setStatus(Status.DONE);
        epic.updateStatus();
        assertEquals(Status.DONE, epic.getStatus());
    }
}