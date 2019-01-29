package com.epam.abstractfactory;

public class CarImpl implements ICar {
    private String name;

    public CarImpl(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void drive() {
        System.out.println("I am driving now");
    }
}
