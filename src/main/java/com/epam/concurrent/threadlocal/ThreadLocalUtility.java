package com.epam.concurrent.threadlocal;

/**
 * Utility class simplifies the manner in which ThreadLocal (of String payload types) are stored, accessed and cleared.
 *
 * @author imamchishty
 */
public class ThreadLocalUtility {

    private static final ThreadLocal<String> local = new ThreadLocal<>();
    public static void set(String requestId) {
        local.set(requestId);
    }
    public static String get() {
        return local.get();
    }

    public static void clear(){
        local.remove();
    }

}