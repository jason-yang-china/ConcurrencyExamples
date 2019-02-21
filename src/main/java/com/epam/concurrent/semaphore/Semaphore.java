package com.epam.concurrent.semaphore;

public class Semaphore implements Lock {
    private int counter;
    private final int license;

    public Semaphore(int counter) {
        this.counter = counter;
        this.license = counter;
    }
    public synchronized void acquire() throws InterruptedException {
        while(counter == 0) {
            wait();
        }
        counter = counter - 1;
    }

    public synchronized void release() {
        while (counter < license) {
           counter = counter + 1;
            notify();
        }

    }
}
