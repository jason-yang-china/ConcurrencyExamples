package com.epam.event;

public class LongRunningEventException extends Exception{
    public LongRunningEventException(String message) {
        super(message);
    }
}
