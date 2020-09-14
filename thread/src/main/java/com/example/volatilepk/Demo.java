package com.example.volatilepk;

public class Demo {
    private static boolean flag = true;
//    private volatile static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() ->{
            while (flag){
//                i++;
            }
        }).start();
        Thread.sleep(1000);
        flag = false;
    }
}
