package ru.yandex.javacource.blokhin.schedule.test;

import org.junit.jupiter.api.Test;
import ru.yandex.javacource.blokhin.schedule.task.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {

    /*
        Тест на проверку привязки сабтасков к эпику:
        1. Пустой ли ссылочный массив эпика после добавления сабтасков;
        2. Соответствует ли кол-во переданных ссылок размеру ссылочного списка эпика.
    */
    @Test
    void addSubtaskToEpic() {
        Epic epic = new Epic("Тест - эпик", "Тест - описание", 0);
        ArrayList<Integer> subtasks = epic.getSubtasksId();

        subtasks.add(0);
        subtasks.add(1);
        subtasks.add(2);

        assertNotNull(subtasks, "ID сабтасков не добавлены");
        assertEquals(3, subtasks.size(), "Кол-во ID не соответствует добавленному кол-ву сабтасков");
    }

    /*
        Тест на проверку вывода сабтасков от эпика:
        1. Пустой ли ссылочный массив эпика после добавления сабтасков;
        2. Соответствует ли записанный список ссылочному списку эпика.
     */
    @Test
    void getSubtasksFromEpic() {
        Epic epic = new Epic("Тест - эпик", "Тест - описание", 0);
        ArrayList<Integer> subtasks = epic.getSubtasksId();

        subtasks.add(0);
        subtasks.add(1);
        subtasks.add(2);

        ArrayList<Integer> subtasksToCompare = new ArrayList<>();

        subtasksToCompare.add(0);
        subtasksToCompare.add(1);
        subtasksToCompare.add(2);

        assertNotNull(subtasks, "ID сабтасков не добавлены");
        assertEquals(subtasksToCompare, subtasks, "Выведенные сабтаски не соответствуют привязанным к эпику");
    }

    /*
        Тест на проверку корректного удаления определённых сабтасков от эпика.
     */

    @Test
    void removeSubtaskFromEpic() {
        Epic epic = new Epic("Тест - эпик", "Тест - описание", 0);
        ArrayList<Integer> subtasks = epic.getSubtasksId();

        subtasks.add(0);
        subtasks.add(1);
        subtasks.add(2);
        subtasks.add(3);
        subtasks.add(4);

        epic.removeSubtask(1);
        epic.removeSubtask(2);

        ArrayList<Integer> subtasksToCompare = new ArrayList<>();

        subtasksToCompare.add(0);
        subtasksToCompare.add(1);
        subtasksToCompare.add(2);

        assertEquals(3, subtasks.size(), "Ссылка на сабтаск не была удалена");
    }

}