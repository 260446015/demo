package com.example.threadv2;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Consumer implements Runnable{
    private int maxSize;
    private Queue<String> msg;
    private Lock lock;
    private Condition condition;

    public Consumer(int maxSize, Queue<String> msg, Lock lock, Condition condition) {
        this.maxSize = maxSize;
        this.msg = msg;
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        while (true){
            lock.lock();
            while (msg.isEmpty()){
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
            System.out.println("消费者消费消息"+msg.remove());
            condition.signal();
            lock.unlock();
        }
    }
}
