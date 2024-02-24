package ru.yandex.javacource.blokhin.schedule.managers.taskmanager;

import ru.yandex.javacource.blokhin.schedule.managers.historymanager.HistoryManager;
import ru.yandex.javacource.blokhin.schedule.task.*;

import java.util.ArrayList;
import java.util.HashMap;

public class InMemoryTaskManager implements TaskManager {

    private int idCount;
    HistoryManager historyManager;
    private static HashMap<Integer, Task> tasks;
    private static HashMap<Integer, Epic> epics;
    private static HashMap<Integer, Subtask> subtasks;

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
    public int addNewTask(Task task) {
        final int id = ++idCount;
        task.setId(id);
        tasks.put(id, task);
        System.out.println("Таск добавлен: " + task);
        return id;
    }

    /// Добавление эпика
    @Override
    public int addNewEpic(Epic epic) {
        final int id = ++idCount;
        epic.setId(id);
        epics.put(id, epic);
        System.out.println("Эпик добавлен: " + epic);
        return id;
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
        subtask.setId(id);
        subtasks.put(id, subtask);
        epic.getSubtasksId().add(subtask.getId());
        updateEpicStatus(epicId);
        System.out.println("Сабтаск добавлен к эпику id: " + epic.getId() + " - " + subtask);
        return id;
    }

    // ----------------------------------------------------------------
    // блок вывода всех объектов
    // ----------------------------------------------------------------

    /// Вывод тасков
    @Override
    public ArrayList<Task> getTasks() {
        ArrayList<Task> taskToPrint = new ArrayList<>(tasks.values());
        System.out.println(taskToPrint);
        return taskToPrint;
    }

    /// Вывод эпиков
    @Override
    public ArrayList<Epic> getEpics() {
        ArrayList<Epic> epicToPrint = new ArrayList<>(epics.values());
        System.out.println(epicToPrint);
        return epicToPrint;
    }

    /// Вывод сабтасков
    @Override
    public ArrayList<Subtask> getSubtasks() {
        ArrayList<Subtask> subtasksToPrint = new ArrayList<>(subtasks.values());
        System.out.println(subtasksToPrint);
        return subtasksToPrint;
    }

    /// Вывод всех сабтасков для эпика
    @Override
    public ArrayList<Subtask> getEpicSubtasks(int epicId) {
        ArrayList<Subtask> tasks = new ArrayList<>();
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
    public Task getTask(int id) {
        Task task = tasks.get(id);
        System.out.println(task);
        historyManager.add(task);
        return task;
    }

    /// Вывод эпика по id
    @Override
    public Epic getEpic(int id) {
        Epic epic = epics.get(id);
        System.out.println(epic);
        historyManager.add(epic);
        return epics.get(id);
    }

    /// Вывод сабтаска по id
    @Override
    public Subtask getSubtask(int id) {
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
        historyManager.add(task);
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
        historyManager.add(epic);
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
        historyManager.add(subtask);
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
    public void deleteTaskById(int id) {
        tasks.remove(id);
        System.out.println("Таск с id " + id + " удалён.");
    }

    /// Удаление эпика по id
    @Override
    public void deleteEpicById(int id) {
        ArrayList<Integer> subtasksToRemove = epics.get(id).getSubtasksId();
        for (Integer removeSubtask : subtasksToRemove) {
            subtasks.remove(removeSubtask);
        }
        epics.remove(id);
        System.out.println("Эпик с id " + id + " и его сабтаски удалёны.");
    }

    /// Удаление сабтаска по id
    @Override
    public void deleteSubtaskById(int id) {
        Subtask subtask = subtasks.remove(id);
        if (subtask == null) {
            return;
        }
        Epic epic = epics.get(subtask.getEpicId());
        epic.removeSubtask(id);
        updateEpicStatus(epic.getId());
        System.out.println("Сабтаск с id " + id + " удалён.");
    }

    // ----------------------------------------------------------------
    // блок private методов
    // ----------------------------------------------------------------

    /// Обновление эпика на статус
    private void updateEpicStatus(int epicId) {
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