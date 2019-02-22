package com.epam;

import com.epam.concurrent.concurrency.IncreaseNumber;
import com.epam.concurrent.concurrency.IncreaseNumberRunnable;
import com.epam.concurrent.concurrency.SyncIncreaseNumber;
import com.epam.concurrent.lock.IncreaseCount;
import com.epam.concurrent.lock.ProducerAndConsumerProcessor;
import com.epam.concurrent.pool.Processor;
import com.epam.concurrent.semaphore.Customer;
import com.epam.concurrent.semaphore.Hotel;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class ConcurrencyTest {

    @Test
    public void increaseNumberTest() throws InterruptedException{

        IncreaseNumber increaseNumber = new IncreaseNumber(0);
        Thread thread1 = new Thread(new IncreaseNumberRunnable(increaseNumber), "Thread 1");
        Thread thread2 = new Thread(new IncreaseNumberRunnable(increaseNumber), "Thread 2");
        Thread thread3 = new Thread(new IncreaseNumberRunnable(increaseNumber), "Thread 3");
        Thread thread4 = new Thread(new IncreaseNumberRunnable(increaseNumber), "Thread 4");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();

        Assert.assertNotEquals(400000, increaseNumber.getNumber());
    }

    @Test
    public void increaseNumberWithSynchronizedTest() throws InterruptedException {

        SyncIncreaseNumber myIncreaseNumber = new SyncIncreaseNumber(0);
        Thread thread1 = new Thread(new IncreaseNumberRunnable(myIncreaseNumber), "Thread 1");
        Thread thread2 = new Thread(new IncreaseNumberRunnable(myIncreaseNumber), "Thread 2");
        Thread thread3 = new Thread(new IncreaseNumberRunnable(myIncreaseNumber), "Thread 3");
        Thread thread4 = new Thread(new IncreaseNumberRunnable(myIncreaseNumber), "Thread 4");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread2.join();
        thread3.join();
        thread1.join();
        thread4.join();
        Assert.assertEquals(400000, myIncreaseNumber.getNumber());
    }

    @Test
    public void reentrantLockTest() throws InterruptedException {
        final IncreaseCount increaseCount = new IncreaseCount();
        Thread t1 = new Thread() {
          @Override
          public void run() {
              while(increaseCount.getCount() < 2000);
          }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                while(increaseCount.getCount() < 2000);
            }
        };

        t1.start();
        t2.start();
        t1.join();
    }

    @Test
    public void bookingRoomTest() throws InterruptedException {
        Hotel hotel = new Hotel();
        new Customer("Jack", hotel).start();
        new Customer("Peter", hotel).start();
        new Customer("Steven Jobs", hotel).start();
        new Customer("John", hotel).start();
        new Customer("Danny", hotel).start();
        new Customer("Rafe", hotel).start();

        Thread.sleep(100);

    }

    @Test
    public void testThreadPool()  throws InterruptedException  {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        int id1 =1;
        int id2 =2;
        executor.submit(new Processor(1));
        executor.submit(new Processor(2));

        executor.shutdown();
        System.out.println("all tasks have been submitted!");
        executor.awaitTermination(20, TimeUnit.MINUTES);
        System.out.println("all tasks have been completed!");
    }

    @Test
    public void testProducerAndCustomerProcessor()  throws InterruptedException  {
        final ProducerAndConsumerProcessor processor = new ProducerAndConsumerProcessor();
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                processor.producer();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                processor.consumer();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(processor.isRunning());

    }

}
