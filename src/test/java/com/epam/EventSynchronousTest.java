package com.epam;

import com.epam.event.*;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class EventSynchronousTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventSynchronousTest.class);
    @Test
    public void testSynchronousEvent() {
        EventManager eventManager = new EventManager();
        try {
            int sEventId = eventManager.create(60);
            eventManager.start(sEventId);
            assertEquals(1, eventManager.getEventPool().size());
            assertTrue(eventManager.getEventPool().size() < EventManager.MAX_RUNNING_EVENTS);
            assertNotEquals(-1, eventManager.numOfCurrentlyRunningSyncEvent());
            eventManager.cancel(sEventId);
            assertTrue(eventManager.getEventPool().isEmpty());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
}
