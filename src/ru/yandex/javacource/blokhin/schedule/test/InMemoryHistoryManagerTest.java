package ru.yandex.javacource.blokhin.schedule.test;

import org.junit.jupiter.api.Test;
import ru.yandex.javacource.blokhin.schedule.managers.historymanager.*;
import ru.yandex.javacource.blokhin.schedule.task.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {

    /*
        Тест на добавление просмотренных заданий в историю просмотров.
    */
    @Test
    void add() {
        HistoryManager historyManager = new InMemoryHistoryManager();

        historyManager.add(new Task("Тест - таск","Тест - описание таска", 0));
        historyManager.add(new Epic("Тест - эпик","Тест - описание эпика", 1));
        historyManager.add(new Subtask("Тест - сабтаск №1 для эпика","Тест - описание сабтаска №1", 2, 1));
        historyManager.add(new Subtask("Тест - сабтаск №2 для эпика","Тест - описание сабтаска №2", 3, 1));
        historyManager.add(new Subtask("Тест - сабтаск №3 для эпика","Тест - описание сабтаска №3", 4, 1));

        List<Task> taskHistory = historyManager.getHistory();

        assertNotNull(taskHistory, "История заполнена");
        assertEquals(5, taskHistory.size(), "Все виды задач не были добавлены");
    }

    /*
        Тест на вывод истории просмотров.
    */
    @Test
    void getHistory() {

        HistoryManager historyManager = new InMemoryHistoryManager();

        historyManager.add(new Task("Тест - таск №1","Тест - описание таска", 0));
        historyManager.add(new Epic("Тест - эпик №1","Тест - описание эпика", 1));
        historyManager.add(new Subtask("Тест - сабтаск №1 для эпика №1","Тест - описание сабтаска №1", 2, 1));
        historyManager.add(new Subtask("Тест - сабтаск №2 для эпика №1","Тест - описание сабтаска №2", 3, 1));
        historyManager.add(new Subtask("Тест - сабтаск №3 для эпика №1","Тест - описание сабтаска №3", 4, 1));

        historyManager.add(new Task("Тест - таск №2","Тест - описание таска", 5));
        historyManager.add(new Epic("Тест - эпик №2","Тест - описание эпика", 6));
        historyManager.add(new Subtask("Тест - сабтаск №4 для эпика №2","Тест - описание сабтаска №4", 7, 6));
        historyManager.add(new Subtask("Тест - сабтаск №5 для эпика №2","Тест - описание сабтаска №5", 8, 6));
        historyManager.add(new Subtask("Тест - сабтаск №6 для эпика №2","Тест - описание сабтаска №6", 9, 6));

        historyManager.add(new Task("Тест - таск №3","Тест - описание таска", 10));
        historyManager.add(new Epic("Тест - эпик №3","Тест - описание эпика", 11));
        historyManager.add(new Subtask("Тест - сабтаск №7 для эпика №3","Тест - описание сабтаска №7", 12, 11));
        historyManager.add(new Subtask("Тест - сабтаск №8 для эпика №3","Тест - описание сабтаска №8", 13, 11));
        historyManager.add(new Subtask("Тест - сабтаск №9 для эпика №3","Тест - описание сабтаска №9", 14, 11));

        // добавляем в историю просмотров последние 10 просмотренных тасков
        List<Task> taskHistory = historyManager.getHistory();
        List<Task> taskHistoryToCompare = new ArrayList<>(10);

        // добавляем в тестовую историю просмотров последние 10 тасков, которые ожидаем в истории
        taskHistoryToCompare.add(new Task("Тест - таск №2","Тест - описание таска", 5));
        taskHistoryToCompare.add(new Epic("Тест - эпик №2","Тест - описание эпика", 6));
        taskHistoryToCompare.add(new Subtask("Тест - сабтаск №4 для эпика №2","Тест - описание сабтаска №4", 7, 6));
        taskHistoryToCompare.add(new Subtask("Тест - сабтаск №5 для эпика №2","Тест - описание сабтаска №5", 8, 6));
        taskHistoryToCompare.add(new Subtask("Тест - сабтаск №6 для эпика №2","Тест - описание сабтаска №6", 9, 6));

        taskHistoryToCompare.add(new Task("Тест - таск №3","Тест - описание таска", 10));
        taskHistoryToCompare.add(new Epic("Тест - эпик №3","Тест - описание эпика", 11));
        taskHistoryToCompare.add(new Subtask("Тест - сабтаск №7 для эпика №3","Тест - описание сабтаска №7", 12, 11));
        taskHistoryToCompare.add(new Subtask("Тест - сабтаск №8 для эпика №3","Тест - описание сабтаска №8", 13, 11));
        taskHistoryToCompare.add(new Subtask("Тест - сабтаск №9 для эпика №3","Тест - описание сабтаска №9", 14, 11));

        assertEquals(taskHistoryToCompare, taskHistory, "История выведена неверно.");
    }

    /*
        Тест на вывод истории просмотров в любом порядке.
    */
    @Test
    void getHistoryInAnyOrder() {

        HistoryManager historyManager = new InMemoryHistoryManager();

        historyManager.add(new Task("Тест - таск №1","Тест - описание таска", 0));
        historyManager.add(new Epic("Тест - эпик №1","Тест - описание эпика", 1));
        historyManager.add(new Subtask("Тест - сабтаск №1 для эпика №1","Тест - описание сабтаска №1", 2, 1));
        historyManager.add(new Subtask("Тест - сабтаск №2 для эпика №1","Тест - описание сабтаска №2", 3, 1));
        historyManager.add(new Subtask("Тест - сабтаск №3 для эпика №1","Тест - описание сабтаска №3", 4, 1));

        historyManager.add(new Task("Тест - таск №2","Тест - описание таска", 5));
        historyManager.add(new Epic("Тест - эпик №2","Тест - описание эпика", 6));
        historyManager.add(new Subtask("Тест - сабтаск №4 для эпика №2","Тест - описание сабтаска №4", 7, 6));
        historyManager.add(new Subtask("Тест - сабтаск №5 для эпика №2","Тест - описание сабтаска №5", 8, 6));
        historyManager.add(new Subtask("Тест - сабтаск №6 для эпика №2","Тест - описание сабтаска №6", 9, 6));

        historyManager.add(new Task("Тест - таск №3","Тест - описание таска", 10));
        historyManager.add(new Epic("Тест - эпик №3","Тест - описание эпика", 11));
        historyManager.add(new Subtask("Тест - сабтаск №7 для эпика №3","Тест - описание сабтаска №7", 12, 11));
        historyManager.add(new Subtask("Тест - сабтаск №8 для эпика №3","Тест - описание сабтаска №8", 13, 11));
        historyManager.add(new Subtask("Тест - сабтаск №9 для эпика №3","Тест - описание сабтаска №9", 14, 11));

        // также проверим удаление уже просмотренных тасков
        historyManager.add(new Subtask("Тест - сабтаск №6 для эпика №2","Тест - описание сабтаска №6", 9, 6));
        historyManager.add(new Epic("Тест - эпик №3","Тест - описание эпика", 11));
        historyManager.add(new Task("Тест - таск №2","Тест - описание таска", 5));

        // добавляем в историю просмотров последние 10 просмотренных тасков
        List<Task> taskHistory = historyManager.getHistory();
        List<Task> taskHistoryToCompare = new ArrayList<>(10);

        // добавляем в тестовую историю просмотров последние 10 тасков, которые ожидаем в истории вне зависимости от порядка просмотра
        taskHistoryToCompare.add(new Task("Тест - таск №2","Тест - описание таска", 5));
        taskHistoryToCompare.add(new Epic("Тест - эпик №2","Тест - описание эпика", 6));

        taskHistoryToCompare.add(new Subtask("Тест - сабтаск №7 для эпика №3","Тест - описание сабтаска №7", 12, 11));
        taskHistoryToCompare.add(new Subtask("Тест - сабтаск №8 для эпика №3","Тест - описание сабтаска №8", 13, 11));
        taskHistoryToCompare.add(new Subtask("Тест - сабтаск №9 для эпика №3","Тест - описание сабтаска №9", 14, 11));

        taskHistoryToCompare.add(new Task("Тест - таск №3","Тест - описание таска", 10));
        taskHistoryToCompare.add(new Epic("Тест - эпик №3","Тест - описание эпика", 11));

        taskHistoryToCompare.add(new Subtask("Тест - сабтаск №4 для эпика №2","Тест - описание сабтаска №4", 7, 6));
        taskHistoryToCompare.add(new Subtask("Тест - сабтаск №5 для эпика №2","Тест - описание сабтаска №5", 8, 6));
        taskHistoryToCompare.add(new Subtask("Тест - сабтаск №6 для эпика №2","Тест - описание сабтаска №6", 9, 6));

        assertTrue(taskHistoryToCompare.containsAll(taskHistory), "Проверка на вывод истории в любом порядке провалена.");
    }
}