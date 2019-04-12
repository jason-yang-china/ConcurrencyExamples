package com.epam;


import com.epam.observer.ImageFeed;
import com.epam.observer.People;
import com.epam.observer.Subject;
import org.junit.Test;



public class SubjectTest {

    @Test
    public void updateInfoTest() {

        People people1 = new People("Jason");
        People people2 = new People("Jack");

        Subject subject = new ImageFeed();
        subject.registerObserver(people1);
        subject.registerObserver(people2);
        subject.update("hello","man");

        subject.getObserverList().stream().forEach(System.out::println);
        subject.registerObserver( new People(("Terry")));
        subject.update("Yummy", "You are great man");
        subject.getObserverList().stream().forEach(System.out::println);

        System.out.println("some one is not going to subscribe the Subject again, he or she will not get the update again");
        subject.removeObserver(people2);
        subject.getObserverList().stream().forEach(System.out::println);

    }
}
