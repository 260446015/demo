package com.example.death_lock;

public class TransferAccount implements Runnable {
    private Account fromAccount;
    private Account toAccount;
    private int balance;
    private Allocater allocater;

    public TransferAccount(Account fromAccount, Account toAccount, int balance, Allocater allocater) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.balance = balance;
        this.allocater = allocater;
    }

    @Override
    public void run() {
        while (true){
            if(allocater.apply(fromAccount,toAccount)){
                try {
                    synchronized (fromAccount){
                        synchronized (toAccount){
                            if(fromAccount.getBalace() >= this.balance){
                                fromAccount.send(balance);

                                toAccount.get(balance);
                            }
                        }
                    }
                } finally {
                    allocater.free(fromAccount,toAccount);
                }
            }
           /* try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            //转出账户的余额
            System.out.println(fromAccount.getName() + "->" + fromAccount.getBalace());
            //转入账户的余额
            System.out.println(toAccount.getName() + "->" + toAccount.getBalace());
        }
    }
}
