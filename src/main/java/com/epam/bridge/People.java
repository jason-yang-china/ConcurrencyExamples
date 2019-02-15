package com.epam.bridge;

public class People implements Animal{
    private Emotion emotion;
    public People(Emotion emotion) {
        this.emotion = emotion;
    }

    public void born() {
        System.out.println("People is born!");
        emotion.happy();
    }

    public void die() {
        System.out.println("People is died!");
        emotion.sad();
    }

    public Emotion getEmotion() {
        return this.emotion;
    }
}
