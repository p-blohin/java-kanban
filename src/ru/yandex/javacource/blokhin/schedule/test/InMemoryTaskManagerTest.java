package ru.yandex.javacource.blokhin.schedule.test;

import org.junit.jupiter.api.Test;
import ru.yandex.javacource.blokhin.schedule.managers.*;
import ru.yandex.javacource.blokhin.schedule.managers.taskmanager.TaskManager;
import ru.yandex.javacource.blokhin.schedule.task.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {

    // ----------------------------------------------------------------
    // Тест на добавление объектов
    // ----------------------------------------------------------------

    // Добавление нового таска
    @Test
    void addNewTask() {
        TaskManager taskManager = Managers.getDefault();

        Task task = new Task();
        task.setName("Тест - название таска");
        task.setDescription("Тест - описание таска");

        int savedTaskId = taskManager.addNewTask(task);
        Task savedTask = taskManager.getTask(savedTaskId);

        Task testTask = new Task("Тест - название таска", "Тест - описание таска", 1);

        assertNotNull(savedTask, "Таск не был сохранён.");
        assertEquals(testTask, savedTask, "Таск сохранён неверно.");
    }

    // Добавление нового эпика
    @Test
    void addNewEpic() {
        TaskManager taskManager = Managers.getDefault();

        Epic epic = new Epic();
        epic.setName("Тест - название эпика");
        epic.setDescription("Тест - описание эпика");
        ArrayList<Integer> subtasks = new ArrayList<>();
        subtasks.add(2);
        subtasks.add(3);
        epic.setSubtasksId(subtasks);

        int savedEpicId = taskManager.addNewEpic(epic);
        Epic savedEpic = taskManager.getEpic(savedEpicId);

        ArrayList<Integer> testSubtasks = new ArrayList<>();
        testSubtasks.add(2);
        testSubtasks.add(3);
        Epic testEpic = new Epic("Тест - название эпика", "Тест - описание эпика", 1, testSubtasks);

        assertNotNull(savedEpic, "Эпик не был сохранён.");
        assertEquals(testEpic, savedEpic, "Эпик сохранён неверно.");
    }

    // Добавление нового сабтаска
    @Test
    void addNewSubtask() {
        TaskManager taskManager = Managers.getDefault();

        Epic epic = new Epic();
        epic.setName("Тест - название эпика");
        epic.setDescription("Тест - описание эпика");
        ArrayList<Integer> subtasks = new ArrayList<>();
        subtasks.add(2);
        epic.setSubtasksId(subtasks);

        Subtask subtask = new Subtask();
        subtask.setName("Тест - название сабтаска");
        subtask.setDescription("Тест - описание сабтаска");
        subtask.setEpicId(1);

        taskManager.addNewEpic(epic);
        int savedSubtaskId = taskManager.addNewSubtask(subtask);
        Subtask savedSubtask = taskManager.getSubtask(savedSubtaskId);

        Subtask testSubtask = new Subtask("Тест - название сабтаска", "Тест - описание сабтаска", 2, 1);

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

        Task task1 = new Task();
        task1.setName("Тест - название таска №1");
        task1.setDescription("Тест - описание таска №1");

        Task task2 = new Task();
        task2.setName("Тест - название таска №2");
        task2.setDescription("Тест - описание таска №2");

        taskManager.addNewTask(task1);
        taskManager.addNewTask(task2);

        Task testTask1 = new Task("Тест - название таска №1", "Тест - описание таска №1", 1);
        Task testTask2 = new Task("Тест - название таска №2", "Тест - описание таска №2", 2);

        ArrayList<Task> testTasks = new ArrayList<>();
        testTasks.add(testTask1);
        testTasks.add(testTask2);

        ArrayList<Task> tasks = taskManager.getTasks();

        assertNotNull(tasks, "Список тасков не найден.");
        assertEquals(testTasks, tasks, "Списки тасков не совпадают, ошибка в сохранении.");
    }

    /// Вывод эпиков
    @Test
    void getEpics() {
        TaskManager taskManager = Managers.getDefault();

        Epic epic1 = new Epic();
        epic1.setName("Тест - название эпика №1");
        epic1.setDescription("Тест - описание эпика №1");
        ArrayList<Integer> subtasks1 = new ArrayList<>();
        subtasks1.add(3);
        epic1.setSubtasksId(subtasks1);

        Epic epic2 = new Epic();
        epic2.setName("Тест - название эпика №2");
        epic2.setDescription("Тест - описание эпика №2");
        ArrayList<Integer> subtasks2 = new ArrayList<>();
        subtasks2.add(4);
        epic2.setSubtasksId(subtasks2);

        taskManager.addNewEpic(epic1);
        taskManager.addNewEpic(epic2);

        ArrayList<Integer> subtasksTest1 = new ArrayList<>();
        subtasksTest1.add(3);
        ArrayList<Integer> subtasksTest2 = new ArrayList<>();
        subtasksTest2.add(4);
        Epic testEpic1 = new Epic("Тест - название эпика №1", "Тест - описание эпика №1", 1, subtasksTest1);
        Epic testEpic2 = new Epic("Тест - название эпика №2", "Тест - описание эпика №2", 2, subtasksTest2);

        ArrayList<Epic> testEpics = new ArrayList<>();
        testEpics.add(testEpic1);
        testEpics.add(testEpic2);

        ArrayList<Epic> epics = taskManager.getEpics();

        assertNotNull(epics, "Список эпиков не найден.");
        assertEquals(testEpics, epics, "Списки эпиков не совпадают, ошибка в сохранении.");
    }

    /// Вывод сабтасков
    @Test
    void getSubtasks() {
        TaskManager taskManager = Managers.getDefault();

        Epic epic = new Epic();
        epic.setName("Тест - название эпика");
        epic.setDescription("Тест - описание эпика");
        ArrayList<Integer> subtasksToAdd = new ArrayList<>();
        subtasksToAdd.add(2);
        subtasksToAdd.add(3);
        epic.setSubtasksId(subtasksToAdd);

        Subtask subtask1 = new Subtask();
        subtask1.setName("Тест - название сабтаска №1");
        subtask1.setDescription("Тест - описание сабтаска №1");
        subtask1.setEpicId(1);

        Subtask subtask2 = new Subtask();
        subtask2.setName("Тест - название сабтаска №2");
        subtask2.setDescription("Тест - описание сабтаска №2");
        subtask2.setEpicId(1);

        taskManager.addNewEpic(epic);
        taskManager.addNewSubtask(subtask1);
        taskManager.addNewSubtask(subtask2);

        Subtask testSubtask1 = new Subtask("Тест - название сабтаска №1", "Тест - описание сабтаска №1", 2, 1);
        Subtask testSubtask2 = new Subtask("Тест - название сабтаска №2", "Тест - описание сабтаска №2", 3, 1);

        ArrayList<Subtask> testSubtasks = new ArrayList<>();
        testSubtasks.add(testSubtask1);
        testSubtasks.add(testSubtask2);

        ArrayList<Subtask> subtasks = taskManager.getSubtasks();

        assertNotNull(subtasks, "Список сабтасков не найден.");
        assertEquals(testSubtasks, subtasks, "Списки сабтасков не совпадают, ошибка в сохранении.");
    }

    /// Вывод всех сабтасков для эпика
    @Test
    void getEpicSubtasks() {
        TaskManager taskManager = Managers.getDefault();

        Epic epic = new Epic();
        epic.setName("Тест - название эпика");
        epic.setDescription("Тест - описание эпика");

        Subtask subtask1 = new Subtask();
        subtask1.setName("Тест - название сабтаска №1");
        subtask1.setDescription("Тест - описание сабтаска №1");
        subtask1.setEpicId(1);

        taskManager.addNewEpic(epic);
        taskManager.addNewSubtask(subtask1);

        ArrayList<Subtask> savedSubtasks = taskManager.getEpicSubtasks(1);

        Subtask testSubtask1 = new Subtask("Тест - название сабтаска №1", "Тест - описание сабтаска №1", 2, 1);

        ArrayList<Subtask> testSubtasks = new ArrayList<>();
        testSubtasks.add(testSubtask1);

        assertNotNull(savedSubtasks, "Для выбранного эпика сабтасков нет.");
        assertEquals(1, savedSubtasks.size(), "Кол-во привязанных сабтасков не совпадают.");
        assertEquals(testSubtasks, savedSubtasks, "Списки привязанных сабтасков не совпадают.");
    }

    // ----------------------------------------------------------------
    // блок вывода объекта по id
    // ----------------------------------------------------------------

    /// Ввывод таска по id
    @Test
    void getTask() {
        TaskManager taskManager = Managers.getDefault();

        Task task = new Task();
        task.setName("Тест - название таска");
        task.setDescription("Тест - описание таска");

        int id = taskManager.addNewTask(task);
        Task savedTask = taskManager.getTask(id);

        Task testTask = new Task("Тест - название таска", "Тест - описание таска", 1);

        assertNotNull(savedTask, "Таск не был найден.");
        assertEquals(testTask, savedTask, "Полученный таск не совпадает ожидаемым объектом.");
    }

    /// Вывод эпика по id
    @Test
    void getEpic() {
        TaskManager taskManager = Managers.getDefault();

        Epic epic = new Epic();
        epic.setName("Тест - название эпика");
        epic.setDescription("Тест - описание эпика");
        ArrayList<Integer> subtasks = new ArrayList<>();
        subtasks.add(2);
        subtasks.add(3);
        epic.setSubtasksId(subtasks);

        int id = taskManager.addNewEpic(epic);
        Epic savedEpic = taskManager.getEpic(id);

        ArrayList<Integer> testSubtasks = new ArrayList<>();
        testSubtasks.add(2);
        testSubtasks.add(3);
        Epic testEpic = new Epic("Тест - название эпика", "Тест - описание эпика", 1, testSubtasks);

        assertNotNull(savedEpic, "Эпик не был найден.");
        assertEquals(testEpic, savedEpic, "Полученный эпик не совпадает ожидаемым объектом.");
    }

    /// Вывод сабтаска по id
    @Test
    void getSubtask() {
        TaskManager taskManager = Managers.getDefault();

        Epic epic = new Epic();
        epic.setName("Тест - название эпика");
        epic.setDescription("Тест - описание эпика");
        ArrayList<Integer> subtasks = new ArrayList<>();
        subtasks.add(2);
        epic.setSubtasksId(subtasks);

        Subtask subtask = new Subtask();
        subtask.setName("Тест - название сабтаска");
        subtask.setDescription("Тест - описание сабтаска");
        subtask.setEpicId(1);

        taskManager.addNewEpic(epic);
        int savedSubtaskId = taskManager.addNewSubtask(subtask);
        Subtask savedSubtask = taskManager.getSubtask(savedSubtaskId);

        Subtask testSubtask = new Subtask("Тест - название сабтаска", "Тест - описание сабтаска", 2, 1);

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

        Task task = new Task();
        task.setName("Тест - название таска");
        task.setDescription("Тест - описание таска");

        int savedTaskId = taskManager.addNewTask(task);

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

        Epic epic = new Epic();
        epic.setName("Тест - название эпика");
        epic.setDescription("Тест - описание эпика");
        ArrayList<Integer> subtasks = new ArrayList<>();
        subtasks.add(2);
        subtasks.add(3);
        epic.setSubtasksId(subtasks);

        int savedEpicId = taskManager.addNewEpic(epic);

        Epic newEpic = new Epic();
        newEpic.setName("Тест - название обновленного эпика");
        newEpic.setDescription("Тест - описание обновленного эпика");
        ArrayList<Integer> newSubtasks = new ArrayList<>();
        subtasks.add(4);
        subtasks.add(5);
        newEpic.setSubtasksId(newSubtasks);

        Epic updatedEpic = new Epic("Тест - название обновленного эпика", "Тест - описание обновленного эпика", 1, newSubtasks);

        taskManager.updateEpic(updatedEpic);

        Epic testEpic = taskManager.getEpic(savedEpicId);

        assertNotNull(testEpic, "Эпик не найден.");
        assertEquals(updatedEpic, testEpic, "Эпик не обновился.");
    }

    /// Обновление сабтаска
    @Test
    void updateSubtask() {
        TaskManager taskManager = Managers.getDefault();

        Epic epic = new Epic();
        epic.setName("Тест - название эпика");
        epic.setDescription("Тест - описание эпика");
        ArrayList<Integer> subtasks = new ArrayList<>();
        subtasks.add(2);
        epic.setSubtasksId(subtasks);

        Subtask subtask = new Subtask();
        subtask.setName("Тест - название сабтаска");
        subtask.setDescription("Тест - описание сабтаска");
        subtask.setEpicId(1);

        taskManager.addNewEpic(epic);
        int savedSubtaskId = taskManager.addNewSubtask(subtask);

        Subtask newSubtask = new Subtask();
        newSubtask.setName("Тест - название обновленного сабтаска");
        newSubtask.setDescription("Тест - описание обновленного сабтаска");
        newSubtask.setEpicId(1);

        Subtask updatedSubtask = new Subtask("Тест - название обновленного сабтаска", "Тест - описание обновленного сабтаска", 2, 1);

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

        Task task1 = new Task();
        task1.setName("Тест - название таска №1");
        task1.setDescription("Тест - описание таска №1");

        Task task2 = new Task();
        task2.setName("Тест - название таска №2");
        task2.setDescription("Тест - описание таска №2");

        taskManager.addNewTask(task1);
        taskManager.addNewTask(task2);

        ArrayList<Task> tasks = taskManager.getTasks();
        assertEquals(2, tasks.size());

        taskManager.deleteTasks();

        ArrayList<Task> testTasks = taskManager.getTasks();
        assertTrue(testTasks.isEmpty(), "Удаление тасков не произошло.");
    }

    /// Удаление всех эпиков
    @Test
    void deleteEpics() {
        TaskManager taskManager = Managers.getDefault();

        Epic epic1 = new Epic();
        epic1.setName("Тест - название эпика №1");
        epic1.setDescription("Тест - описание эпика №1");
        ArrayList<Integer> subtasks1 = new ArrayList<>();
        subtasks1.add(3);
        epic1.setSubtasksId(subtasks1);

        Epic epic2 = new Epic();
        epic2.setName("Тест - название эпика №2");
        epic2.setDescription("Тест - описание эпика №2");
        ArrayList<Integer> subtasks2 = new ArrayList<>();
        subtasks2.add(4);
        epic2.setSubtasksId(subtasks2);

        taskManager.addNewEpic(epic1);
        taskManager.addNewEpic(epic2);

        ArrayList<Epic> epics = taskManager.getEpics();
        assertEquals(2, epics.size());

        taskManager.deleteEpics();

        ArrayList<Epic> testEpics = taskManager.getEpics();
        assertTrue(testEpics.isEmpty(), "Удаление эпиков не произошло.");
    }

    /// Удаление всех сабтасков
    @Test
    void deleteSubtasks() {
        TaskManager taskManager = Managers.getDefault();

        Epic epic = new Epic();
        epic.setName("Тест - название эпика");
        epic.setDescription("Тест - описание эпика");
        ArrayList<Integer> subtasksToAdd = new ArrayList<>();
        subtasksToAdd.add(2);
        subtasksToAdd.add(3);
        epic.setSubtasksId(subtasksToAdd);

        Subtask subtask1 = new Subtask();
        subtask1.setName("Тест - название сабтаска №1");
        subtask1.setDescription("Тест - описание сабтаска №1");
        subtask1.setEpicId(1);

        Subtask subtask2 = new Subtask();
        subtask2.setName("Тест - название сабтаска №2");
        subtask2.setDescription("Тест - описание сабтаска №2");
        subtask2.setEpicId(1);

        taskManager.addNewEpic(epic);
        taskManager.addNewSubtask(subtask1);
        taskManager.addNewSubtask(subtask2);

        ArrayList<Subtask> subtasks = taskManager.getSubtasks();
        assertEquals(2, subtasks.size());

        taskManager.deleteSubtasks();

        ArrayList<Subtask> testSubtasks = taskManager.getSubtasks();
        assertTrue(testSubtasks.isEmpty(), "Удаление сабтасков не произошло.");
    }

    // ----------------------------------------------------------------
    // блок удаления объекта по id
    // ----------------------------------------------------------------

    /// Удаление таска по id
    @Test
    void deleteTaskById() {
        TaskManager taskManager = Managers.getDefault();

        Task task1 = new Task();
        task1.setName("Тест - название таска №1");
        task1.setDescription("Тест - описание таска №1");

        Task task2 = new Task();
        task2.setName("Тест - название таска №2");
        task2.setDescription("Тест - описание таска №2");

        Task task3 = new Task();
        task3.setName("Тест - название таска №3");
        task3.setDescription("Тест - описание таска №3");

        taskManager.addNewTask(task1);
        int idToDelete = taskManager.addNewTask(task2);
        taskManager.addNewTask(task3);

        ArrayList<Task> tasks = taskManager.getTasks();
        assertEquals(3, tasks.size());

        taskManager.deleteTaskById(idToDelete);

        ArrayList<Task> tasksAfterDelete = taskManager.getTasks();
        assertFalse(tasksAfterDelete.contains(idToDelete), "Удаление передаваемого таска не прошло.");
    }

    /// Удаление эпика по id
    @Test
    void deleteEpicById() {
        TaskManager taskManager = Managers.getDefault();

        Epic epic1 = new Epic();
        epic1.setName("Тест - название эпика №1");
        epic1.setDescription("Тест - описание эпика №1");
        ArrayList<Integer> subtasks1 = new ArrayList<>();
        subtasks1.add(4);
        epic1.setSubtasksId(subtasks1);

        Epic epic2 = new Epic();
        epic2.setName("Тест - название эпика №2");
        epic2.setDescription("Тест - описание эпика №2");
        ArrayList<Integer> subtasks2 = new ArrayList<>();
        subtasks2.add(5);
        epic2.setSubtasksId(subtasks2);

        Epic epic3 = new Epic();
        epic3.setName("Тест - название эпика №3");
        epic3.setDescription("Тест - описание эпика №3");
        ArrayList<Integer> subtasks3 = new ArrayList<>();
        subtasks3.add(6);
        epic3.setSubtasksId(subtasks3);

        taskManager.addNewEpic(epic1);
        int idToDelete = taskManager.addNewEpic(epic2);
        taskManager.addNewEpic(epic3);

        ArrayList<Epic> epics = taskManager.getEpics();
        assertEquals(3, epics.size());

        taskManager.deleteEpicById(idToDelete);

        ArrayList<Epic> epicsAfterDelete = taskManager.getEpics();
        assertFalse(epicsAfterDelete.contains(idToDelete), "Удаление передаваемого эпика не прошло.");
    }

    /// Удаление сабтаска по id
    @Test
    void deleteSubtaskById() {
        TaskManager taskManager = Managers.getDefault();

        Epic epic = new Epic();
        epic.setName("Тест - название эпика №1");
        epic.setDescription("Тест - описание эпика №1");
        ArrayList<Integer> subtasksToAdd = new ArrayList<>();
        subtasksToAdd.add(2);
        subtasksToAdd.add(3);
        subtasksToAdd.add(4);
        epic.setSubtasksId(subtasksToAdd);

        Subtask subtask1 = new Subtask();
        subtask1.setName("Тест - название сабтаска №1");
        subtask1.setDescription("Тест - описание сабтаска №1");
        subtask1.setEpicId(1);

        Subtask subtask2 = new Subtask();
        subtask2.setName("Тест - название сабтаска №2");
        subtask2.setDescription("Тест - описание сабтаска №2");
        subtask2.setEpicId(1);

        Subtask subtask3 = new Subtask();
        subtask3.setName("Тест - название сабтаска №3");
        subtask3.setDescription("Тест - описание сабтаска №3");
        subtask3.setEpicId(1);

        taskManager.addNewEpic(epic);
        taskManager.addNewSubtask(subtask1);
        int idToDelete = taskManager.addNewSubtask(subtask2);
        taskManager.addNewSubtask(subtask3);

        ArrayList<Subtask> subtasks = taskManager.getSubtasks();
        assertEquals(3, subtasks.size());

        taskManager.deleteSubtaskById(idToDelete);

        ArrayList<Subtask> subtasksAfterDelete = taskManager.getSubtasks();
        assertFalse(subtasksAfterDelete.contains(idToDelete), "Удаление передаваемого сабтаска не прошло.");
    }
}