package com.epam;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.lang.reflect.Method;

public class CGlibTest {

    @Test
    public void introduction_to_cglib() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Person.class);
//        enhancer.setCallback(new FixedValue() {
//            @Override
//            public Object loadObject() throws Exception {
//                return "Hello Jim";
//            }
//        });
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                if(method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
                    return "Hello Jim";
                } else {
                    return methodProxy.invokeSuper(o, objects);
                }
            }
        });
        Person proxy = (Person) enhancer.create();
        System.out.println(proxy.sayHello("Leo"));
        System.out.println(proxy.getAge());


    }

    static class Person {
        public String sayHello(String name) {
            return "Hello "+name;
        }

        public int getAge() {
            return 10;
        }
    }
}
