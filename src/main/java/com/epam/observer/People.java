package com.epam.observer;

public class People implements  Observer {

    private String name;
    private String content;
    private String title;

    public People(String name) {
        this.name = name;
    }
    @Override
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return name+" get title "+title+", get content "+content;
    }
}
