package com.example;

import org.openjdk.jol.info.ClassLayout;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);
        Object o = new Object();
        String s = ClassLayout.parseInstance(o).toPrintable();
        System.out.println(s);
        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}
