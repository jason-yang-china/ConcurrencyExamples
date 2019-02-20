package com.epam.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class WorkerRunnable implements Runnable {
    private final CountDownLatch doneSignal;
    private final int i;

    public WorkerRunnable(CountDownLatch countDownLatch, int i) {
        this.doneSignal = countDownLatch;
        this.i = i;
    }


    public void run() {
        doWork(i);
        doneSignal.countDown();
        System.out.println("The count is "+doneSignal.getCount());
    }

    void doWork(int i) {
        System.out.println("The number of "+i+" thread is running");
    }
}
