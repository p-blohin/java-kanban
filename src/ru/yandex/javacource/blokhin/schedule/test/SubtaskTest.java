package ru.yandex.javacource.blokhin.schedule.test;

import org.junit.jupiter.api.Test;
import ru.yandex.javacource.blokhin.schedule.task.Subtask;

import static org.junit.jupiter.api.Assertions.*;

class SubtaskTest {

    @Test
    void getEpicId() {
        Subtask subtask = new Subtask("Тест - название сабтаска","Тест - описание сабтаска", 1, 2);

        int testEpicId = 2;
        int savedEpicId = subtask.getEpicId();

        assertEquals(testEpicId, savedEpicId, "ID привязанного эпика не совпадает.");
    }
}