package com.epam.concurrent.concurrency;

public class IncreaseNumberRunnable implements Runnable {
    private final int MAX =100000;
    private Increasement increaseNumber;

    public IncreaseNumberRunnable(Increasement increaseNumber) {
        this.increaseNumber = increaseNumber;
    }

    public void run() {
        for(int i = 0; i< MAX; i++) {
            increaseNumber.increaseNumber();
            System.out.println(Thread.currentThread().getName()+ ":"+this.increaseNumber.getNumber());
        }
    }
}
