package com.epam;

import com.epam.prototype.Hero;
import com.epam.prototype.Prototype;
import com.epam.prototype.Sample;
import org.junit.Assert;
import org.junit.Test;

public class PrototypeTest {

    @Test
    public void testSampleCloneObject() throws CloneNotSupportedException{
        Sample sample = new Sample("justin");
        System.out.println(sample.getName());

        Sample sampleClone = sample.clone();
        sampleClone.setName("jackson");

        Assert.assertEquals("justin",sample.getName());
        Assert.assertEquals("jackson",sampleClone.getName());
    }

    @Test
    public void testPrototypeSubClass()  throws CloneNotSupportedException{
        Prototype hero = new Hero("Ray Liao");
        Hero hero1 = (Hero) hero.copy();
        Hero hero2 = new Hero(hero1);

        System.out.println(hero);
        System.out.println(hero1);
        System.out.println(hero2);

    }
}
