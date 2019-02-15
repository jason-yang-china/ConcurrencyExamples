package com.epam.prototype;


/**
 * The prototype allows user to create copy of the existing object, and modify it to your needs,
 * instead of going through the trouble of creating an object from the scratch and setting it up.
 */
public class Hero extends Prototype {
    private String name;


    public Hero(String name) {
        this.name = name;
    }

    public Hero(Hero hero) {
        this.name = hero.name;
    }

    @Override
    public Object copy() {
        return new Hero(this);
    }

    @Override
    public String toString(){
        return "Hero's name is "+name;
    }
}
