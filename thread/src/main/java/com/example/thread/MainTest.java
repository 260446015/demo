package com.example.thread;

import java.util.LinkedList;
import java.util.Queue;

public class MainTest {
    public static void main(String[] args) throws InterruptedException {
        Queue<String> queue = new LinkedList<>();
        int maxSize = 5;
        Producer producer = new Producer(queue,maxSize);
        Coustomer coustomer = new Coustomer(queue,maxSize);
        new Thread(coustomer).start();

        new Thread(producer).start();
    }
}
