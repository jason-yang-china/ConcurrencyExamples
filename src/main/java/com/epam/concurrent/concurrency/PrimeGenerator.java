package com.epam.concurrent.concurrency;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class PrimeGenerator {

    private final BlockingQueue<BigInteger> queue = new ArrayBlockingQueue<BigInteger>(100);
    private final Producer producer = new Producer();
    private final Consumer consumer = new Consumer();

    private final BigInteger endPointNum = BigInteger.valueOf(733);

    public void start() throws InterruptedException {
        producer.setName("producer");
        consumer.setName("consumer");
        producer.start();
        consumer.start();

        producer.join();

    }

    private class Producer extends Thread {
        public void run() {
            BigInteger bigInteger = BigInteger.ONE;
            synchronized (Producer.class) {
                while(true) {
                    bigInteger = bigInteger.nextProbablePrime();
                    try {
                        queue.put(bigInteger);
                        System.out.println(Thread.currentThread().getName() + " produces prime integer :" + bigInteger + ", queue size is " + queue.size());
                        if (bigInteger.longValue() == endPointNum.longValue()){
                            break;
                        }
                    }catch (InterruptedException e) {
                        System.out.println("queue size is " + queue.size());
                        break;
                    }
                }
            }
        }
    }

    private class Consumer extends Thread {


        public void run() {
            synchronized (Consumer.class) {
                while(true) {
                    try {
                        BigInteger bigInteger = queue.take();
                        System.out.println(Thread.currentThread().getName()+ " take prime integer :" +  bigInteger + ", queue size is " + queue.size());
                        if (bigInteger.longValue() == endPointNum.longValue()) {
                            break;
                        }
                    }catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }






}
