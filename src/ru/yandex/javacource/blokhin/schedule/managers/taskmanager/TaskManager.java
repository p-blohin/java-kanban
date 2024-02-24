package ru.yandex.javacource.blokhin.schedule.managers.taskmanager;

import ru.yandex.javacource.blokhin.schedule.task.*;

import java.util.ArrayList;

public interface TaskManager {

    // ----------------------------------------------------------------
    // блок по добавления объектов
    // ----------------------------------------------------------------

    /// Добавление таска
    int addNewTask(Task task);

    /// Добавление эпика
    int addNewEpic(Epic epic);

    /// Добавление сабтаска и привязка к эпику
    Integer addNewSubtask(Subtask subtask);

    // ----------------------------------------------------------------
    // блок по вывода всех объектов
    // ----------------------------------------------------------------

    /// Вывод тасков
    ArrayList<Task> getTasks();

    /// Вывод эпиков
    ArrayList<Epic> getEpics();

    /// Вывод сабтасков
    ArrayList<Subtask> getSubtasks();

    /// Вывод всех сабтасков для эпика
    ArrayList<Subtask> getEpicSubtasks(int epicId);

    // ----------------------------------------------------------------
    // блок по вывода объекта по id
    // ----------------------------------------------------------------

    /// Ввывод таска по id
    Task getTask(int id);

    /// Вывод эпика по id
    Epic getEpic(int id);

    /// Вывод сабтаска по id
    Subtask getSubtask(int id);

    // ----------------------------------------------------------------
    // блок по обновлению объекта
    // ----------------------------------------------------------------

    /// Обновление таска
    void updateTask(Task task);

    /// Обновление эпика
    void updateEpic(Epic epic);

    /// Обновление сабтаска
    void updateSubtask(Subtask subtask);

    // ----------------------------------------------------------------
    // блок по удалению всех объектов
    // ----------------------------------------------------------------

    /// Удаление всех тасков
    void deleteTasks();

    /// Удаление всех эпиков
    void deleteEpics();

    /// Удаление всех сабтасков
    void deleteSubtasks();

    // ----------------------------------------------------------------
    // блок по удалению объекта по id
    // ----------------------------------------------------------------

    /// Удаление таска по id
    void deleteTaskById(int id);

    /// Удаление эпика по id
    void deleteEpicById(int id);

    /// Удаление сабтаска по id
    void deleteSubtaskById(int id);

}