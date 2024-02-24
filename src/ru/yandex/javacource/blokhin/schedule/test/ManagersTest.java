package ru.yandex.javacource.blokhin.schedule.test;

import org.junit.jupiter.api.Test;
import ru.yandex.javacource.blokhin.schedule.managers.Managers;
import ru.yandex.javacource.blokhin.schedule.managers.taskmanager.TaskManager;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ManagersTest {

    @Test
    void getDefault() {
        TaskManager taskManager = Managers.getDefault();
        assertNotNull(taskManager, "Созданный менеджер равен null.");
    }
}
