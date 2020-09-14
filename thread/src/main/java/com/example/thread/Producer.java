package com.example.thread;

import java.util.Queue;

public class Producer implements Runnable {
    private Queue<String> queue;
    private int maxSize;

    public Producer(Queue<String> queue, int maxSize) {
        this.queue = queue;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            i++;
            synchronized (queue) {
                while (queue.size() == maxSize) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
               /* try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                System.out.println("生产者生产消息" + i);
                queue.add("" + i);
                queue.notify();
            }
        }
    }
}
