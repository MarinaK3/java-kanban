package service;

import model.Epic;
import model.SubTask;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskManager {
    private HashMap<Integer, Task> tasks;
    private HashMap<Integer, Epic> epics;
    private HashMap<Integer, SubTask> subTasks;
    private int counter = 0;

    public TaskManager() {
        this.tasks = new HashMap<>();
        this.epics = new HashMap<>();
        this.subTasks = new HashMap<>();
    }

    private int generateId() {
        return ++counter;
    }

    public Task createTask(Task task) {
        task.setId(generateId());
        tasks.put(task.getId(), task);
        return task;
    }

    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public Task getTask(int id) {
        return tasks.get(id);
    }

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public void deleteTaskById(int id) {
        tasks.remove(id);
    }

    public void deleteAllTasks() {
        tasks.clear();
    }

    public Epic createEpic(Epic epic) {
        epic.setId(generateId());
        epic.updateStatus();
        epics.put(epic.getId(), epic);
        return epic;
    }

    public void updateEpic(Epic epic) {
        Epic saved = epics.get(epic.getId());
        if (saved == null) {
            return;
        }
        saved.setName(epic.getName());
        saved.setDescription(epic.getDescription());
    }

    public Epic getEpic(int id) {
        return epics.get(id);
    }

    public ArrayList<Task> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    public void deleteEpicById(int id) { //TODO: delete all subtasks?
        Epic epic = epics.get(id);
        ArrayList<SubTask> subTasksForEpic = getAllSubTasksForEpic(epic);
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

    public void deleteAllEpics() {
        deleteAllSubTasks();
        epics.clear();
    }

    public ArrayList<SubTask> getAllSubTasksForEpic(Epic epic) {
        return new ArrayList<>(epic.getSubTasks());
    }

    public SubTask createSubTask(SubTask subTask) {
        subTask.setId(generateId());
        subTasks.put(subTask.getId(), subTask);
        Epic epic = epics.get(subTask.getEpic().getId());
        epic.addSubTask(subTask);
        epic.updateStatus();
        return subTask;
    }

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

    public SubTask getSubTask(int id) {
        return subTasks.get(id);
    }

    public ArrayList<Task> getAllSubTasks() {
        return new ArrayList<>(subTasks.values());
    }

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

    public void deleteAllSubTasks() {
        subTasks.clear();
        for (Epic epic : epics.values()) {
            List<SubTask> subTasksForEpic = epic.getSubTasks();
            subTasksForEpic.clear();
            epic.updateStatus();
        }
    }
}
