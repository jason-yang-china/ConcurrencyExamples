package com.epam;

import com.epam.lazyInitialization.Fruit;
import com.epam.lazyInitialization.FruitType;
import org.junit.Assert;
import org.junit.Test;

public class LazyInitializationTest {

    @Test
    public void showAllWithOneObject() {
        Fruit.getFruitByTypeName(FruitType.banana);
        Fruit.showAll();
        Assert.assertEquals(1, Fruit.getFruitSize());
    }

    @Test
    public void showAllWithMoreObject() {
        Fruit.getFruitByTypeName(FruitType.banana);
        Fruit.showAll();
        Fruit.getFruitByTypeName(FruitType.apple);
        Fruit.showAll();
        Fruit.getFruitByTypeName(FruitType.apple);
        Fruit.showAll();
        Assert.assertEquals(2, Fruit.getFruitSize());

       // Fruit.getFruitByTypeName(FruitType.banana);
    }
}
