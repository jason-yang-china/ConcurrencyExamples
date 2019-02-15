package com.epam.event;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class EventManager implements ThreadCompleteListener {

    public static final int MAX_RUNNING_EVENTS = 1000; //Just don't wanna have to many events. :)
    public static final int MAX_EVENT_TIME = 1800;
    private int currentlyRunningSyncEvent = -1;
    public static final int MIN_ID = 1;
    public static final int MAX_ID = MAX_RUNNING_EVENTS;

    private Random rand;
    private Map<Integer, Event> eventPool;


    public EventManager() {
        rand = new Random(1);
        eventPool = new ConcurrentHashMap<Integer, Event>(MAX_RUNNING_EVENTS);
    }

    /**
     *
     * @param eventTime
     * @return
     */
    public int create(int eventTime) throws MaxNumOfEventsAllowedException, InvalidOperationException, LongRunningEventException  {
        if(currentlyRunningSyncEvent != -1) {
            throw new InvalidOperationException("Event ["+currentlyRunningSyncEvent+"] is still running. Please wait until it finishes and try again");
        }

        int eventId = createEvent(eventTime, true);
        currentlyRunningSyncEvent = eventId;

        return eventId;
    }

    public int createAsyncEvent(int eventTime) throws MaxNumOfEventsAllowedException, LongRunningEventException {

        int eventId = createEvent(eventTime, false);
        return eventId;
    }

    private int createEvent(int eventTime, boolean isSynchronous) throws MaxNumOfEventsAllowedException, LongRunningEventException {
        if(eventPool.size() == MAX_RUNNING_EVENTS) {
            throw new MaxNumOfEventsAllowedException("Too many events are running at the moment. Please try it again later.");
        }
        if(eventTime >= MAX_EVENT_TIME) {
            throw new LongRunningEventException("Maximum event time allowed is "+MAX_EVENT_TIME+" seconds, please try again");
        }
        int newEventId = generateId();
        Event newEvent = new Event(newEventId, eventTime, isSynchronous);
        newEvent.addListener(this);

        eventPool.put(newEventId, newEvent);
        return newEventId;
    }
    public void completedEventHandler(int eventId) {
        eventPool.remove(eventId);
    }

    public void start(int eventId) throws EventDoesNotExistException {
        if(!eventPool.containsKey(eventId)){
            throw new EventDoesNotExistException(eventId + "does not exist. ");
        }
        eventPool.get(eventId).start();
    }

    public void cancel(int eventId) throws EventDoesNotExistException {
        if(!eventPool.containsKey(eventId)){
            throw new EventDoesNotExistException(eventId + "does not exist. ");
        }
        eventPool.get(eventId).stop();
        eventPool.remove(eventId);
    }

    public void status(int eventId) throws EventDoesNotExistException {
        if(!eventPool.containsKey(eventId)){
            throw new EventDoesNotExistException(eventId + "does not exist. ");
        }
        eventPool.get(eventId).status();
    }

    public Map<Integer, Event> getEventPool() {
        return eventPool;
    }

    public int numOfCurrentlyRunningSyncEvent() {
        return currentlyRunningSyncEvent;
    }
    /**
     * Returns a pseudo-random number between min and max, inclusive. The difference between min and max can be at most
     * <code>Integer.MAX_VALUE - 1</code>.
     */
    private int generateId() {
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((MAX_ID - MIN_ID) + 1) + MIN_ID;
        while (eventPool.containsKey(randomNum)) {
            randomNum = rand.nextInt((MAX_ID - MIN_ID) + 1) + MIN_ID;
        }

        return randomNum;
    }
}
