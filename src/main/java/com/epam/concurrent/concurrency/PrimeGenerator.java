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
    private boolean isStoped = false;

    public void start() throws InterruptedException {


        executor.submit(new Producer());
        executor.submit(new Consumer());

        executor.shutdown();
        executor.awaitTermination(500, TimeUnit.DAYS);
    }

    private class Producer extends Thread {
        public void run() {

                while(true) {
                    try {
                        lock.lock();

                        bigInteger = bigInteger.nextProbablePrime();
                        queue.put(bigInteger);
                        System.out.println(Thread.currentThread().getName() + " produces prime integer :" + bigInteger + ", queue size is " + queue.size());
                        if (bigInteger.longValue() == endPointNum.longValue()){
                            System.out.println("Producer "+Thread.currentThread().getName()+" has been cancelled");
                            isStoped = true;
                            break;
                        }
                    }catch (InterruptedException e) {
                        System.out.println("it is already interrupted, queue size is " + queue.size());

                        break;
                    }
                    finally {
                        lock.unlock();
                    }
                }
            }

    }

    private class Consumer extends Thread {

        public void run() {
                while(true) {
                    synchronized (Consumer.class) {
                        try {
                            BigInteger bigInteger = queue.take();
                            System.out.println(Thread.currentThread().getName()+ " take prime integer :" +  bigInteger + ", queue size is " + queue.size());
                            if (bigInteger.longValue() == endPointNum.longValue()) {
                                System.out.println("Consumer "+Thread.currentThread().getName()+" has been cancelled");
                                break;
                            }
                        }
                        catch (InterruptedException e) {
                            System.out.println("Consumer InterruptedException: "+e);
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        }
    }






}
