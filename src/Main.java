import model.Status;
import model.Task;
import service.TaskManager;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");

        TaskManager taskManager = new TaskManager();
        Task task1 = taskManager.createTask(new Task("Cook", Status.NEW, "Cooking dinner"));
        System.out.println("create task1: " + task1);

        Task task2 = taskManager.createTask(new Task("Shop", Status.NEW, "Buy grocery"));
        System.out.println("create task2: " + task2);

        Task task3 = taskManager.createTask(new Task("Clean", Status.NEW, "Wash windows"));
        System.out.println("create task3: " + task3);

        System.out.println("Get all tasks: " + taskManager.getAllTasks());

        Task taskFromManager = taskManager.getTask(task2.getId());

        System.out.println("Get task by Id: " + taskFromManager);

        taskFromManager.setName("ShopEdeka");
        taskManager.updateTask(taskFromManager);
        System.out.println("Task Updated " + taskFromManager);

        taskManager.deleteTaskById(task1.getId());
        System.out.println("Task1 deleted");

        System.out.println("Get all tasks: " + taskManager.getAllTasks());

        taskManager.deleteAllTasks();
        System.out.println("All tasks deleted");


        System.out.println("Get all tasks: " + taskManager.getAllTasks());
    }
}
