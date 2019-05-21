package com.epam;


import com.epam.grow.Person;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;

import static junit.framework.TestCase.assertTrue;

public class SerializableAndDeserializableTest {
    @Test
    public void whenSerializableTheSameObject() throws IOException,ClassNotFoundException {
        Person person = new Person();
        person.setAge(10);
        person.setName("Joe");

        FileOutputStream fileOutputStream = new FileOutputStream("myfile.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(person);
        objectOutputStream.flush();
        objectOutputStream.close();

        FileInputStream fileInputStream = new FileInputStream("myfile.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Person p2 = (Person)objectInputStream.readObject();
        objectInputStream.close();

        assertTrue(p2.getName().equals(person.getName()));
        assertTrue(p2.getAge()==person.getAge());

    }
}
