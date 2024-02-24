package ru.yandex.javacource.blokhin.schedule.managers;

import ru.yandex.javacource.blokhin.schedule.managers.historymanager.*;
import ru.yandex.javacource.blokhin.schedule.managers.taskmanager.*;

public class Managers {
    public static TaskManager getDefault() {
        return new InMemoryTaskManager(getDefaultHistoryManager());
    }

    private static HistoryManager getDefaultHistoryManager() {
        return new InMemoryHistoryManager();
    }
}
