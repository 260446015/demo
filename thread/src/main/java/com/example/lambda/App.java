package com.example.lambda;

import java.util.*;

public class App {
    private static Set set = new HashSet();

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            set.clear();
            for (int i = 0; i < 100; i++) {
                List<String> list1 = new ArrayList<>();
                List<String> list2 = new Vector<>();
                list1.add("a");
                list1.add("b");
                list1.add("c");
                list1.add("d");
                list1.parallelStream().forEach(str -> {
//                    synchronized (list2){
                        list2.add(str);
//                    }
                });
//                Thread.sleep(1000);
                if(list2.size() != 4){
                    System.out.println(list2.size());
                }
//                set.add(list2.size());
            }
        }

    }
}
