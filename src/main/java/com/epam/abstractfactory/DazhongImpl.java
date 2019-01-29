package com.epam.abstractfactory;

public class DazhongImpl implements ICar {
    private String name;

    public DazhongImpl(String name) {
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
        return "DazhongImpl[" +
                "name='" + name + '\'' +
                ']';
    }

    public void drive() {
        System.out.println("I am driving like a big bug");
    }


}
