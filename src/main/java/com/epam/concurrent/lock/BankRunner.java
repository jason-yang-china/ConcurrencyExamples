package com.epam.concurrent.lock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankRunner {
    private Account account1 = new Account();
    private Account account2 = new Account();
    private Random random = new Random();

    private Object obj1 = new Object();
//    private Object obj2 = new Object();
//
//    private Lock lock1 = new ReentrantLock();
//    private Lock lock2 = new ReentrantLock();

    private void acquireLocks(Lock firstLock, Lock secondlock) throws InterruptedException{
        boolean gotFirstLock = false;
        boolean gotSecondLock = false;


        while (true) {
            //Acquire locks

            try {
                gotFirstLock = firstLock.tryLock();
                gotSecondLock = secondlock.tryLock();

            }finally {
                if(gotFirstLock && gotSecondLock) {
                    return;
                }
                if(gotFirstLock) {
                    firstLock.unlock();
                }
                if(gotSecondLock) {
                    secondlock.unlock();
                }
            }
            Thread.sleep(1);
        }
    }

    public void thread1() throws InterruptedException {

        synchronized (obj1) {
            for (int i = 0; i < 10000; i++) {
//            acquireLocks(lock1,lock2);
//            lock1.lock();
//            lock2.lock();
                try {
                    Account.transfer(account1, account2, random.nextInt(100));
                } finally {
                    //lock1.unlock();
                    //lock2.unlock();
                }
            }
        }

    }

    public  void thread2() throws InterruptedException {
        synchronized (obj1) {
            for (int i = 0; i < 10000; i++) {
//            acquireLocks(lock1,lock2);
//            lock1.lock();
//            lock2.lock();
                try {
                    Account.transfer(account2, account1, random.nextInt(100));
                } finally {
                    //lock1.lock();
                    //lock2.lock();
                }
            }
        }
    }

    public void totalAmount() {
        System.out.println("account 1 balance : "+account1.getBalance());
        System.out.println("account 2 balance :"+account2.getBalance());
        int amount = account1.getBalance() + account2.getBalance();
        System.out.println("total balance :"+amount);
    }
}
