package ru.yandex.javacource.blokhin.schedule.managers.taskmanager;

import ru.yandex.javacource.blokhin.schedule.task.*;

import java.util.ArrayList;
import java.util.List;

public interface TaskManager {

    // ----------------------------------------------------------------
    // блок по добавления объектов
    // ----------------------------------------------------------------

    /// Добавление таска
    Integer addNewTask(Task task);

    /// Добавление эпика
    Integer addNewEpic(Epic epic);

    /// Добавление сабтаска и привязка к эпику
    Integer addNewSubtask(Subtask subtask);

    // ----------------------------------------------------------------
    // блок по вывода всех объектов
    // ----------------------------------------------------------------

    /// Вывод тасков
    List<Task> getTasks();

    /// Вывод эпиков
    List<Epic> getEpics();

    /// Вывод сабтасков
    List<Subtask> getSubtasks();

    /// Вывод всех сабтасков для эпика
    List<Subtask> getEpicSubtasks(Integer epicId);

    // ----------------------------------------------------------------
    // блок по вывода объекта по id
    // ----------------------------------------------------------------

    /// Ввывод таска по id
    Task getTask(Integer id);

    /// Вывод эпика по id
    Epic getEpic(Integer id);

    /// Вывод сабтаска по id
    Subtask getSubtask(Integer id);

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
    void deleteTaskById(Integer id);

    /// Удаление эпика по id
    void deleteEpicById(Integer id);

    /// Удаление сабтаска по id
    void deleteSubtaskById(Integer id);

    List<Task> getHistory();
}