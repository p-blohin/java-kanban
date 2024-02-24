package ru.yandex.javacource.blokhin.schedule.managers.historymanager;

import ru.yandex.javacource.blokhin.schedule.task.*;

import java.util.List;

public interface HistoryManager {
    /// Обновить историю просмотра
    void add(Task task);

    /// Вызвать историю просмотров
    List<Task> getHistory();
}
