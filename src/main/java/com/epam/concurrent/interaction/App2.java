package com.epam.concurrent.interaction;

public class App2 {
    private int count = 0;

    public static void main(String[] args) {
        App2 app2 = new App2();
        app2.doWork();
    }

    public void doWork() {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0;i<10000; i++) {
                    synchronized (this) {
                        count++;
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                for(int i=0; i<10000;i++) {
                    synchronized (this) {
                        count++;
                    }
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        }catch (InterruptedException ex) {

        }

        System.out.println("count is now "+count);
    }
}
