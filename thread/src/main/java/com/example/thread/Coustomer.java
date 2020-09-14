package com.example.thread;

import java.util.Queue;

public class Coustomer implements Runnable {
    private Queue<String> queue;
    private int maxSize;

    public Coustomer(Queue<String> queue, int maxSize) {
        this.queue = queue;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {

                while (queue.isEmpty()) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                /*try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                System.out.println("消费者消费消息：" + queue.remove());
                queue.notify();
            }
        }
    }
}
