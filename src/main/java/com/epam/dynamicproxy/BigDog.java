package com.epam.dynamicproxy;

public class BigDog implements Dog {
    @Override
    public void walk() {
        System.out.println("I am working");
    }

    @Override
    public void talk() {
        System.out.println("I am talking");
    }
}
