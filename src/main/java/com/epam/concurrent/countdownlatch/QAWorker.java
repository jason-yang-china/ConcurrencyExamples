package com.epam.concurrent.countdownlatch;

public class QAWorker implements Runnable {
    public void run() {
        System.out.println("Task is assigned to QA team");

//        try {
//            Thread.sleep(1000);
//        }catch (InterruptedException ex) {
//            System.out.println("exception: "+ex.getMessage());
//        } finally {
//            System.out.println("Task is completed by QA team");
//        }
        System.out.println("Task is completed by QA team");
    }
}
