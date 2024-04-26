package service;

import model.Epic;
import model.Status;
import model.SubTask;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("InMemoryTaskManagerTest")
class InMemoryTaskManagerTest {
    InMemoryTaskManager inMemoryTaskManager;
    private Task task1;
    private Task task2;
    private Epic epic;
    private SubTask subTask1;
    private SubTask subTask2;

    @BeforeEach
    public void setUp() {
        EmptyHistoryManager emptyHistoryManager = new EmptyHistoryManager();
        inMemoryTaskManager = new InMemoryTaskManager(emptyHistoryManager);
        task1 = inMemoryTaskManager.createTask(new Task("Cook", "Cooking dinner"));
        task2 = inMemoryTaskManager.createTask(new Task("Shop", "Buy grocery"));
        epic = inMemoryTaskManager.createEpic(new Epic("Relocation", "Relocate to other town"));
        subTask1 = inMemoryTaskManager.createSubTask(new SubTask("Prepare docs", "Collect all documents", epic));
        subTask2 = inMemoryTaskManager.createSubTask(new SubTask("Pack things", "Pack all things", epic));
    }

    @Test
    @DisplayName("createTaskTest")
    public void shouldCreateTaskTest() {
        assertTrue(task1.getId() > 0);
        assertEquals("Cook", inMemoryTaskManager.getTask(task1.getId()).getName());
        assertEquals("Cooking dinner", inMemoryTaskManager.getTask(task1.getId()).getDescription());
    }

    @Test
    @DisplayName("updateTask")
    public void shouldUpdateTaskTest() {
        task1.setStatus(Status.IN_PROGRESS);
        task1.setName("Cook updated");

        inMemoryTaskManager.updateTask(task1);

        assertEquals("Cook updated", inMemoryTaskManager.getTask(task1.getId()).getName());
        assertEquals(Status.IN_PROGRESS, inMemoryTaskManager.getTask(task1.getId()).getStatus());
    }

    @Test
    @DisplayName("getTask")
    public void shouldGetTaskByIdTest() {
        Task retrievedTask = inMemoryTaskManager.getTask(task1.getId());

        assertEquals(retrievedTask, task1);
    }

    @Test
    @DisplayName("getAllTasks")
    public void shouldGetAllTasksTest() {
        List<Task> retrievedTasks = inMemoryTaskManager.getAllTasks();

        assertEquals(retrievedTasks.get(0), task1);
        assertEquals(retrievedTasks.get(1), task2);
    }

    @Test
    @DisplayName("deleteTaskById")
    public void shouldDeleteTaskByIdTest() {
        inMemoryTaskManager.deleteTaskById(task1.getId());

        assertNull(inMemoryTaskManager.getTask(task1.getId()));
    }

    @Test
    @DisplayName("deleteAllTasks")
    public void shouldDeleteAllTasksTest() {
        inMemoryTaskManager.deleteAllTasks();

        assertTrue(inMemoryTaskManager.getAllTasks().isEmpty());
    }

    @Test
    @DisplayName("createEpic")
    public void shouldCreateEpicTest() {
        assertTrue(epic.getId() > 0);
        assertEquals("Relocation", inMemoryTaskManager.getEpic(epic.getId()).getName());
        assertEquals("Relocate to other town", inMemoryTaskManager.getEpic(epic.getId()).getDescription());
    }

    @Test
    @DisplayName("updateEpic")
    public void shouldUpdateEpicTest() {
        epic.setName("Relocation Update");
        epic.setDescription("Updated description");

        inMemoryTaskManager.updateEpic(epic);

        assertEquals("Relocation Update", inMemoryTaskManager.getEpic(epic.getId()).getName());
        assertEquals("Updated description", inMemoryTaskManager.getEpic(epic.getId()).getDescription());
    }

    @Test
    @DisplayName("getEpic")
    public void shouldGetEpicByIdTest() {
        Epic retrievedEpic = inMemoryTaskManager.getEpic(epic.getId());

        assertEquals(retrievedEpic, epic);
    }

    @Test
    @DisplayName("getAllEpics")
    public void shouldGetAllEpicsTest() {
        List<Task> retrievedEpics = inMemoryTaskManager.getAllEpics();

        assertEquals(retrievedEpics.get(0), epic);
    }

