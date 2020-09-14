package com.example.death_lock;

public class App {

    public static void main(String[] args) {
        Account fromAccount = new Account("ydw",30000);
        Account toAccount = new Account("fyp",10000);
        Allocater allocater = new Allocater();
        new Thread(new TransferAccount(fromAccount,toAccount,500, allocater)).start();
        new Thread(new TransferAccount(toAccount,fromAccount,1000, allocater)).start();
    }
}
