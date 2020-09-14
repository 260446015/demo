package com.example.death_lock;

public class Account {
    private String name;
    private int balace;

    public Account(String name, int balace) {
        this.name = name;
        this.balace = balace;
    }

    public void send(int balace){
        this.balace -=balace;
    }
    public void get(int balace){
        this.balace += balace;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalace() {
        return balace;
    }

    public void setBalace(int balace) {
        this.balace = balace;
    }
}
