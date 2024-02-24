package ru.yandex.javacource.blokhin.schedule.managers.taskmanager;

import ru.yandex.javacource.blokhin.schedule.managers.historymanager.*;
import ru.yandex.javacource.blokhin.schedule.task.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTaskManager implements TaskManager {

    private int idCount;
    private final HistoryManager historyManager;
    private final Map<Integer, Task> tasks;
    private final Map<Integer, Epic> epics;
    private final Map<Integer, Subtask> subtasks;

    public InMemoryTaskManager(HistoryManager historyManager) {
        idCount = 0;
        this.historyManager = historyManager;
        tasks = new HashMap<>();
        epics = new HashMap<>();
        subtasks = new HashMap<>();
    }

    // ----------------------------------------------------------------
    // блок добавления объектов
    // ----------------------------------------------------------------

    /// Добавление таска
    @Override
    public Integer addNewTask(Task task) {
        final int id = ++idCount;
        if (task.getId() == null) {
            task.setId(id);
            tasks.put(id, task);
        } else {
            tasks.put(task.getId(), task);
        }
        System.out.println("Таск добавлен: " + task);
        return task.getId();
    }

    /// Добавление эпика
    @Override
    public Integer addNewEpic(Epic epic) {
        final int id = ++idCount;
        if (epic.getId() == null) {
            epic.setId(id);
            epics.put(id, epic);
        } else {
            epics.put(epic.getId(), epic);
        }
        System.out.println("Эпик добавлен: " + epic);
        return epic.getId();
    }

    /// Добавление сабтаска и привязка к эпику
    @Override
    public Integer addNewSubtask(Subtask subtask) {
        int epicId = subtask.getEpicId();
        Epic epic = epics.get(epicId);
        if (epic == null) {
            return null;
        }
        int id = ++idCount;
        if (subtask.getId() == null) {
            subtask.setId(id);
            subtasks.put(id, subtask);
        } else {
            subtasks.put(subtask.getId(), subtask);
        }
        epic.getSubtasksId().add(subtask.getId());
        updateEpicStatus(epicId);
        System.out.println("Сабтаск добавлен к эпику id: " + epic.getId() + " - " + subtask);
        return subtask.getId();
    }

    // ----------------------------------------------------------------
    // блок вывода всех объектов
    // ----------------------------------------------------------------

    /// Вывод тасков
    @Override
    public List<Task> getTasks() {
        List<Task> taskToPrint = new ArrayList<>(tasks.values());
        System.out.println(taskToPrint);
        return taskToPrint;
    }

    /// Вывод эпиков
    @Override
    public List<Epic> getEpics() {
        List<Epic> epicToPrint = new ArrayList<>(epics.values());
        System.out.println(epicToPrint);
        return epicToPrint;
    }

    /// Вывод сабтасков
    @Override
    public List<Subtask> getSubtasks() {
        List<Subtask> subtasksToPrint = new ArrayList<>(subtasks.values());
        System.out.println(subtasksToPrint);
        return subtasksToPrint;
    }

    /// Вывод всех сабтасков для эпика
    @Override
    public List<Subtask> getEpicSubtasks(Integer epicId) {
        List<Subtask> tasks = new ArrayList<>();
        Epic epic = epics.get(epicId);
        if (epic == null) {
            return null;
        }
        for (int id : epic.getSubtasksId()) {
            tasks.add(subtasks.get(id));
        }
        return tasks;
    }

    // ----------------------------------------------------------------
    // блок вывода объекта по id
    // ----------------------------------------------------------------

    /// Ввывод таска по id
    @Override
    public Task getTask(Integer id) {
        Task task = tasks.get(id);
        System.out.println(task);
        historyManager.add(task);
        return task;
    }

    /// Вывод эпика по id
    @Override
    public Epic getEpic(Integer id) {
        Epic epic = epics.get(id);
        System.out.println(epic);
        historyManager.add(epic);
        return epics.get(id);
    }

    /// Вывод сабтаска по id
    @Override
    public Subtask getSubtask(Integer id) {
        Subtask subtask = subtasks.get(id);
        System.out.println(subtask);
        historyManager.add(subtask);
        return subtasks.get(id);
    }

    // ----------------------------------------------------------------
    // блок обновления объекта
    // ----------------------------------------------------------------

    /// Обновление таска
    @Override
    public void updateTask(Task task) {
        final int id = task.getId();
        final Task savedTask = tasks.get(id);
        if (savedTask == null) {
            return;
        }
        tasks.put(id, task);
        System.out.println("Таск с id " + id + " изменён.");
    }

    /// Обновление эпика
    @Override
    public void updateEpic(Epic epic) {
        final Epic savedEpic = epics.get(epic.getId());
        savedEpic.setName(epic.getName());
        savedEpic.setDescription(epic.getDescription());
        if (savedEpic == null) {
            return;
        }
        epics.put(savedEpic.getId(), savedEpic);
        System.out.println("Эпик с id " + savedEpic.getId() + " изменён.");
    }

    /// Обновление сабтаска
    @Override
    public void updateSubtask(Subtask subtask) {
        int id = subtask.getId();
        int epicId = subtask.getEpicId();
        Subtask savedSubtask = subtasks.get(id);
        if (savedSubtask == null) {
            return;
        }
        Epic epic = epics.get(epicId);
        if (epic == null) {
            return;
        }
        subtasks.put(id, subtask);
        updateEpicStatus(epicId);
    }

    // ----------------------------------------------------------------
    // блок удаления всех объектов
    // ----------------------------------------------------------------

    /// Удаление всех тасков
    @Override
    public void deleteTasks() {
        tasks.clear();
        System.out.println("Все таски удалены.");
    }

    /// Удаление всех эпиков
    @Override
    public void deleteEpics() {
        epics.clear();
        deleteSubtasks();
        System.out.println("Все эпики удалены.");
    }

    /// Удаление всех сабтасков
    @Override
    public void deleteSubtasks() {
        for (Epic epic : epics.values()) {
            epic.getSubtasksId().clear();
            updateEpicStatus(epic.getId());
        }
        subtasks.clear();
        System.out.println("Все сабтаски удалены.");
    }

    // ----------------------------------------------------------------
    // блок удаления объекта по id
    // ----------------------------------------------------------------

    /// Удаление таска по id
    @Override
    public void deleteTaskById(Integer id) {
        tasks.remove(id);
        System.out.println("Таск с id " + id + " удалён.");
    }

    /// Удаление эпика по id
    @Override
    public void deleteEpicById(Integer id) {
        List<Integer> subtasksToRemove = epics.get(id).getSubtasksId();
        for (Integer removeSubtask : subtasksToRemove) {
            subtasks.remove(removeSubtask);
        }
        epics.remove(id);
        System.out.println("Эпик с id " + id + " и его сабтаски удалёны.");
    }

    /// Удаление сабтаска по id
    @Override
    public void deleteSubtaskById(Integer id) {
        Subtask subtask = subtasks.remove(id);
        if (subtask == null) {
            return;
        }
        Epic epic = epics.get(subtask.getEpicId());
        epic.removeSubtask(id);
        updateEpicStatus(epic.getId());
        System.out.println("Сабтаск с id " + id + " удалён.");
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }

    // ----------------------------------------------------------------
    // блок private методов
    // ----------------------------------------------------------------

    /// Обновление эпика на статус
    private void updateEpicStatus(Integer epicId) {
        Epic epic = epics.get(epicId);
        if (epic == null) {
            return;
        }
        ArrayList<Integer> subs = epic.getSubtasksId();
        if (subs.isEmpty()) {
            epic.setStatus(TaskStatus.NEW);
            return;
        }
        TaskStatus status = null;
        for (int id : subs) {
            final Subtask subtask = subtasks.get(id);
            if (subtask == null) {
                return;
            }
            if (status == null) {
                status = subtask.getStatus();
                continue;
            }

            if (status.equals(subtask.getStatus()) &&
                    !status.equals(TaskStatus.IN_PROGRESS)) {
                continue;
            }
            epic.setStatus(TaskStatus.IN_PROGRESS);
            return;
        }
        epic.setStatus(status);
    }

}