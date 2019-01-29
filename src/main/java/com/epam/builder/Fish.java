package com.epam.builder;



public class Fish {
    private boolean doubleEyes;
    private boolean seaFish;
    private String name;

    public boolean isDoubleEyes() {
        return doubleEyes;
    }

    public void setDoubleEyes(boolean doubleEyes) {
        this.doubleEyes = doubleEyes;
    }

    public boolean isSeaFish() {
        return seaFish;
    }

    public void setSeaFish(boolean seaFish) {
        this.seaFish = seaFish;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fish fish = (Fish) o;
        return doubleEyes == fish.doubleEyes &&
                seaFish == fish.seaFish &&
                name == fish.name;
    }

    @Override
    public String toString() {
        return "Fish[" +
                "doubleEyes=" + doubleEyes +
                ", seaFish=" + seaFish +
                ", name='" + name + '\'' +
                ']';
    }
}
