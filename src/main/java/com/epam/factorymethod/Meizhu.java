package com.epam.factorymethod;

public class Meizhu implements Phone {
    private String name;

    public Meizhu(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void calling() {
        System.out.println("Meizhu");
    }
}
