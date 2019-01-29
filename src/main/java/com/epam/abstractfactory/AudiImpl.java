package com.epam.abstractfactory;

public class AudiImpl implements ICar{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AudiImpl(String name){
        this.name = name;
    }
    public void drive() {
        System.out.println("I am driving quick");
    }
}
