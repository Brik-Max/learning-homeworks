package com.company;

import java.util.Arrays;

public class FilterUtils {


    public static <T> T[] filter(T[] array, Filter<T> filter) {


        // Создаем новый массив того же типа и длины
        T[] result = Arrays.copyOf(array, array.length);

        // Применяем функцию apply к каждому элементу
        for (int i = 0; i < array.length; i++) {
            result[i] = filter.apply(array[i]);
        }

        return result;
    }
}