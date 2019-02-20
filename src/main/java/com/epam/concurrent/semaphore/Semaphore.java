package com.epam.concurrent.semaphore;

public class Semaphore implements Lock {
    private int counter;
    private int license;

    public Semaphore(int counter) {
        this.counter = counter;
        this.license = counter;
    }
    public void aquire() throws InterruptedException {
        while(counter == 0) wait();
        counter = counter - 1;
    }

    public void release() {
        while (counter < license) {
           counter = counter + 1;
        }
        notify();
    }
}
