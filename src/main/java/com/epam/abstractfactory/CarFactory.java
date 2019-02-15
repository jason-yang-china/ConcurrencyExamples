package com.epam.abstractfactory;

public class CarFactory implements AbstractFactory {


    public ICar createCar(Type type) {

        ICar car = null;
        switch (type) {
            case Jieda:
                car = new CarImpl("Jieda");
                break;
            case Porscha:
                car = new PorschaImpl("Porscha");
                break;
            case Dazhong:
                car = new DazhongImpl("Dazhou");
                break;
            default:
                break;
        }
        return car;

    }

    public IPhone createPhone(PhoneType type) {
        IPhone phone = null;
        switch (type) {
            case Meizhu:
                phone = new ApplePhone("Meizhu");
                break;
            default:
                break;
        }
        return phone;
    }
}

