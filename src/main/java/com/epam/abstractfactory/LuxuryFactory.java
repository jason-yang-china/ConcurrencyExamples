package com.epam.abstractfactory;

public class LuxuryFactory implements  AbstractFactory {
    public ICar createCar(Type type){
        ICar car = null;
        switch (type) {
            case Audi:
                car = new AudiImpl("Audi");
                break;
            case Porscha:
                car = new PorschaImpl("Porscha");
                break;

            default:
                break;
        }
        return car;

    }
}
