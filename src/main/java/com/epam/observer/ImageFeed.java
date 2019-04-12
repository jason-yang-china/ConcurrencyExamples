package com.epam.observer;

import java.util.ArrayList;
import java.util.List;

public class ImageFeed implements Subject{

    private List<Observer> list;

    public ImageFeed() {
        list = new ArrayList<Observer>();
    }

    public void registerObserver(Observer observer) {
        this.list.add(observer);
    }

    public void removeObserver(Observer observer) {
        this.list.remove(observer);
    }

    @Override
    public void update(String title, String content) {
        list.stream().forEach(o->o.update(title,content));
    }

    public List<Observer> getObserverList(){
        return list;
    }
}
