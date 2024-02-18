package ru.yandex.javacource.blokhin.schedule.manager;

import ru.yandex.javacource.blokhin.schedule.task.Epic;
import ru.yandex.javacource.blokhin.schedule.task.Subtask;
import ru.yandex.javacource.blokhin.schedule.task.Task;
import ru.yandex.javacource.blokhin.schedule.task.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private int idCount = 0;

    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();
    private HashMap<Integer, Subtask> subtasks = new HashMap<>();

    // ----------------------------------------------------------------
    // блок по CRUD для тасков
    // ----------------------------------------------------------------

    /// Добавление таска
    public int addNewTask(Task task) {
        final int id = ++idCount;
        task.setId(id);
        tasks.put(id, task);
        System.out.println("Таск добавлен: " + task);
        return id;
    }

    /// Вывод тасков
    public ArrayList<Task> getTasks() {
        ArrayList<Task> taskToPrint = new ArrayList<>(tasks.values());
        System.out.println(taskToPrint);
        return taskToPrint;
    }

    /// Ввывод таска по id
    public Task getTask(int id) {
        System.out.println(tasks.get(id));
        return tasks.get(id);
    }

    /// Апдейт таска
    public void updateTask(Task task) {
        final int id = task.getId();
        final Task savedTask = tasks.get(id);
        if (savedTask == null) {
            return;
        }
        tasks.put(id, task);
        System.out.println("Таск с id " + id + " изменён.");
    }

    /// Удаление всех тасков
    public void deleteTasks() {
        tasks.clear();
        System.out.println("Все таски удалены.");
    }

    /// Удаление таска по id
    public void deleteTaskById(int id) {
        tasks.remove(id);
        System.out.println("Такс с id " + id + " удалён.");
    }

    // ----------------------------------------------------------------
    // блок по CRUD для эпиков
    // ----------------------------------------------------------------

    /// Добавление эпика
    public int addNewEpic(Epic epic) {
        final int id = ++idCount;
        epic.setId(id);
        epics.put(id, epic);
        System.out.println("Эпик добавлен: " + epic);
        return id;
    }

    /// Вывод эпика
    public ArrayList<Epic> getEpics() {
        ArrayList<Epic> epicToPrint = new ArrayList<>(epics.values());
        System.out.println(epicToPrint);
        return epicToPrint;
    }

    /// Вывод эпика по id
    public Epic getEpic(int id) {
        System.out.println(epics.get(id));
        return epics.get(id);
    }

    /// Апдейт эпика
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

    /// Удаление всех эпиков
    public void deleteEpics() {
        epics.clear();
        deleteSubtasks();
        System.out.println("Все эпики удалены.");
    }

    /// Удаление эпика по id
    public void deleteEpicById(int id) {
        ArrayList<Integer> subtasksToRemove = epics.get(id).getSubtasksId();
        for (Integer removeSubtask : subtasksToRemove) {
            subtasks.remove(removeSubtask);
        }
        epics.remove(id);
        System.out.println("Эпик с id " + id + " и его сабтаски удалёны.");
    }

    /// Обновление эпика на статус
    /*public void updateEpicStatus(int epicId) {
        Epic epic = epics.get(epicId);
        ArrayList<Integer> subtasksToCheck = epic.getSubtasksId();
        ArrayList<TaskStatus> subtasksStatus = new ArrayList<>();

        for (Subtask subtask : subtasks.values()) {
            for (Integer subtasksId : subtasksToCheck) {
                if (subtasksId.equals(subtask.getId())) {
                    subtasksStatus.add(subtask.getStatus());
                }
            }
        }

        if (!(subtasksStatus.isEmpty())) {
            if (!(subtasksStatus.contains(TaskStatus.NEW) &&
                    !(subtasksStatus.contains(TaskStatus.IN_PROGRESS)))) {
                epic.setStatus(TaskStatus.DONE);
                System.out.println("Эпик с id " + epicId + " выполнен.");
            } else {
                System.out.println("Эпик с id " + epicId + " ещё в работе.");
            }
        }
    } */

    /// Альтернативный вариант
    private void updateEpicStatus(int epicId) {
        Epic epic = epics.get(epicId);
        ArrayList<Integer> subs = epic.getSubtasksId();
        if (subs.isEmpty()) {
            epic.setStatus(TaskStatus.NEW);
            return;
        }
        TaskStatus status = null;
        for (int id : subs) {
            final Subtask subtask = subtasks.get(id);
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

    /// Вывод всех сабтасков для эпика

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
    // блок по CRUD для сабтасков
    // ----------------------------------------------------------------

    /// Добавление сабтаска и привязка к эпику
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
        System.out.println("Сабтаск добавлен: " + subtask);
        return id;
    }

    /// Вывод сабтаска
    public ArrayList<Subtask> getSubtasks() {
        ArrayList<Subtask> subtasksToPrint = new ArrayList<>(subtasks.values());
        System.out.println(subtasksToPrint);
        return subtasksToPrint;
    }

    /// Вывод сабтаска по id
    public Subtask getSubtask(int id) {
        System.out.println(subtasks.get(id));
        return subtasks.get(id);
    }

    /// Апдейт сабтаска
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

    /// Удаление всех сабтасков
    public void deleteSubtasks() {
        for (Epic epic : epics.values()) {
            epic.getSubtasksId().clear();
            updateEpicStatus(epic.getId());
        }
        subtasks.clear();
        System.out.println("Все сабтаски удалены.");
    }

    /// Удаление сабтаска по id
    public void deleteSubtaskById(int id) {
        Subtask subtask = subtasks.remove(id);
        if (subtask == null) {
            return;
        }
        Epic epic = epics.get(subtask.getEpicId());
        epic.getSubtasksId().remove(id);
        updateEpicStatus(epic.getId());
        System.out.println("Сабтаск с id " + id + " удалён.");
    }

}