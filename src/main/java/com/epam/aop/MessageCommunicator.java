package com.epam.aop;

public class MessageCommunicator {
    public static void deliver(String message) {
        System.out.println(message);
    }

    public static void deliver(String person, String message) {
        System.out.println(person+", "+message);
    }
}
