package com.epam;

import com.epam.poolobject.MyObject;
import com.epam.poolobject.PooledObjects;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class ObjectPoolTest {

    @Test
    public void getObjectFromPoolTest() throws InterruptedException {
        MyObject obj = PooledObjects.getObject();
        Thread.sleep(3000);
        MyObject obj2 = PooledObjects.getObject();
        Assert.assertNotEquals(obj,obj2);
    }

    @Test
    public void releaseObjectBackToPoolTest() throws InterruptedException {
        MyObject obj = PooledObjects.getObject();
        PooledObjects.releaseObject(obj);
        Thread.sleep(1000); //sleep only check the time span less than expire time
        MyObject obj1 = PooledObjects.getObject();
        Assert.assertEquals(obj, obj1);
    }

    @Test
    public void releaseObjectBackToPoolWithExpiredTimeTest() throws InterruptedException {
        MyObject obj = PooledObjects.getObject();
        PooledObjects.releaseObject(obj);
        Thread.sleep(6000); //sleep only check the expire time
        MyObject obj1 = PooledObjects.getObject();
        Assert.assertNotEquals(obj, obj1);
    }

    @Test
    public void checkInuseObjectsFromThePool() throws InterruptedException {
        MyObject obj1 = PooledObjects.getObject();
        Thread.sleep(1); //sleep only check the expire time
        MyObject obj2 = PooledObjects.getObject();
        Thread.sleep(2); //sleep only check the expire time
        MyObject obj3 = PooledObjects.getObject();

        Map<MyObject, Long> map = PooledObjects.getInusedMap();
        Assert.assertEquals(map.containsKey(obj1), true);
        Assert.assertEquals(map.containsKey(obj2), true);
        Assert.assertEquals(map.containsKey(obj3), true);
    }

    @Test
    public void checkInuseObjectsAndReleaseOneFromThePool() {

        MyObject obj1 = PooledObjects.getObject();
        MyObject obj2 = PooledObjects.getObject();
        MyObject obj3 = PooledObjects.getObject();
        PooledObjects.releaseObject(obj1);

        Map<MyObject, Long> map = PooledObjects.getInusedMap();
        Assert.assertEquals(map.containsKey(obj1), false);
        Assert.assertEquals(map.containsKey(obj2), true);
        Assert.assertEquals(map.containsKey(obj3), true);
    }
}
