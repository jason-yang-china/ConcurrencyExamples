package com.epam.concurrent.lock;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner  {
    private int count = 0;
    private Lock lock = new ReentrantLock();
    private Condition cond = lock.newCondition();
    private void increment(){
        for (int i=0;i<10000;i++) {
            count++;
        }
    }

    public void firstThread() throws InterruptedException{
        System.out.println("Enter into first thread");
        lock.lock();
        cond.await();
        System.out.println("Woken up");
        try {
            increment();
        }finally {
            lock.unlock();
        }
    }

    public void secondThread() throws InterruptedException{
        Thread.sleep(100);
        lock.lock();
        System.out.println("Press the return key!");
        System.out.println("Got return key!");
        cond.signal();
        try {
            System.out.println("second thread run increment method");
            increment();
        }finally {
            System.out.println("Unlocked the lock");
            lock.unlock();
        }

    }

    public void finished() {
        System.out.println("Count is "+count);
    }
}
