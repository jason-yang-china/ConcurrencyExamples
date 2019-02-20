package com.epam;

import com.epam.concurrent.concurrency.IncreaseNumber;
import com.epam.concurrent.concurrency.IncreaseNumberRunnable;
import com.epam.concurrent.concurrency.SyncIncreaseNumber;
import com.epam.concurrent.lock.IncreaseCount;
import org.junit.Assert;
import org.junit.Test;



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
}
