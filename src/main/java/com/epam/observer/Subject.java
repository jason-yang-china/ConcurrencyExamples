package com.epam.observer;

import java.util.List;

public interface Subject {
    void update(String title, String content);
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    List<Observer> getObserverList();
}
