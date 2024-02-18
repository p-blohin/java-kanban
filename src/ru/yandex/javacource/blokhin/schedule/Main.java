package ru.yandex.javacource.blokhin.schedule;

import ru.yandex.javacource.blokhin.schedule.task.*;
import ru.yandex.javacource.blokhin.schedule.manager.TaskManager;

public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");

        TaskManager taskManager = new TaskManager();

        int task1 = taskManager.addNewTask(new Task("Приготовить еды",
                "Приготовить еды на обед")); // id 1
        int task2 = taskManager.addNewTask(new Task("Помыть пол",
                "Помыть пол на кухне")); // id 2
        int task3 = taskManager.addNewTask(new Task("Лечь спать",
                "Лечь спать пораньше")); // id 3

        int epic1 = taskManager.addNewEpic(new Epic("Дела мужчины",
                "Что должен сделать каждый мужчина в своей жизни")); // id 4

        int subtask1 = taskManager.addNewSubtask(new Subtask("Посадить дерево",
                "Каждый мужчина должен посадить дерево", 4)); // id 5
        int subtask2 = taskManager.addNewSubtask(new Subtask("Построить дом",
                "Каждый мужчина должен построить дом", 4)); // id 6
        int subtask3 = taskManager.addNewSubtask(new Subtask("Вырастить сына",
                "Каждый мужчина должен вырастить сына", 4)); // id 7

        int epic2 = taskManager.addNewEpic(new Epic("Дела русской женщины",
                "Что должна сделать каждая русская женщина")); // id 8

        int subtask4 = taskManager.addNewSubtask(new Subtask("Зайти в горящую избу",
                "Каждая русская женщина в горящую избу войдёт", 8)); // id 9
        int subtask5 = taskManager.addNewSubtask(new Subtask("Остановить коня на скаку",
                "Каждая женщина коня на скаку остановит", 8)); // id 10

        System.out.println("************************");

        taskManager.getTasks();
        taskManager.getEpics();
        taskManager.getSubtasks();

        System.out.println("************************");

        taskManager.getTask(1);
        taskManager.getEpic(8);
        taskManager.getSubtask(9);

        System.out.println("************************");

        taskManager.deleteTaskById(2);
        taskManager.getTasks();
        System.out.println(taskManager.getEpicSubtasks(4));
        taskManager.deleteEpicById(8);
        taskManager.deleteSubtaskById(10);

        System.out.println("************************");

        taskManager.updateTask(new Task("Приготовить еды",
                "Приготовить еды на ужин", TaskStatus.DONE, 1));
        taskManager.getTask(1);
        taskManager.updateSubtask(new Subtask("Построить дом",
                "Каждый мужчина должен построить дом", TaskStatus.DONE, 6, 4));
        taskManager.updateSubtask(new Subtask("Вырастить сына",
                "Каждый мужчина должен вырастить сына", TaskStatus.DONE, 7, 4));
        //taskManager.updateEpicStatus(4);

        System.out.println("************************");

        taskManager.updateEpic(new Epic("Дела мужчины",
                "Что должен сделать каждый мужчина в своей жизни (если очень захочет)", 4));

        System.out.println("************************");

        taskManager.deleteTasks();
        taskManager.deleteEpics();
        taskManager.deleteSubtasks();
    }
}
