package com.yfy.multithreading.basic.thread;

public class CASCase {
    public volatile int value;

    public synchronized void add() {
        value++;
    }
}
