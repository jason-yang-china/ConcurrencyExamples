package com.epam.adapter;

public class WaterGunAdpter implements ShootBullet {
    private WaterGun waterGun;
    public WaterGunAdpter() {
        waterGun = new WaterGun();
    }

    public void shoot() {
        waterGun.shootWater();;
    }
}
