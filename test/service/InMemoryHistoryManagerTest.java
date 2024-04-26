package service;

import model.Epic;
import model.SubTask;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("InMemoryHistoryManagerTest")
class InMemoryHistoryManagerTest {
    InMemoryHistoryManager inMemoryHistoryManager;
    InMemoryTaskManager inMemoryTaskManager;
    Task task;
    Epic epic;
    SubTask subTask;

    @BeforeEach
    public void setUp() {
        inMemoryHistoryManager = new InMemoryHistoryManager();
        inMemoryTaskManager = new InMemoryTaskManager(inMemoryHistoryManager);
        task = inMemoryTaskManager.createTask(new Task("Cook", "Cooking dinner"));
        epic = inMemoryTaskManager.createEpic(new Epic("Relocation", "Relocate to other town"));
        subTask = inMemoryTaskManager.createSubTask(new SubTask("Prepare docs", "Collect all documents", epic));
    }

    @DisplayName("addTest")
    @Test
    public void shouldAddAndFindByIdTest() {
        inMemoryHistoryManager.add(task);
        inMemoryHistoryManager.add(epic);
        inMemoryHistoryManager.add(subTask);

        assertEquals(3, inMemoryHistoryManager.getAll().size());
        assertEquals(task, inMemoryHistoryManager.getAll().get(0));
        assertEquals(epic, inMemoryHistoryManager.getAll().get(1));
        assertEquals(subTask, inMemoryHistoryManager.getAll().get(2));

        assertEquals(task.getId(), inMemoryHistoryManager.getAll().get(0).getId());
        assertEquals(epic.getId(), inMemoryHistoryManager.getAll().get(1).getId());
        assertEquals(subTask.getId(), inMemoryHistoryManager.getAll().get(2).getId());
    }

    @DisplayName("getAllTest")
    @Test
    void getAllTest() {
        inMemoryHistoryManager.add(task);
        inMemoryHistoryManager.add(epic);
        inMemoryHistoryManager.add(subTask);

        assertEquals(3, inMemoryHistoryManager.getAll().size());
    }

    @DisplayName("shouldNotAddNullTaskTest")
    @Test
    public void shouldNotAddNullTaskTest() {
        inMemoryHistoryManager.add(null);
        inMemoryHistoryManager.add(epic);
        inMemoryHistoryManager.add(subTask);

        assertEquals(2, inMemoryHistoryManager.getAll().size());
        assertEquals(epic, inMemoryHistoryManager.getAll().get(0));
        assertEquals(subTask, inMemoryHistoryManager.getAll().get(1));
    }
}