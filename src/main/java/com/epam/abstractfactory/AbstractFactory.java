package com.epam.abstractfactory;

public interface AbstractFactory {
    enum Type {
        Jieda,
        Porscha,
        Dazhong,
        Audi
    }

    enum PhoneType {
        IPhone,
        Meizhu
    }

    ICar createCar(Type type);
    IPhone createPhone(PhoneType type);
}
