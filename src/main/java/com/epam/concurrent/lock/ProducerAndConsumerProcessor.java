package com.epam.concurrent.lock;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerAndConsumerProcessor {

    private BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(1000);
    private Random random = new Random();
    private Object lock = new Object();

    private boolean isRunning = true;
    private final int LIMIT_NUM = 200;
    public void producer() {
        int num = 0;

        while (isRunning) {
            try {
                synchronized (lock) {
                    while (queue.size() == LIMIT_NUM) {
                        lock.wait();
                    }

                    queue.put(num++);
                    lock.notify();
                }
            }catch (InterruptedException ex) {

            }
        }
    }

    public void consumer(){
        while (isRunning) {
            try {
                synchronized (lock) {
                    while (queue.size() == 0) {
                        lock.wait();
                    }
                    System.out.print("the size of queue is: "+queue.size());
                    int num = queue.take();
                    System.out.println("; number is: "+num);
                    while (num == 200) {
                        isRunning = false;
                        break;
                    }
                    lock.notify();
                }
                Thread.sleep(random.nextInt(100));


            }catch (InterruptedException ex) {

            }
        }
    }

    public boolean isRunning() {
        return isRunning;
    }
}
