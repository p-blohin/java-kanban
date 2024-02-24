package ru.yandex.javacource.blokhin.schedule.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.javacource.blokhin.schedule.managers.*;
import ru.yandex.javacource.blokhin.schedule.managers.taskmanager.TaskManager;
import ru.yandex.javacource.blokhin.schedule.task.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {

    Task task, task1, task2;
    Epic epic, epic1, epic2;
    Subtask subtask, subtask1, subtask2,
            subtask3, subtask4, subtask5,
            subtask6, subtask7, subtask8;

    @BeforeEach
    void setUp() {
        task = new Task("Тест - название таска",
                "Тест - описание таска",
                1);
        task1 = new Task("Тест - название таска №1",
                "Тест - описание таска №1",
                2);
        task2 = new Task("Тест - название таска №2",
                "Тест - описание таска №2",
                3);

        epic = new Epic("Тест - название эпика",
                "Тест - описание эпика",
                4);
        epic1 = new Epic("Тест - название эпика №1",
                "Тест - описание эпика №1",
                5);
        epic2 = new Epic("Тест - название эпика №2",
                "Тест - описание эпика №2",
                6);

        subtask = new Subtask("Тест - название сабтаска",
                "Тест - описание сабтаска",
                7, 4);
        subtask1 = new Subtask("Тест - название сабтаска №1",
                "Тест - описание сабтаска №1",
                8, 4);
        subtask2 = new Subtask("Тест - название сабтаска №2",
                "Тест - описание сабтаска №2",
                9, 4);

        subtask3 = new Subtask("Тест - название сабтаска №3",
                "Тест - описание сабтаска №3",
                10, 5);
        subtask4 = new Subtask("Тест - название сабтаска №4",
                "Тест - описание сабтаска №4",
                11, 5);
        subtask5 = new Subtask("Тест - название сабтаска №5",
                "Тест - описание сабтаска №5",
                12, 5);

        subtask6 = new Subtask("Тест - название сабтаска №6",
                "Тест - описание сабтаска №6",
                13, 6);
        subtask7 = new Subtask("Тест - название сабтаска №7",
                "Тест - описание сабтаска №7",
                14, 6);
        subtask8 = new Subtask("Тест - название сабтаска №8",
                "Тест - описание сабтаска №8",
                15, 6);

    }

    // ----------------------------------------------------------------
    // Тест на добавление объектов
    // ----------------------------------------------------------------

    // Добавление нового таска
    @Test
    void addNewTask() {
        TaskManager taskManager = Managers.getDefault();

        Integer savedTaskId = taskManager.addNewTask(task);
        Task savedTask = taskManager.getTask(savedTaskId);

        Task testTask = new Task("Тест - название таска", "Тест - описание таска", 1);

        assertNotNull(savedTask, "Таск не был сохранён.");
        assertEquals(testTask, savedTask, "Таск сохранён неверно.");
    }

    // Добавление нового эпика
    @Test
    void addNewEpic() {
        TaskManager taskManager = Managers.getDefault();

        Integer savedEpicId = taskManager.addNewEpic(epic);
        Epic savedEpic = taskManager.getEpic(savedEpicId);

        ArrayList<Integer> testSubtasks = new ArrayList<>();
        testSubtasks.add(7);
        testSubtasks.add(8);
        Epic testEpic = new Epic("Тест - название эпика", "Тест - описание эпика", 4, testSubtasks);

        assertNotNull(savedEpic, "Эпик не был сохранён.");
        assertEquals(testEpic, savedEpic, "Эпик сохранён неверно.");
    }

    // Добавление нового сабтаска
    @Test
    void addNewSubtask() {
        TaskManager taskManager = Managers.getDefault();

        taskManager.addNewEpic(epic);
        Integer savedSubtaskId = taskManager.addNewSubtask(subtask);
        System.out.println("Сохранённое id - " + savedSubtaskId);
        Subtask savedSubtask = taskManager.getSubtask(savedSubtaskId);

        Subtask testSubtask = new Subtask("Тест - название сабтаска", "Тест - описание сабтаска", 7, 4);

        assertNotNull(savedSubtask, "Сабтаск не был сохранён.");
        assertEquals(testSubtask, savedSubtask, "Сабтаск сохранён неверно.");
    }

    // ----------------------------------------------------------------
    // блок вывода всех объектов
    // ----------------------------------------------------------------

    /// Вывод тасков
    @Test
    void getTasks() {
        TaskManager taskManager = Managers.getDefault();

        taskManager.addNewTask(task1);
        taskManager.addNewTask(task2);

        Task testTask1 = new Task("Тест - название таска №1", "Тест - описание таска №1", 2);
        Task testTask2 = new Task("Тест - название таска №2", "Тест - описание таска №2", 3);

        List<Task> testTasks = new ArrayList<>();
        testTasks.add(testTask1);
        testTasks.add(testTask2);

        List<Task> tasks = taskManager.getTasks();

        assertNotNull(tasks, "Список тасков не найден.");
        assertEquals(testTasks, tasks, "Списки тасков не совпадают, ошибка в сохранении.");
    }

    /// Вывод эпиков
    @Test
    void getEpics() {
        TaskManager taskManager = Managers.getDefault();

        taskManager.addNewEpic(epic1);
        taskManager.addNewEpic(epic2);

        Epic testEpic1 = new Epic("Тест - название эпика №1", "Тест - описание эпика №1", 5);
        Epic testEpic2 = new Epic("Тест - название эпика №2", "Тест - описание эпика №2", 6);

        List<Epic> testEpics = new ArrayList<>();
        testEpics.add(testEpic1);
        testEpics.add(testEpic2);

        List<Epic> epics = taskManager.getEpics();

        assertNotNull(epics, "Список эпиков не найден.");
        assertEquals(testEpics, epics, "Списки эпиков не совпадают, ошибка в сохранении.");
    }

    /// Вывод сабтасков
    @Test
    void getSubtasks() {
        TaskManager taskManager = Managers.getDefault();

        taskManager.addNewEpic(epic);
        taskManager.addNewSubtask(subtask1);
        taskManager.addNewSubtask(subtask2);

        Subtask testSubtask1 = new Subtask("Тест - название сабтаска №1", "Тест - описание сабтаска №1", 8, 4);
        Subtask testSubtask2 = new Subtask("Тест - название сабтаска №2", "Тест - описание сабтаска №2", 9, 4);

        List<Subtask> testSubtasks = new ArrayList<>();
        testSubtasks.add(testSubtask1);
        testSubtasks.add(testSubtask2);

        List<Subtask> subtasks = taskManager.getSubtasks();

        assertNotNull(subtasks, "Список сабтасков не найден.");
        assertEquals(testSubtasks, subtasks, "Списки сабтасков не совпадают, ошибка в сохранении.");
    }

    /// Вывод всех сабтасков для эпика
    @Test
    void getEpicSubtasks() {
        TaskManager taskManager = Managers.getDefault();

        taskManager.addNewEpic(epic);
        taskManager.addNewSubtask(subtask);
        taskManager.addNewSubtask(subtask1);
        taskManager.addNewSubtask(subtask2);

        List<Subtask> savedSubtasks = taskManager.getEpicSubtasks(4);

        Subtask testSubtask1 = new Subtask("Тест - название сабтаска", "Тест - описание сабтаска", 7, 4);
        Subtask testSubtask2 = new Subtask("Тест - название сабтаска №1", "Тест - описание сабтаска №1", 8, 4);
        Subtask testSubtask3 = new Subtask("Тест - название сабтаска №2", "Тест - описание сабтаска №2", 9, 4);

        List<Subtask> testSubtasks = new ArrayList<>();
        testSubtasks.add(testSubtask1);
        testSubtasks.add(testSubtask2);
        testSubtasks.add(testSubtask3);

        assertNotNull(savedSubtasks, "Для выбранного эпика сабтасков нет.");
        assertEquals(3, savedSubtasks.size(), "Кол-во привязанных сабтасков не совпадают.");
        assertEquals(testSubtasks, savedSubtasks, "Списки привязанных сабтасков не совпадают.");
    }

    // ----------------------------------------------------------------
    // блок вывода объекта по id
    // ----------------------------------------------------------------

    /// Ввывод таска по id
    @Test
    void getTask() {
        TaskManager taskManager = Managers.getDefault();

        Integer id = taskManager.addNewTask(task);
        Task savedTask = taskManager.getTask(id);

        Task testTask = new Task("Тест - название таска", "Тест - описание таска", 1);

        assertNotNull(savedTask, "Таск не был найден.");
        assertEquals(testTask, savedTask, "Полученный таск не совпадает ожидаемым объектом.");
    }

    /// Вывод эпика по id
    @Test
    void getEpic() {
        TaskManager taskManager = Managers.getDefault();

        Integer id = taskManager.addNewEpic(epic);
        Epic savedEpic = taskManager.getEpic(id);

        ArrayList<Integer> testSubtasks = new ArrayList<>();
        testSubtasks.add(7);
        testSubtasks.add(8);
        Epic testEpic = new Epic("Тест - название эпика", "Тест - описание эпика", 4, testSubtasks);

        assertNotNull(savedEpic, "Эпик не был найден.");
        assertEquals(testEpic, savedEpic, "Полученный эпик не совпадает ожидаемым объектом.");
    }

    /// Вывод сабтаска по id
    @Test
    void getSubtask() {
        TaskManager taskManager = Managers.getDefault();

        taskManager.addNewEpic(epic);
        Integer savedSubtaskId = taskManager.addNewSubtask(subtask);
        Subtask savedSubtask = taskManager.getSubtask(savedSubtaskId);

        Subtask testSubtask = new Subtask("Тест - название сабтаска", "Тест - описание сабтаска", 7, 4);

        assertNotNull(savedSubtask, "Сабтаск не был найден.");
        assertEquals(testSubtask, savedSubtask, "Полученный сабтаск не совпадает ожидаемым объектом.");
    }

    // ----------------------------------------------------------------
    // блок обновления объекта
    // ----------------------------------------------------------------

    /// Обновление таска
    @Test
    void updateTask() {
        TaskManager taskManager = Managers.getDefault();

        Integer savedTaskId = taskManager.addNewTask(task);

        Task newTask = new Task();
        newTask.setName("Тест - название обновленного таска");
        newTask.setDescription("Тест - описание обновленного таска");

        Task updatedTask = new Task("Тест - название обновленного таска", "Тест - описание обновленного таска", 1);

        taskManager.updateTask(updatedTask);

        Task testTask = taskManager.getTask(savedTaskId);

        assertNotNull(testTask, "Таск не найден.");
        assertEquals(updatedTask, testTask, "Таск не обновился.");
    }

    /// Обновление эпика
    @Test
    void updateEpic() {
        TaskManager taskManager = Managers.getDefault();

        Integer savedEpicId = taskManager.addNewEpic(epic);

        Epic newEpic = new Epic();
        newEpic.setName("Тест - название обновленного эпика");
        newEpic.setDescription("Тест - описание обновленного эпика");
        ArrayList<Integer> newSubtasks = new ArrayList<>();
        newSubtasks.add(8);
        newSubtasks.add(9);
        newEpic.setSubtasksId(newSubtasks);

        Epic updatedEpic = new Epic("Тест - название обновленного эпика", "Тест - описание обновленного эпика", 4, newSubtasks);

        taskManager.updateEpic(updatedEpic);

        Epic testEpic = taskManager.getEpic(savedEpicId);

        assertNotNull(testEpic, "Эпик не найден.");
        assertEquals(updatedEpic, testEpic, "Эпик не обновился.");
    }

    /// Обновление сабтаска
    @Test
    void updateSubtask() {
        TaskManager taskManager = Managers.getDefault();

        taskManager.addNewEpic(epic);
        Integer savedSubtaskId = taskManager.addNewSubtask(subtask);

        Subtask newSubtask = new Subtask();
        newSubtask.setName("Тест - название обновленного сабтаска");
        newSubtask.setDescription("Тест - описание обновленного сабтаска");
        newSubtask.setEpicId(4);

        Subtask updatedSubtask = new Subtask("Тест - название обновленного сабтаска", "Тест - описание обновленного сабтаска", 7, 4);

        taskManager.updateSubtask(updatedSubtask);

        Subtask testSubtask = taskManager.getSubtask(savedSubtaskId);

        assertNotNull(testSubtask, "Сабтаск не найден.");
        assertEquals(updatedSubtask, testSubtask, "Сабтаск не обновился.");
    }

    // ----------------------------------------------------------------
    // блок удаления всех объектов
    // ----------------------------------------------------------------

    /// Удаление всех тасков
    @Test
    void deleteTasks() {
        TaskManager taskManager = Managers.getDefault();

        taskManager.addNewTask(task1);
        taskManager.addNewTask(task2);

        List<Task> tasks = taskManager.getTasks();
        assertEquals(2, tasks.size());

        taskManager.deleteTasks();

        List<Task> testTasks = taskManager.getTasks();
        assertTrue(testTasks.isEmpty(), "Удаление тасков не произошло.");
    }

    /// Удаление всех эпиков
    @Test
    void deleteEpics() {
        TaskManager taskManager = Managers.getDefault();

        taskManager.addNewEpic(epic1);
        taskManager.addNewEpic(epic2);

        List<Epic> epics = taskManager.getEpics();
        assertEquals(2, epics.size());

        taskManager.deleteEpics();

        List<Epic> testEpics = taskManager.getEpics();
        assertTrue(testEpics.isEmpty(), "Удаление эпиков не произошло.");
    }

    /// Удаление всех сабтасков
    @Test
    void deleteSubtasks() {
        TaskManager taskManager = Managers.getDefault();

        taskManager.addNewEpic(epic);
        taskManager.addNewSubtask(subtask1);
        taskManager.addNewSubtask(subtask2);

        List<Subtask> subtasks = taskManager.getSubtasks();
        assertEquals(2, subtasks.size());

        taskManager.deleteSubtasks();

        List<Subtask> testSubtasks = taskManager.getSubtasks();
        assertTrue(testSubtasks.isEmpty(), "Удаление сабтасков не произошло.");
    }

    // ----------------------------------------------------------------
    // блок удаления объекта по id
    // ----------------------------------------------------------------

    /// Удаление таска по id
    @Test
    void deleteTaskById() {
        TaskManager taskManager = Managers.getDefault();

        taskManager.addNewTask(task);
        Integer idToDelete = taskManager.addNewTask(task1);
        taskManager.addNewTask(task2);

        List<Task> tasks = taskManager.getTasks();
        assertEquals(3, tasks.size());

        taskManager.deleteTaskById(idToDelete);

        List<Task> tasksAfterDelete = taskManager.getTasks();
        assertFalse(tasksAfterDelete.contains(idToDelete), "Удаление передаваемого таска не прошло.");
    }

    /// Удаление эпика по id
    @Test
    void deleteEpicById() {
        TaskManager taskManager = Managers.getDefault();

        taskManager.addNewEpic(epic);
        Integer idToDelete = taskManager.addNewEpic(epic1);
        taskManager.addNewEpic(epic2);

        List<Epic> epics = taskManager.getEpics();
        assertEquals(3, epics.size());

        taskManager.deleteEpicById(idToDelete);

        List<Epic> epicsAfterDelete = taskManager.getEpics();
        assertFalse(epicsAfterDelete.contains(idToDelete), "Удаление передаваемого эпика не прошло.");
    }

    /// Удаление сабтаска по id
    @Test
    void deleteSubtaskById() {
        TaskManager taskManager = Managers.getDefault();

        taskManager.addNewEpic(epic);
        taskManager.addNewSubtask(subtask);
        Integer idToDelete = taskManager.addNewSubtask(subtask1);
        taskManager.addNewSubtask(subtask2);

        List<Subtask> subtasks = taskManager.getSubtasks();
        assertEquals(3, subtasks.size());

        taskManager.deleteSubtaskById(idToDelete);

        List<Subtask> subtasksAfterDelete = taskManager.getSubtasks();
        assertFalse(subtasksAfterDelete.contains(idToDelete), "Удаление передаваемого сабтаска не прошло.");
    }
}