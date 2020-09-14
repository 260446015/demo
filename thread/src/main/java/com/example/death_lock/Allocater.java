package com.example.death_lock;

import java.util.ArrayList;
import java.util.List;

public class Allocater {
    private List<Object> queue = new ArrayList<>();

    public synchronized boolean apply(Account fromAccount,Account toAccount){
        if(queue.contains(fromAccount) || queue.contains(toAccount)){
            return false;
        }
        queue.add(fromAccount);
        queue.add(toAccount);
        return true;
    }

    public synchronized void free(Account fromAccount,Account toAccount){
        queue.remove(fromAccount);
        queue.remove(toAccount);
    }
}
