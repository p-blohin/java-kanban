import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private static int idCount = 0;

    // добавление задачи в список

    public void addToTaskList(HashMap<Integer, Task> taskList, Task taskToAdd) {
        taskList.put(taskToAdd.getId(), taskToAdd);
    }

    // ----------------------------------------------------------------
    // блок по созданию тасков, сабтасков и эпиков
    // ----------------------------------------------------------------

    // метод для создания уникальных id
    public static int createId() {
        return idCount++;
    }

    // создание таска
    public Task createTask(Task task) {
        Task newTask = new Task(task.getName(), task.getDescription(),
                createId(), task.getStatus());
        return newTask;
    }

    // создание сабтаска
    public Subtask createSubtask(Subtask subtask) {
        Subtask newSubtask = new Subtask(subtask.getName(), subtask.getDescription(),
                createId(), subtask.getStatus());
        return newSubtask;
    }

    // привязка сабтаска к эпику
    public void addSubtaskToEpic(Epic epic, Subtask subtask) {
        ArrayList<Integer> epicSubtasks = epic.getSubtasksId();
        if (epicSubtasks.contains(subtask.getId())) {
            epicSubtasks.remove((Object)subtask.getId());
        }
        epicSubtasks.add(subtask.getId());
        subtask.setEpicId(epic.getId());
    }

    // создание эпика
    public Epic createEpic(Epic epic) {
        Epic newEpic = new Epic(epic.getName(), epic.getDescription(),
                createId(), epic.getStatus());
        return newEpic;
    }

    // ----------------------------------------------------------------
    // блок по выводу всех задач, тасков, сабтасков и эпиков
    // ----------------------------------------------------------------

    // все задачи
    public void printAllTasks(HashMap<Integer, Task> taskList) {
        System.out.println("Показываю все задачи");
        for (Integer keyId : taskList.keySet()) {
            System.out.println(keyId);
            Task taskToPrint = taskList.get(keyId);
            System.out.println(taskToPrint);
        }
    }

    // только таски
    public void printOnlyTasks(HashMap<Integer, Task> taskList) {
        System.out.println("Показываю все таски");
        for (Integer keyId : taskList.keySet()) {
            Task taskToPrint = taskList.get(keyId);
            if (taskToPrint.getClass().equals(Task.class)) {
                System.out.println(keyId);
                System.out.println(taskToPrint);
            }
        }
    }

    // только сабтаски
    public void printOnlySubtasks(HashMap<Integer, Task> taskList) {
        System.out.println("Показываю все сабтаски");
        for (Integer keyId : taskList.keySet()) {
            Task SubtaskToPrint = taskList.get(keyId);
            if (SubtaskToPrint.getClass().equals(Subtask.class)) {
                System.out.println(keyId);
                System.out.println(SubtaskToPrint);
            }
        }
    }

    // только эпики
    public void printOnlyEpics(HashMap<Integer, Task> taskList) {
        System.out.println("Показываю все эпики");
        for (Integer keyId : taskList.keySet()) {
            Task epicToPrint = taskList.get(keyId);
            if (epicToPrint.getClass().equals(Epic.class)) {
                System.out.println(keyId);
                System.out.println(epicToPrint);
            }
        }
    }

    // ----------------------------------------------------------------
    // блок по выводу по id всех задач, тасков, сабтасков и эпиков
    // ----------------------------------------------------------------

    // все задачи
    public void printTaskById(HashMap<Integer, Task> taskList, int id) {
        System.out.println("Показываю задачу под номером " + id);
        System.out.println(taskList.get(id));
    }

    // только таски
    public void printOnlyTaskById(HashMap<Integer, Task> taskList, int id) {
        if (taskList.get(id).getClass().equals(Task.class)) {
            System.out.println("Показываю таск под номером " + id);
            System.out.println(taskList.get(id));
        } else {
            System.out.println("Таска под таким номером нет!");
        }
    }

    // только сабтаски
    public void printOnlySubtaskById(HashMap<Integer, Task> taskList, int id) {
        if (taskList.get(id).getClass().equals(Subtask.class)) {
            System.out.println("Показываю сабтаск под номером " + id);
            System.out.println(taskList.get(id));
        } else {
            System.out.println("Сабтаска под таким номером нет!");
        }
    }

    // только эпики
    public void printOnlyEpicById(HashMap<Integer, Task> taskList, int id) {
        if (taskList.get(id).getClass().equals(Epic.class)) {
            System.out.println("Показываю эпик под номером " + id);
            System.out.println(taskList.get(id));
        } else {
            System.out.println("Эпика под таким номером нет!");
        }
    }

    // ----------------------------------------------------------------
    // вывод всех сабтасков для эпика
    // ----------------------------------------------------------------

    public void printAllSubtaskByEpic(HashMap<Integer, Task> taskList, Epic epic) {
        System.out.println("Для эпика под id " + epic.getId() + " сабтаски такие:");
        ArrayList<Integer> subtaskByEpic = epic.getSubtasksId();
        for (Integer subtaskId : subtaskByEpic) {
            for (Integer keyId : taskList.keySet()) {
                if (subtaskId.equals(keyId)) {
                    Task subtaskToPrint = taskList.get(subtaskId);
                    System.out.println(subtaskToPrint);
                }
            }
        }
    }

    // ----------------------------------------------------------------
    // блок по удалению всех задач, тасков, сабтасков и эпиков
    // ----------------------------------------------------------------

    // все задачи
    public void deleteAllTasks(HashMap<Integer, Task> taskList) {
        taskList.clear();
        System.out.println("Все задачи удалены");
    }

    // ----------------------------------------------------------------
    // блок по удалению по id всех задач, тасков, сабтасков и эпиков
    // ----------------------------------------------------------------

    // только задачи
    public void deleteTaskById(HashMap<Integer, Task> taskList, int id) {
        Task taskToRemove = taskList.get(id);
        taskList.remove(taskToRemove.getId());
        System.out.println("Задание под id " + id + " удалено");
    }

    // только таски
    public void deleteOnlyTaskById(HashMap<Integer, Task> taskList, int id) {
        Task taskToRemove = taskList.get(id);
        if (taskToRemove.getClass().equals(Task.class)) {
            taskList.remove(taskToRemove.getId());
            System.out.println("Таск под id " + id + " удален");
        } else {
            System.out.println("Таска под таким id нет!");
        }
    }

    // только сабтаски
    public void deleteOnlySubtaskById(HashMap<Integer, Task> taskList, int id) {
        Task subtaskToRemove = taskList.get(id);
        if (subtaskToRemove.getClass().equals(Subtask.class)) {
            taskList.remove(subtaskToRemove.getId());
            System.out.println("Сабтаск под id " + id + " удален");
        } else {
            System.out.println("Сабтаска под таким id нет!");
        }
    }

    // только эпики
    public void deleteOnlyEpicById(HashMap<Integer, Task> taskList, int id) {
        Task epicToRemove = taskList.get(id);
        if (epicToRemove.getClass().equals(Epic.class)) {
            taskList.remove(epicToRemove.getId());
            System.out.println("Эпик под id " + id + " удален");
        } else {
            System.out.println("Эпика под таким id нет!");
        }
    }

    // ----------------------------------------------------------------
    // блок по изменению всех задач, тасков, сабтасков и эпиков
    // ----------------------------------------------------------------

    public void updateTask(HashMap<Integer, Task> taskList, int id, Task newTask) {
        newTask.setId(id);
        taskList.put(id, newTask);
        System.out.println("Таск / эпик с id " + newTask.getId() + " изменён");
    }

    // проверка эпика на статус
    public TaskStatus epicProgressCheck(HashMap<Integer, Task> taskList, int epicId) {
        Task taskToCheck = taskList.get(epicId);
        TaskStatus epicStatus = TaskStatus.NEW;
        if (taskToCheck.getClass().equals(Epic.class)) {
            Epic epic = (Epic) taskToCheck;
            ArrayList<Integer> subtasksToCheck = epic.getSubtasksId();
            ArrayList<TaskStatus> subtasksStatus = new ArrayList<>();
            for (Integer keyId : taskList.keySet()) {
                for (Integer subtasksId : subtasksToCheck) {
                    if (subtasksId.equals(keyId)) {
                        subtasksStatus.add(taskList.get(subtasksId).getStatus());
                    }
                }
            }
            if (!(subtasksStatus.contains(TaskStatus.NEW) || subtasksStatus.contains(TaskStatus.IN_PROGRESS))) {
                epicStatus = TaskStatus.DONE;
            } else if (subtasksStatus.contains(TaskStatus.DONE)) {
                epicStatus = TaskStatus.IN_PROGRESS;
            }
        }
        return epicStatus;
    }

    public void updateSubtask(HashMap<Integer, Task> taskList, int id, Subtask newSubtask, Epic epic) {
        newSubtask.setId(id);
        addSubtaskToEpic(epic, newSubtask);
        taskList.put(id, newSubtask);

        int epicId = newSubtask.getEpicId();
        Task epicToSearch = taskList.get(epicId);
        epicToSearch.setStatus(epicProgressCheck(taskList, epicId));
        taskList.put(epicToSearch.getId(), epicToSearch);

        if (epicToSearch.getStatus().equals(TaskStatus.DONE)) {
            System.out.println("Эпик с id " + epicToSearch.getId() + " выполнен!");
        }
        System.out.println("Сабтаск с id " + newSubtask.getId() + " изменён");
    }
}