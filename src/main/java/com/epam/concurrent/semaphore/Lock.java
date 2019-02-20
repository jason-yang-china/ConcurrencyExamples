package com.epam.concurrent.semaphore;

public interface Lock {
    void aquire() throws InterruptedException;;
    void release();
}