    @Test
    @DisplayName("deleteEpicById")
    public void shouldDeleteEpicByIdAndAllSubTasksTest() {
        List<SubTask> subTasksList = epic.getSubTasks();

        inMemoryTaskManager.deleteEpicById(epic.getId());

        assertNull(inMemoryTaskManager.getEpic(epic.getId()));
        assertTrue(subTasksList.isEmpty());
    }

    @Test
    @DisplayName("deleteAllEpics")
    public void shouldDeleteAllEpicsTest() {
        inMemoryTaskManager.deleteAllEpics();

        assertTrue(inMemoryTaskManager.getAllSubTasks().isEmpty());
        assertTrue(inMemoryTaskManager.getAllEpics().isEmpty());
    }

    @Test
    @DisplayName("getAllSubTasksForEpic")
    public void shouldGetAllSubTasksForEpicTest() {
        List<SubTask> subTaskList = inMemoryTaskManager.getAllSubTasksForEpic(epic);

        assertEquals(subTask1, subTaskList.get(0));
        assertEquals(subTask2, subTaskList.get(1));
    }

    @Test
    @DisplayName("createSubTask")
    public void shouldCreateSubTaskTest() {
        assertTrue(subTask1.getId() > 0);
        assertEquals("Prepare docs", inMemoryTaskManager.getSubTask(subTask1.getId()).getName());
        assertEquals("Collect all documents", inMemoryTaskManager.getSubTask(subTask1.getId()).getDescription());
        assertEquals(epic, inMemoryTaskManager.getSubTask(subTask1.getId()).getEpic());
    }

    @Test
    @DisplayName("updateSubTask")
    public void shouldUpdateSubTaskTest() {
        subTask1.setName("Prepare docs Update");
        subTask1.setDescription("Updated collect all documents");

        inMemoryTaskManager.updateSubTask(subTask1);

        assertEquals("Prepare docs Update", inMemoryTaskManager.getSubTask(subTask1.getId()).getName());
        assertEquals("Updated collect all documents", inMemoryTaskManager.getSubTask(subTask1.getId()).getDescription());
    }

    @Test
    @DisplayName("getSubTask")
    public void shouldGetSubTaskByIdTest() {
        SubTask retrievedSubTask = inMemoryTaskManager.getSubTask(subTask1.getId());

        assertEquals(retrievedSubTask, subTask1);
    }

    @Test
    @DisplayName("getAllSubTasks")
    public void shouldGetAllSubTasksTest() {
        List<Task> retrievedSubTasks = inMemoryTaskManager.getAllSubTasks();

        assertEquals(retrievedSubTasks.get(0), subTask1);
        assertEquals(retrievedSubTasks.get(1), subTask2);
    }

    @Test
    @DisplayName("deleteSubTaskById")
    public void shouldDeleteSubTaskByIdTest() {
        inMemoryTaskManager.deleteSubTaskById(subTask1.getId());

        assertNull(inMemoryTaskManager.getSubTask(subTask1.getId()));
    }

    @Test
    @DisplayName("deleteAllSubTasks")
    public void shouldDeleteAllSubTasksTest() {
        inMemoryTaskManager.deleteAllSubTasks();

        assertTrue(inMemoryTaskManager.getAllSubTasks().isEmpty());
    }

    @Test
    @DisplayName("getHistoryTest")
    public void shouldReturnHistoryTest() {
        InMemoryHistoryManager inMemoryHistoryManager = new InMemoryHistoryManager();
        inMemoryTaskManager = new InMemoryTaskManager(inMemoryHistoryManager);
        task1 = inMemoryTaskManager.createTask(new Task("Cook", "Cooking dinner"));
        epic = inMemoryTaskManager.createEpic(new Epic("Relocation", "Relocate to other town"));
        subTask1 = inMemoryTaskManager.createSubTask(new SubTask("Prepare docs", "Collect all documents", epic));

        inMemoryTaskManager.getTask(task1.getId());
        inMemoryTaskManager.getEpic(epic.getId());
        inMemoryTaskManager.getSubTask(subTask1.getId());
        List<Task> historyList = inMemoryTaskManager.getHistory();

        assertTrue(historyList.size() == 3);
    }


    private static class EmptyHistoryManager implements HistoryManager {
        @Override
        public void add(Task task) {
        }

        @Override
        public List<Task> getAll() {
            return Collections.emptyList();
        }
    }
}

