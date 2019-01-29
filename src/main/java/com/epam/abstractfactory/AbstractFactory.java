package com.epam.abstractfactory;

public interface AbstractFactory {
    enum Type {
        Jieda,
        Porscha,
        Dazhong,
        Audi
    }
    ICar createCar(Type type);
}
