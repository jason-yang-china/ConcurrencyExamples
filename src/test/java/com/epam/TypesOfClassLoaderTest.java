package com.epam;

import com.sun.javafx.util.Logging;
import org.junit.Test;

import java.util.ArrayList;

public class TypesOfClassLoaderTest {

    @Test
    public void typeOfClassLoaderTest() {
        System.out.println(this.getClass().getClassLoader());
        System.out.println(Logging.class.getClassLoader());
        System.out.println(ArrayList.class.getClassLoader());
    }

    @Test
    public void classForNameTest() throws InstantiationException,IllegalAccessException{
        try {
            Class loader = Class.forName("com.epam.TypesOfClassLoaderTest1");
            TypesOfClassLoaderTest test = (TypesOfClassLoaderTest)loader.newInstance();
            System.out.println(loader);
            System.out.println(test);
        }catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException "+e);
        }
    }

    public void findClassTest() {

    }
}
