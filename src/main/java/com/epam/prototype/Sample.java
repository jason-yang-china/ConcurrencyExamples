package com.epam.prototype;

import sun.java2d.pipe.SpanShapeRenderer;

public class Sample implements Cloneable{
    private String name;

    public Sample(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Sample clone() throws CloneNotSupportedException {
        return new Sample(name);
    }
}
