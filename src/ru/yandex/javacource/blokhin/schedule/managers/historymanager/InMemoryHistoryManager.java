package ru.yandex.javacource.blokhin.schedule.managers.historymanager;

import ru.yandex.javacource.blokhin.schedule.task.*;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private List<Task> taskHistory = new ArrayList<>(10);

    // ----------------------------------------------------------------
    // блок по обновлению истории просмотра
    // ----------------------------------------------------------------

    /// Обновить историю просмотра
    @Override
    public void add(Task task) {
        if (taskHistory.contains(task)) {
            taskHistory.remove(task);
        }
        if (taskHistory.size() >= 10) {
            taskHistory.removeFirst();
        }
        taskHistory.add(task);
    }

    // ----------------------------------------------------------------
    // блок по выводу истории просмотра
    // ----------------------------------------------------------------

    /// Вызвать историю просмотров
    @Override
    public List<Task> getHistory() {
        return taskHistory;
    }
}
