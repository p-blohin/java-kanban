package ru.yandex.javacource.blokhin.schedule.managers.historymanager;

import ru.yandex.javacource.blokhin.schedule.task.*;

import java.util.List;

public interface HistoryManager {

    // ----------------------------------------------------------------
    // блок по обновлению истории просмотра
    // ----------------------------------------------------------------

    /// Обновить историю просмотра
    void add(Task task);

    // ----------------------------------------------------------------
    // блок по выводу истории просмотра
    // ----------------------------------------------------------------

    /// Вызвать историю просмотров
    List<Task> getHistory();
}
