package com.epam.abstractfactory;

public class PorschaImpl implements ICar{
    private String name;
    public PorschaImpl(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PorschaImpl[" +
                "name='" + name + '\'' +
                ']';
    }

    public void drive() {
        System.out.println("I am driving fast");
    }
}
