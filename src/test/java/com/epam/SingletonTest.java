package com.epam;

import com.epam.singleton.Singleton;
import org.junit.Test;
import org.junit.Assert;

public class SingletonTest {

    @Test
    public void compareTwoObjectsAreEqualsTest() {
        Singleton mySingleton1 = Singleton.getInstance();
        Singleton mySingleton2 = Singleton.getInstance();
        Assert.assertNotEquals(mySingleton1, null);
        Assert.assertNotEquals(mySingleton2, null);
        Assert.assertEquals(mySingleton1, mySingleton2);
    }

    @Test
    public void checkHashCodeForTwoObjects() {
        Singleton mySingleton1 = Singleton.getInstance();
        Singleton mySingleton2 = Singleton.getInstance();
        Assert.assertNotEquals(mySingleton1, null);
        Assert.assertNotEquals(mySingleton2, null);
        Assert.assertEquals(mySingleton1.toString(),mySingleton2.toString());
        Assert.assertEquals(mySingleton1.hashCode(), mySingleton2.hashCode());
    }

    @Test
    public void checkToStringMethod() {
        Singleton mySingleton1 = Singleton.getInstance();
        Singleton mySingleton2 = Singleton.getInstance();
        Assert.assertEquals(mySingleton1.toString(),"I am Singleton");
        Assert.assertEquals(mySingleton2.toString(),"I am Singleton");
    }

}
