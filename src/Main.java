import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");

        HashMap<Integer, Task> taskList = new HashMap<>();

        TaskManager taskManager = new TaskManager();

        Task task1 = taskManager.createTask(new Task("Приготовить еды", "Приготовить еды на обед", 1));
        Task task2 = taskManager.createTask(new Task("Помыть пол", "Помыть пол на кухне", 2));
        Task task3 = taskManager.createTask(new Task("Лечь спать", "Лечь спать пораньше", 3));

        Epic epic1 = taskManager.createEpic(new Epic("Дела мужчины",
                "Что должен сделать каждый мужчина в своей жизни", 1));

        Subtask subtask1 = taskManager.createSubtask(new Subtask("Посадить дерево",
                "Каждый мужчина должен посадить дерево", 0));
        Subtask subtask2 = taskManager.createSubtask(new Subtask("Построить дом",
                "Каждый мужчина должен построить дом", 0));
        Subtask subtask3 = taskManager.createSubtask(new Subtask("Вырастить сына",
                "Каждый мужчина должен вырастить сына", 0));
        taskManager.addSubtaskToEpic(epic1, subtask1);
        taskManager.addSubtaskToEpic(epic1, subtask2);
        taskManager.addSubtaskToEpic(epic1, subtask3);

        Epic epic2 = taskManager.createEpic(new Epic("Дела русской женщины",
                "Что должна сделать каждая русская женщина", 0));

        Subtask subtask4 = taskManager.createSubtask(new Subtask("Зайти в горящую избу",
                "Каждая русская женщина в горящую избу войдёт", 0));
        Subtask subtask5 = taskManager.createSubtask(new Subtask("Остановить коня на скаку",
                "Каждая женщина коня на скаку остановит", 0));
        taskManager.addSubtaskToEpic(epic2, subtask4);
        taskManager.addSubtaskToEpic(epic2, subtask5);

        taskManager.addToTaskList(taskList, task1); // id 0
        taskManager.addToTaskList(taskList, task2); // id 1
        taskManager.addToTaskList(taskList, task3); // id 2
        taskManager.addToTaskList(taskList, subtask1); // id 4
        taskManager.addToTaskList(taskList, subtask2); // id 5
        taskManager.addToTaskList(taskList, subtask3); // id 6
        taskManager.addToTaskList(taskList, epic1); // id 3
        taskManager.addToTaskList(taskList, subtask4); // id 8
        taskManager.addToTaskList(taskList, subtask5); // id 9
        taskManager.addToTaskList(taskList, epic2); // id 7

        Subtask subtask4update = taskManager.createSubtask(new Subtask("Зайти в горящую избу",
                "Каждая русская женщина в горящую избу войдёт", 0, TaskStatus.DONE));
        Subtask subtask5update = taskManager.createSubtask(new Subtask("Остановить коня на скаку",
                "Каждая женщина коня на скаку остановит", 0, TaskStatus.DONE));
        taskManager.updateSubtask(taskList, 8, subtask4update, epic2);
        taskManager.printOnlySubtasks(taskList);
        taskManager.printOnlyEpicById(taskList, 7);
        taskManager.updateSubtask(taskList, 9, subtask5update, epic2);
        taskManager.printOnlySubtasks(taskList);
        taskManager.printOnlyEpicById(taskList, 7);
    }
}
