package com.epam.factorymethod;

public class IPhone implements Phone {
    private String name;
    public IPhone(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void calling() {
        System.out.println("IPhone");
    }
}
