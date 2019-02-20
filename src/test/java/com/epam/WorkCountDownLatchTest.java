package com.epam;

import com.epam.concurrent.countdownlatch.DevWorker;
import com.epam.concurrent.countdownlatch.QAWorker;
import com.epam.concurrent.countdownlatch.WorkerRunnable;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class WorkCountDownLatchTest {


    @Test
    public void countDownLatchTest() throws InterruptedException{
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Thread devWorker1 = new Thread(new DevWorker(countDownLatch, "DevA"));
        Thread devWorker2 = new Thread(new DevWorker(countDownLatch, "DevB"));

        devWorker1.start();
        devWorker2.start();

        countDownLatch.await();
        Thread qaWorker = new Thread(new QAWorker());
        qaWorker.start();

    }

    @Test
    public  void countDownTest() throws InterruptedException{
        CountDownLatch countDownLatch = new CountDownLatch(4);
        for(int i =0; i<4;i++) {
            Thread thread = new Thread(new WorkerRunnable(countDownLatch, i));
            thread.start();
        }
        countDownLatch.await();
        Thread qaWorker = new Thread(new QAWorker());
        qaWorker.start();
    }
}
