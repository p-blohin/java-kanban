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
        final int id = epic.getId();
        final Epic savedEpic = epics.get(id);
        if (savedEpic == null) {
            return;
        }
        epics.put(id, epic);
        System.out.println("Эпик с id " + id + " изменён.");
    }

    /// Удаление всех эпиков
    public void deleteEpics() {
        epics.clear();
        System.out.println("Все эпики удалены.");
    }

    /// Удаление эпика по id
    public void deleteEpicById(int id) {
        ArrayList<Integer> subtasksToRemove = epics.get(id).getSubtasksId();
        for (Integer removeSubtask : subtasksToRemove) {
            deleteSubtaskById(removeSubtask);
        }
        epics.remove(id);
        System.out.println("Эпик с id " + id + " и его сабтаски удалёны.");
    }

    /// Проверка эпика на статус
    public TaskStatus epicProgressCheck(int epicId) {
        TaskStatus epicStatus = epics.get(epicId).getStatus();
        ArrayList<Integer> subtasksToCheck = epics.get(epicId).getSubtasksId();
        ArrayList<TaskStatus> subtasksStatus = new ArrayList<>();

        for (Subtask subtask : subtasks.values()) {
            for (Integer subtasksId : subtasksToCheck) {
                if (subtasksId.equals(subtask.getId())) {
                    subtasksStatus.add(subtask.getStatus());
                }
            }
        }

        if (!(subtasksStatus.contains(TaskStatus.NEW) &&
                !(subtasksStatus.contains(TaskStatus.IN_PROGRESS)))) {
            epicStatus = TaskStatus.DONE;
            System.out.println("Эпик с id " + epicId + " выполнен.");
        } else {
            System.out.println("Эпик с id " + epicId + " ещё в работе.");
        }
        return epicStatus;
    }

    /// вывод всех сабтасков для эпика

    public void getAllSubtaskByEpic(int id) {
        Epic epic = epics.get(id);
        System.out.println("Для эпика под id " + epic.getId() + " сабтаски такие:");
        ArrayList<Integer> subtasksByEpic = epic.getSubtasksId();
        for (Integer subtaskId : subtasksByEpic) {
            for (Integer keyId : subtasks.keySet()) {
                if (subtaskId.equals(keyId)) {
                    System.out.println(subtasks.get(subtaskId));
                }
            }
        }
    }

    // ----------------------------------------------------------------
    // блок по CRUD для сабтасков
    // ----------------------------------------------------------------

    /// Добавление сабтаска и привязка к эпику
    public int addNewSubtask(Epic epic, Subtask subtask) {
        final int id = ++idCount;
        subtask.setId(id);
        subtasks.put(id, subtask);

        ArrayList<Integer> epicSubtasksId = epic.getSubtasksId();
        epicSubtasksId.add(id);
        epic.setSubtasksId(epicSubtasksId);
        updateEpic(epic);

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
        if (epics.containsKey(subtask.getEpicId())) {
            final int id = subtask.getId();
            final Subtask savedSubtask = subtasks.get(id);
            if (savedSubtask == null) {
                return;
            }
            subtasks.put(id, subtask);
            System.out.println("Сабтаск с id " + id + " изменён.");
        } else {
            System.out.println("Эпика этого сабтаска не существует.");
        }
    }

    /// Удаление всех сабтасков
    public void deleteSubtasks() {
        subtasks.clear();
        System.out.println("Все сабтаски удалены.");
    }

    /// Удаление сабтаска по id
    public void deleteSubtaskById(int id) {
        subtasks.remove(id);
        System.out.println("Сабтаск с id " + id + " удалён.");
    }

}