import model.Epic;
import model.Status;
import model.SubTask;
import model.Task;
import service.TaskManager;


public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");

        TaskManager taskManager = new TaskManager();
        //Tasks
        Task task1 = taskManager.createTask(new Task("Cook", "Cooking dinner"));
        System.out.println("create task1: " + task1);

        Task task2 = taskManager.createTask(new Task("Shop", "Buy grocery"));
        System.out.println("create task2: " + task2);

        System.out.println("Get all tasks: " + taskManager.getAllTasks());

        Task taskFromManager = taskManager.getTask(task2.getId());
        System.out.println("Get task by Id: " + taskFromManager);

        Task taskUpdated = new Task(task2.getId(), "Updated Shop Task", "Buy grocery");
        taskUpdated.setStatus(Status.IN_PROGRESS);
        taskManager.updateTask(taskUpdated);
        System.out.println("Task2 Updated " + taskUpdated);

        taskManager.deleteTaskById(task1.getId());
        System.out.println("Task1 deleted");

        System.out.println("Get all tasks: " + taskManager.getAllTasks());

        taskManager.deleteAllTasks();
        System.out.println("All tasks deleted");
        System.out.println("Get all tasks: " + taskManager.getAllTasks());

        //Epics
        Epic epic1 = taskManager.createEpic(new Epic("ToDoYandexInTime", "To do all Yandex tasks"));
        System.out.println("create epic1: " + epic1);

        Epic epic2 = taskManager.createEpic(new Epic("CleanHouse", "Clean all house"));
        System.out.println("create epic2: " + epic2);

        Epic epicFromManager = taskManager.getEpic(epic2.getId());
        epicFromManager.setName("CleanHouseUpdated");
        epicFromManager.setDescription("Clean all house updated");
        taskManager.updateEpic(epicFromManager);
        System.out.println("Epic Updated " + epicFromManager);

        System.out.println("Get epic by Id: " + taskManager.getEpic(epic1.getId()));

        System.out.println("Get all epics: " + taskManager.getAllEpics());

        //SubTasks
        Epic epic3 = taskManager.createEpic(new Epic("Relocation", "Relocate to other town"));
        System.out.println("create epic3: " + epic3);

        SubTask subTask1 = taskManager.createSubTask(new SubTask("Prepare docs", "Collect all documents", epic3));
        System.out.println("create subTask1: " + subTask1);
        System.out.println("check epic3 status after SubTask1 was created: " + epic3);

        subTask1.setStatus(Status.DONE);
        taskManager.updateSubTask(subTask1);
        System.out.println("updated subTask1: " + subTask1);
        System.out.println("check epic3 status after SubTask1 was updated: " + epic3);

        SubTask subTask2 = taskManager.createSubTask(new SubTask("Pack things", "Pack all things", epic3));
        System.out.println("create subTask2: " + subTask2);
        subTask2.setStatus(Status.IN_PROGRESS);
        taskManager.updateSubTask(subTask2);
        System.out.println("updated subTask2: " + subTask2);
        System.out.println("check epic3 status after SubTask2 was updated: " + epic3);


        System.out.println("Get subTask2 by Id: " + taskManager.getSubTask(subTask2.getId()));
        System.out.println("Get all subTasks: " + taskManager.getAllSubTasks());

        SubTask subTask3 = taskManager.createSubTask(new SubTask("Find new place", "Find new good place", epic3));
        System.out.println("Get all subTasks: " + taskManager.getAllSubTasks());
        System.out.println(" deleting SubTask1... ");
        taskManager.deleteSubTaskById(subTask1.getId());
        System.out.println("SubTask1 deleted");
        System.out.println("Get all subTasks after SubTask1 deleted: " + taskManager.getAllSubTasks());

        System.out.println("Get all epics: " + taskManager.getAllEpics());

        System.out.println("Deleting all epics...");
        taskManager.deleteAllEpics();
        System.out.println("Get all epics: " + taskManager.getAllEpics());
        System.out.println("Get all subTasks: " + taskManager.getAllSubTasks());
        System.out.println("Get all subTasks for epic3: " + taskManager.getAllSubTasksForEpic(epic3));
        System.out.println("Get all subTasks for epic2: " + taskManager.getAllSubTasksForEpic(epic2));
    }
}
