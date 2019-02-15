package com.epam;

import com.epam.abstractfactory.PorschaImpl;
import com.epam.factorymethod.IFactoryMethod;
import com.epam.factorymethod.IPhone;
import com.epam.factorymethod.Phone;
import com.epam.factorymethod.PhoneFactory;
import org.junit.Assert;
import org.junit.Test;

public class FactoryMethodTest {

    @Test
    public void createApplePhoneByFactoryMethod(){
        PhoneFactory factory = new PhoneFactory();
        Phone phone =  factory.createPhone(IFactoryMethod.Type.IPhone);
        IPhone myPhone = (IPhone)phone;
        Assert.assertEquals("Apple", myPhone.getName());

    }

    @Test
    public void createMeizhuPhoneByFactoryMethod() {
        PhoneFactory factory = new PhoneFactory();
        Phone phone =  factory.createPhone(IFactoryMethod.Type.Meizhu);
    }
}
