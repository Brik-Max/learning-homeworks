package com.company;

import java.util.ArrayDeque;
import java.util.Deque;

public class MyStringBuilder {
    private StringBuilder stringBuilder;
    private Deque<Memento> history;

    // Внутренний класс Memento для хранения снимков
    private static class Memento {
        private final String state;

        public Memento(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }
    }

    public MyStringBuilder() {
        this.stringBuilder = new StringBuilder();
        this.history = new ArrayDeque<>();
    }

    public MyStringBuilder(String str) {
        this.stringBuilder = new StringBuilder(str);
        this.history = new ArrayDeque<>();
    }

    // Создание снимка
    private Memento createMemento() {
        return new Memento(stringBuilder.toString());
    }

    // Восстановление из снимка
    private void restoreFromMemento(Memento memento) {
        this.stringBuilder = new StringBuilder(memento.getState());
    }

    // Основные методы
    public MyStringBuilder append(String str) {
        history.push(createMemento());
        stringBuilder.append(str);
        return this;
    }

    public MyStringBuilder insert(int offset, String str) {
        history.push(createMemento());
        stringBuilder.insert(offset, str);
        return this;
    }

    public MyStringBuilder delete(int start, int end) {
        history.push(createMemento());
        stringBuilder.delete(start, end);
        return this;
    }

    public MyStringBuilder undo() {
        if (!history.isEmpty()) {
            Memento memento = history.pop();
            restoreFromMemento(memento);
        } else {
            System.out.println("Нет операций для отмены");
        }
        return this;
    }

    public String toString() {
        return stringBuilder.toString();
    }

    public int length() {
        return stringBuilder.length();
    }
}