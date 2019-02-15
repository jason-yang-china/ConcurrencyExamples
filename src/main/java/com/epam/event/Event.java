package com.epam.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Event implements Runnable,IEvent {
    private static final Logger LOGGER = LoggerFactory.getLogger(Event.class);
    private int eventId;
    private int eventTime;
    private boolean isSynchronous;
    private Thread thread;

    private boolean isComplete = false;
    private ThreadCompleteListener eventListener;

    public Event(final int eventId, final int eventTime, final boolean isSynchronous) {
        this.eventId = eventId;
        this.eventTime = eventTime;
        this.isSynchronous = isSynchronous;
    }
    public void start() {
        thread = new Thread(this);
        thread.start();
   }

   public void stop() {
        if(null == thread) {
            return;
        }
        thread.interrupt();
   }

   public void status() {
       if (!isComplete) {
           LOGGER.info("[{}] is not done.", eventId);
       } else {
           LOGGER.info("[{}] is done.", eventId);
       }
   }

   public void run() {
       long currentTime = System.currentTimeMillis();
       long endTime = currentTime + (eventTime * 1000);
       while (System.currentTimeMillis() < endTime) {
           try {
               System.out.println("I am asleep");
               Thread.sleep(1000); // Sleep for 1 second.
           } catch (InterruptedException e) {
               return;
           }
       }
       isComplete = true;
       completed();
   }
    public final void addListener(final ThreadCompleteListener listener) {
        this.eventListener = listener;
    }
    private final void completed() {
        if (eventListener != null) {
            eventListener.completedEventHandler(eventId);
        }
    }
}
