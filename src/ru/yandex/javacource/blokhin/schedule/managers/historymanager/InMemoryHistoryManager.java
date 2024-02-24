package ru.yandex.javacource.blokhin.schedule.managers.historymanager;

import ru.yandex.javacource.blokhin.schedule.task.*;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private final List<Task> taskHistory = new ArrayList<>(10);

    /// Обновить историю просмотра
    @Override
    public void add(Task task) {
        taskHistory.remove(task);
        taskHistory.add(task);
    }

    /// Вызвать историю просмотров
    @Override
    public List<Task> getHistory() {
        if (taskHistory.size() > 10) {
            while (taskHistory.size() > 10) {
                taskHistory.removeFirst();
            }
        }
        return taskHistory;
    }
}
