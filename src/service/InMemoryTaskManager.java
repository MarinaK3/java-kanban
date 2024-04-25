package service;

import model.Epic;
import model.SubTask;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {
    private HashMap<Integer, Task> tasks;
    private HashMap<Integer, Epic> epics;
    private HashMap<Integer, SubTask> subTasks;
    private HistoryManager historyManager;
    private int counter = 0;

    public InMemoryTaskManager(HistoryManager historyManager) {
        this.historyManager = historyManager;
        this.tasks = new HashMap<>();
        this.epics = new HashMap<>();
        this.subTasks = new HashMap<>();
    }

    private int generateId() {
        return ++counter;
    }

    @Override
    public Task createTask(Task task) {
        task.setId(generateId());
        tasks.put(task.getId(), task);
        return task;
    }

    @Override
    public void updateTask(Task task) {
        Task savedTask = tasks.get(task.getId());
        if (savedTask == null) {
            return;
        }
        tasks.put(task.getId(), task);
    }

    @Override
    public Task getTask(int id) {
        historyManager.add(tasks.get(id));
        return tasks.get(id);

    }

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public void deleteTaskById(int id) {
        tasks.remove(id);
    }

    @Override
    public void deleteAllTasks() {
        tasks.clear();
    }

    @Override
    public Epic createEpic(Epic epic) {
        epic.setId(generateId());
        epic.updateStatus();
        epics.put(epic.getId(), epic);
        return epic;
    }

    @Override
    public void updateEpic(Epic epic) {
        Epic saved = epics.get(epic.getId());
        if (saved == null) {
            return;
        }
        saved.setName(epic.getName());
        saved.setDescription(epic.getDescription());
    }

    @Override
    public Epic getEpic(int id) {
        historyManager.add(epics.get(id));
        return epics.get(id);
    }

    @Override
    public List<Task> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public void deleteEpicById(int id) {
        Epic epic = epics.get(id);
        List<SubTask> subTasksForEpic = getAllSubTasksForEpic(epic);
        ArrayList<Integer> subTasksId = new ArrayList<>();
        for (SubTask subTask : subTasksForEpic) {
            subTasksId.add(subTask.getId());
        }
        for (Integer subTaskId : subTasksId) {
            subTasks.remove(subTaskId);
        }
        epic.getSubTasks().clear();
        epics.remove(id);
    }

    @Override
    public void deleteAllEpics() {
        deleteAllSubTasks();
        epics.clear();
    }

    @Override
    public List<SubTask> getAllSubTasksForEpic(Epic epic) {
        return new ArrayList<>(epic.getSubTasks());
    }

    @Override
    public SubTask createSubTask(SubTask subTask) {
        subTask.setId(generateId());
        subTasks.put(subTask.getId(), subTask);
        Epic epic = epics.get(subTask.getEpic().getId());
        epic.addSubTask(subTask);
        epic.updateStatus();
        return subTask;
    }

    @Override
    public void updateSubTask(SubTask subTask) {
        subTasks.put(subTask.getId(), subTask);
        Epic epic = epics.get(subTask.getEpic().getId());
        if (epic == null) {
            return;
        }
        List<SubTask> subTasksForEpic = epic.getSubTasks();
        for (int i = 0; i < subTasksForEpic.size(); i++) {
            if (subTasksForEpic.equals(subTask)) {
                subTasksForEpic.set(i, subTask);
                break;
            }
        }
        epic.updateStatus();
    }

    @Override
    public SubTask getSubTask(int id) {
        historyManager.add(subTasks.get(id));
        return subTasks.get(id);
    }

    @Override
    public List<Task> getAllSubTasks() {
        return new ArrayList<>(subTasks.values());
    }

    @Override
    public void deleteSubTaskById(int id) {
        SubTask subTask = subTasks.remove(id);
        Epic epic = epics.get(subTask.getEpic().getId());
        if (epic == null) {
            return;
        }
        List<SubTask> subTasksForEpic = epic.getSubTasks();
        for (int i = subTasksForEpic.size() - 1; i >= 0; i--) {
            if (subTasksForEpic.get(i).getId() == id) {
                subTasksForEpic.remove(i);
                break;
            }
        }
        epic.updateStatus();
    }

    @Override
    public void deleteAllSubTasks() {
        subTasks.clear();
        for (Epic epic : epics.values()) {
            List<SubTask> subTasksForEpic = epic.getSubTasks();
            subTasksForEpic.clear();
            epic.updateStatus();
        }
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getAll();
    }
}
