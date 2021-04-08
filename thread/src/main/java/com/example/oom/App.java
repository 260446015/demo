package com.example.oom;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

    private final static ThreadLocal<Byte[]> tl = new ThreadLocal<>();

    public static void main(String[] args) throws IOException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        while (true){
            Thread.sleep(1000);
            executorService.execute(() ->{
                tl.set(new Byte[1024*1000]);
            });
        }
    }
}
