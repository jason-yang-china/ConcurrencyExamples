package com.epam.abstractfactory;

public class ApplePhone implements IPhone {
    private String name;

    public ApplePhone(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void calling() {
        System.out.println("I am Iphone");
    }
}
