package com.example.threadv2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class App {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        int maxSize = 5;
        Queue<String> msg = new LinkedList<>();
        Producer producer = new Producer(maxSize,msg,lock,condition);
        Consumer consumer = new Consumer(maxSize,msg,lock,condition);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
