package com.epam.concurrent.lock;

import com.epam.concurrent.concurrency.Increasement;

import java.util.concurrent.locks.ReentrantLock;

public class IncreaseCount {


    private final ReentrantLock lock = new ReentrantLock();
    private int count = 0;

    public int getCount (){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+ ":" + count);
            return count++;
        }finally {
            lock.unlock();
        }
    }

}
