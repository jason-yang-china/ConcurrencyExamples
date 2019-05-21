package com.epam;

import com.epam.aop.MessageCommunicator;
import com.epam.dynamicproxy.*;
import org.junit.Test;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AspectTest {
    @Test
    public void aspectTest() {
        MessageCommunicator.deliver("Wanna learn AspectJ?");
        MessageCommunicator.deliver("Harry", "having fun?");
    }

    @Test
    public void testSimpleDynamicProxyWithMap() {

        Map map = (Map) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[] {Map.class}, new SimpleInvocationHandler());
        map.put("hello","world");

    }

    @Test
    public void testSimpleDynamicProxyWithList() {
        List list = (List) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[] {List.class}, new SimpleInvocationHandler());
        list.add("test");
    }

    @Test
    public void timingDynamicTest() {
        Map map = (Map) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[] {Map.class}, new TimingDynamicInvocationHandler(new HashMap<>()));
        map.put("hello","world");
        map.get("hello");

        List list = (List) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[] {List.class}, new TimingDynamicInvocationHandler(new ArrayList<>()));
        list.add("test");
    }

    @Test
    public void testDynamicProxy() {
        BigDog bigDog = new BigDog();
        Dog dog = (Dog)Proxy.newProxyInstance(bigDog.getClass().getClassLoader(), new Class[] {Dog.class}, new SimpleInvocationHandler());
        dog.walk();
        dog.talk();
    }

    @Test
    public void dynamicProxyTest() {
        BigDog dog = new BigDog();
        Dog logged = withLogging(dog, Dog.class);
        logged.talk();
        logged.walk();
        logged.walk();
        System.out.println(logged);
    }


    public static <T> T withLogging(T target, Class<T> tClass) {
        return (T) Proxy.newProxyInstance(tClass.getClassLoader(), new Class<?>[]{tClass}, new DynamicInvocationHandler(target));
    }

}
