package com.company;

public class Main {
    public static void main(String[] args) {

        MyStringBuilder sb = new MyStringBuilder("Hello");

        System.out.println("Исходное состояние: " + sb);

        sb.append(" World");
        System.out.println("После append: " + sb);

        sb.insert(5, " Beautiful");
        System.out.println("После insert: " + sb);

        sb.delete(5, 15);
        System.out.println("После delete: " + sb);

        sb.undo();
        System.out.println("После undo: " + sb);

        sb.undo();
        System.out.println("После undo: " + sb);

        sb.undo();
        System.out.println("После undo: " + sb);

        sb.undo(); // Попытка отменить, когда история пуста


    }
}
