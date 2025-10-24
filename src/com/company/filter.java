package com.company;

interface Filter<T> {
    T apply(T o);
}