package com.epam.concurrent.semaphore;

public interface Lock {
    void acquire() throws InterruptedException;;
    void release();
}
