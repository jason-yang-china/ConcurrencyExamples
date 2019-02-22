package com.epam.concurrent.pool;

public class Processor implements Runnable {
    private int id;
    public Processor(int id) {
        this.id = id;
    }
    public void run() {
        System.out.println("Starting: "+id);
        try {
            Thread.sleep(10);
        }catch (InterruptedException ex) {

        }
        System.out.println("Completed:"+id);
    }
}
