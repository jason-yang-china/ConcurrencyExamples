package com.epam.concurrent.java8concurrency;


public class Task extends Thread{

    private boolean isVisible = false;

    public void threadSetVisible() {
        isVisible = true;
    }

    public  void run() {
        while(!isVisible) {
            System.out.println(Thread.currentThread().getName()+" is running infinitely");
        }
    }

}
