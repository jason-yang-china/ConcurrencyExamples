package com.epam.builder;

public interface Builder {
    Fish build();
    Builder setDoubleEyes(final boolean doubleEyes);
    Builder setName(final String name);
    Builder setSeaFish(final boolean seaFish);

}
