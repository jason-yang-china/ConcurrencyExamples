package com.epam.concurrent.interaction;

import java.util.Scanner;

class Processor implements Runnable{
    private volatile boolean isRunning = true;

    public void run() {
        while (isRunning) {
            System.out.println("Hello Buddy");
            try {
                Thread.sleep(50);
            }catch (InterruptedException e) {
                System.out.println("InterruptedException"+e);
            }
        }
    }

    public void shutdown() {
        isRunning = false;
    }
}

public class App {
    public static void main(String[] args) {
        Processor proc = new Processor();
        Thread t1 = new Thread(proc);
        t1.start();

        System.out.println("Press return to stop");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        proc.shutdown();

    }
}