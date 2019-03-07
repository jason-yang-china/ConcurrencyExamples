package com.epam.concurrent.concurrency;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class PrimeGenerator {

    private static final ExecutorService executor = Executors.newFixedThreadPool(4);
    private final BlockingQueue<BigInteger> queue = new ArrayBlockingQueue<BigInteger>(100);
    private BigInteger bigInteger = BigInteger.ONE;
    private ReentrantLock lock = new ReentrantLock();
    private final BigInteger endPointNum = BigInteger.valueOf(733);

    public void start() throws InterruptedException {
//        producer.setName("producer");
//        consumer.setName("consumer");
//        producer.start();
//        consumer.start();
//
//        producer.join();
        //executor.submit(producer);

        for (int i=0;i<2;i++) {
            executor.submit(new Producer());
            executor.submit(new Consumer());
        }
        //executor.submit(producer);
        //executor.submit(producer);
        //executor.submit(consumer);
        //executor.submit(consumer);

        executor.shutdown();
        executor.awaitTermination(500, TimeUnit.MILLISECONDS);
    }

    private class Producer extends Thread {
        public void run() {

                while(true) {
                    try {
                        lock.lock();
                        bigInteger = bigInteger.nextProbablePrime();
                    }finally {
                        lock.unlock();
                    }

                    try {
                        queue.put(bigInteger);
                        System.out.println(Thread.currentThread().getName() + " produces prime integer :" + bigInteger + ", queue size is " + queue.size());
                        if (bigInteger.longValue() == endPointNum.longValue()){
                            break;
                        }
                    }
                    catch (InterruptedException e) {
                        System.out.println("queue size is " + queue.size());
                        break;
                    }
                }
            }
//        }
    }

    private class Consumer extends Thread {


        public void run() {
//            synchronized (Consumer.class) {
                while(true) {
                    try {
                        BigInteger bigInteger = queue.take();
                        System.out.println(Thread.currentThread().getName()+ " take prime integer :" +  bigInteger + ", queue size is " + queue.size());
                        if (bigInteger.longValue() == endPointNum.longValue()) {
                            break;
                        }
                    }
                    catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
//        }
    }






}
