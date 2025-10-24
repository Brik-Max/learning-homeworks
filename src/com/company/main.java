package com.company;

import java.util.Arrays;

import static com.company.FilterUtils.filter;

public class main {

    public static void main(String[] args) {

        Integer[] numbers = {1, 2, 3, 4, 5};

        // Используем анонимный класс
        Integer[] doubledNumbers = filter(numbers, new Filter<Integer>() {
            @Override
            public Integer apply(Integer num) {
                return num * 2;
            }
        });

        //
        Integer[] doubledNumbers2 = filter(numbers, x -> x*2);

        System.out.println("Исходный массив: " + Arrays.toString(numbers));
        System.out.println("Удвоенный массив: " + Arrays.toString(doubledNumbers));
        System.out.println("Удвоенный массив: " + Arrays.toString(doubledNumbers2));


        String[] words = {"hello", "world", "java"};

        String[] upperCaseWords = filter(words, new Filter<String>() {
            @Override
            public String apply(String s) {
                return s.toUpperCase();
            }
        });

        System.out.println("Исходные слова: " + Arrays.toString(words));

        System.out.println("В верхнем регистре: " + Arrays.toString(upperCaseWords));


    }
}
