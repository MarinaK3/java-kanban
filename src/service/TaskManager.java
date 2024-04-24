package service;

import model.Epic;
import model.SubTask;
import model.Task;

import java.util.ArrayList;

public interface TaskManager {
    Task createTask(Task task);

    void updateTask(Task task);

    Task getTask(int id);

    ArrayList<Task> getAllTasks();

    void deleteTaskById(int id);

    void deleteAllTasks();

    Epic createEpic(Epic epic);

    void updateEpic(Epic epic);

    Epic getEpic(int id);

    ArrayList<Task> getAllEpics();

    void deleteEpicById(int id);

    void deleteAllEpics();

    ArrayList<SubTask> getAllSubTasksForEpic(Epic epic);

    SubTask createSubTask(SubTask subTask);

    void updateSubTask(SubTask subTask);

    SubTask getSubTask(int id);

    ArrayList<Task> getAllSubTasks();

    void deleteSubTaskById(int id);

    void deleteAllSubTasks();
}
