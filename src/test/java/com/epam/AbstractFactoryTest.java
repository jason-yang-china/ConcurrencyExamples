package com.epam;

import com.epam.abstractfactory.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * The client uses Factory to create car, it has two kind of cars, normal and luxury, create different kind of car through different factory
 */
public class AbstractFactoryTest {

    @Test
   public void createJiedaCar() {
       AbstractFactory factory = new CarFactory();
       ICar car = factory.createCar(AbstractFactory.Type.Jieda);
        CarImpl impl=(CarImpl)car;
        Assert.assertEquals("Jieda", impl.getName());
   }

   @Test
   public void createPorscheOrAudiCar() {
       AbstractFactory factory = new LuxuryFactory();
       ICar car = factory.createCar(AbstractFactory.Type.Porscha);
       PorschaImpl impl = (PorschaImpl)car;
       Assert.assertEquals("Porscha", impl.getName());
       ICar audiCar = factory.createCar(AbstractFactory.Type.Audi);
       if(audiCar instanceof AudiImpl) {
           AudiImpl audiCarImpl = (AudiImpl) audiCar;
           Assert.assertEquals("Audi", audiCarImpl.getName());
       }
   }
}
