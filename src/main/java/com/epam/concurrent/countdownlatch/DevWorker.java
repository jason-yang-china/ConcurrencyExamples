package com.epam.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class DevWorker implements Runnable {
    private CountDownLatch countDownLatch;
    private String groupName;
    public DevWorker(CountDownLatch countDownLatch, String name) {
        this.countDownLatch = countDownLatch;
        this.groupName = name;
    }

    private String getGroupName() { return this.groupName;}


    public void run() {
        System.out.println("Task was assigned to development team "+ this.getGroupName());
        try {
            Thread.sleep(1000);
        }catch (InterruptedException ex) {

        }
        System.out.println("Task finished by development team "+ this.getGroupName());
        this.countDownLatch.countDown();
        System.out.println("DONE "+this.countDownLatch);
    }
}
