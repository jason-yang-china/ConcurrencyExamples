package com.epam.adapter;

public class Warlord {
    public ShootBullet shootBullet;

    public Warlord(){
    }

    public Warlord(ShootBullet shootBullet){
        this.shootBullet = shootBullet;
    }

    public void setShootingBullet(ShootBullet shootBullet) {
        this.shootBullet = shootBullet;
    }

    public void row() {
        shootBullet.shoot();
    }
}
