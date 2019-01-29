package com.epam.builder;

public class FishBuilder implements Builder {

    private Fish fish;
    public FishBuilder(){
        fish = new Fish();
    }
    public Fish build() {
        Fish tmpfish = new Fish();
        tmpfish.setName(fish.getName());
        tmpfish.setSeaFish(fish.isSeaFish());
        tmpfish.setDoubleEyes(fish.isDoubleEyes());
        return tmpfish;
    }

    public Builder setDoubleEyes(final boolean doubleEyes) {
         fish.setDoubleEyes(doubleEyes);
         return this;
    }

    public Builder setSeaFish(final boolean seaFish) {
        fish.setSeaFish(seaFish);
        return this;
    }

    public Builder setName(final String name) {
        fish.setName(name);
        return this;
    }

}
