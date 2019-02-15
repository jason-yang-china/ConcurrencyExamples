package com.epam.factorymethod;

public class PhoneFactory implements IFactoryMethod {
    public Phone createPhone(Type type) {
        switch (type)
        {
            case IPhone:
                return new IPhone("Apple");
            case Meizhu:
                return new Meizhu("Meizhu");
            default:
                return null;
        }

    }
}
