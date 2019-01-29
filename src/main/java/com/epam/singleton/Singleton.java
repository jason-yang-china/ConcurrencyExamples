package com.epam.singleton;

public class Singleton {


    private static Singleton instance = null;

    public static Singleton getInstance() {
            synchronized (Singleton.class) {
                if(instance == null) {
                    instance = new Singleton();
                }
            }
        return instance;
    }

    @Override
    public String toString() {
        return "I am Singleton";
    }
}
