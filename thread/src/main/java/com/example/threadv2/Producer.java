package com.example.threadv2;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Producer implements Runnable {
    private int maxSize;
    private Queue<String> msg;
    private Lock lock;
    private Condition condition;

    public Producer(int maxSize, Queue<String> msg, Lock lock, Condition condition) {
        this.maxSize = maxSize;
        this.msg = msg;
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        int i = 0;
        while (true){
            lock.lock();
            while (msg.size() == maxSize){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("生产者生产消息"+ ++i);
            msg.add(""+ i);
            condition.signal();
            lock.unlock();
        }
    }
}
