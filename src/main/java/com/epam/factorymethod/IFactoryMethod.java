package com.epam.factorymethod;

public interface IFactoryMethod {
    enum Type {
        IPhone,
        Meizhu,
        Huawei
    }
     Phone createPhone(Type type);
}
