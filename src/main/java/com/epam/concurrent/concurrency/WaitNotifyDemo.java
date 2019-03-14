package com.epam.concurrent.concurrency;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WaitNotifyDemo {

    // Note: this sample (being a toy application) doesn't handle InterruptedException properly.
    // See: http://www.ibm.com/developerworks/java/library/j-jtp05236.html

    private static final Logger log = Logger.getLogger(WaitNotifyDemo.class.getName());

    public static void main(String[] args) {
        runPoliteTest();
        //runBusyTest();
    }

    private static void runPoliteTest() {

        log.info("Running test with polite consumers...");

        Queue<String> queue = new LinkedList<String>();
        Thread producer = new Thread(new ProducerThread(10, queue), "Producer-for-PoliteTest");
        Thread consumerA = new Thread(new PoliteConsumerThread("PoliteConsumer A", queue), "PoliteConsumer A");
        Thread consumerB = new Thread(new PoliteConsumerThread("PoliteConsumer B", queue), "PoliteConsumer B");

        consumerA.setDaemon(true);
        consumerB.setDaemon(true);

        producer.start();
        consumerA.start();
        consumerB.start();

        try {
            producer.join();
        } catch (InterruptedException e) {
            // NOTE: production applications shouldn't do this...
            log.log(Level.WARNING, "interrupted waiting producer to join", e);
        }
    }

    private static void runBusyTest() {

        log.info("Running test with busy consumers. Get ready to hear those CPU fans go!");

        Queue<String> queue = new LinkedList<String>();
        Thread producer = new Thread(new ProducerThread(10, queue), "Producer-for-BusyTest");
        Thread consumerA = new Thread(new BusyConsumerThread("BusyConsumer A", queue), "BusyConsumer A");
        Thread consumerB = new Thread(new BusyConsumerThread("BusyConsumer B", queue), "BusyConsumer B");

        consumerA.setDaemon(true);
        consumerB.setDaemon(true);

        producer.start();
        consumerA.start();
        consumerB.start();

        try {
            producer.join();
            consumerA.join();
            consumerB.join();

        } catch (InterruptedException e) {
            // NOTE: production applications shouldn't do this...
            log.log(Level.WARNING, "interrupted waiting producer to join", e);
        }
    }

    private static class ProducerThread implements Runnable {
        private final Queue<String> queue;
        private final int duration;

        private ProducerThread(int duration, Queue<String> queue) {
            this.queue = queue;
            this.duration = duration;
        }

        @Override
        public void run() {
            log.info("Starting...");
            for(int i = 0; i < duration; i++) {

                String line = readLineFromFile(i);
                synchronized (queue) {
                    queue.add(line);
                    queue.notifyAll();
                }
            }
            log.info("Done.");
        }

        private String readLineFromFile(int i) {
            try {
                log.info("Reading line from file...");
                Thread.sleep(TimeUnit.MILLISECONDS.toMillis(1));
            } catch (InterruptedException e) {
                log.log(Level.WARNING, "interrupted waiting for next line from file", e);
            }
            return "LINE #" + i;
        }
    }

    private static class BusyConsumerThread implements Runnable {

        private final String name;
        private final Queue<String> queue;

        private BusyConsumerThread(String name, Queue<String> queue) {
            this.name = name;
            this.queue = queue;
        }

        @Override
        public void run() {
            while(true) {
                synchronized (queue) {
                    String line = queue.poll();
                    if(line != null) {
                        log.info(name + " got message: " + line);
                    }
                }
            }
        }
    }


    private static class PoliteConsumerThread implements Runnable {

        private final String name;
        private final Queue<String> queue;

        private PoliteConsumerThread(String name, Queue<String> queue) {
            this.name = name;
            this.queue = queue;
        }

        @Override
        public void run() {
            while(true) {

                String line;

                synchronized (queue) {
                    while( (line = queue.poll()) == null) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            log.log(Level.WARNING, "interrupted waiting for a line from the queue", e);
                        }
                    }
                }

                log.info(name + " got message: " + line);
            }
        }
    }

}

