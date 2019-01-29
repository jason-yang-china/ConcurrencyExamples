package com.epam;

import com.epam.builder.Builder;
import com.epam.builder.Fish;
import com.epam.builder.FishBuilder;
import org.junit.Assert;
import org.junit.Test;

public class BuilderTest {

    @Test
    public void buildFishTest() {
        Builder builder = new FishBuilder();
        Fish fish = builder.setDoubleEyes(true).setName("Salon").setSeaFish(false).build();
        Assert.assertNotEquals(fish, null);
    }

    @Test
    public void buildMultipleFishsTest() {

        Builder builder = new FishBuilder();
        Fish fish1 = builder.setDoubleEyes(true).setName("Salon").setSeaFish(false).build();
        Fish fish2 = builder.setDoubleEyes(false).setName("lampreys").setSeaFish(true).build();
        Builder builder2 = new FishBuilder();
        Fish fish3 = builder2.setName("newFish").build();
        Assert.assertNotEquals(fish1, null);
        Assert.assertNotEquals(fish2, null);
        Assert.assertNotEquals(fish3, null);
        System.out.println(fish1);
        System.out.println(fish2);
        System.out.println(fish3);

        Assert.assertEquals("Salon", fish1.getName());
        Assert.assertEquals("lampreys", fish2.getName());
    }
}
