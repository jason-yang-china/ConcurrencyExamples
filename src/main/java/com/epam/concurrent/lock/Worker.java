package com.epam.concurrent.lock;

import com.epam.concurrent.interaction.App2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker {

    private Random random = new Random();
    private List<Integer> list1 = new ArrayList<Integer>();
    private List<Integer> list2 = new ArrayList<Integer>();

    private Object obj1 = new Object();
    private Object obj2 = new Object();

    public void stageOne() {
       synchronized (obj1) {
            try {
                Thread.sleep(1);
            }catch (InterruptedException ex) {

            }
            list1.add(random.nextInt(100));
        }
    }

    public void stageTwo() {
        synchronized (obj2) {
            try {
                Thread.sleep(1);
            }catch (InterruptedException ex) {

            }
            list2.add(random.nextInt(100));
        }
    }

    public void process() {
        for (int i=0;i<2000;i++) {
            stageOne();
            stageTwo();
        }
    }

    public static void main(String[] args) {
        final Worker worker = new Worker();
        long start = System.currentTimeMillis();
        //worker.process();
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                worker.process();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                worker.process();
            }
        });
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        }catch (InterruptedException ex) {

        }
        long end = System.currentTimeMillis();

        System.out.println("the period to run process is "+(end - start));
        System.out.println("List1: "+worker.list1.size()+"List2: "+worker.list2.size());
    }

}
