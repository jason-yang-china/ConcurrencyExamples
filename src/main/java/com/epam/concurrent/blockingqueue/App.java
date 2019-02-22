package com.epam.concurrent.blockingqueue;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {

    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(1000);

    public static void  main(String[] args) throws InterruptedException{
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    producer();
                }catch (InterruptedException ex) {

                }
            }
        });


        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    consumer();
                }catch (InterruptedException ex) {

                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    private static void producer() throws InterruptedException{
        Random random = new Random();
        while (true) {
            queue.put(random.nextInt(100));
        }
    }

    private static void consumer() throws InterruptedException{
        Thread.sleep(100);
        while(true) {
           int number = queue.take();
           System.out.println("Taken number: "+number+"; Queue size is:"+queue.size());
        }
    }
}
