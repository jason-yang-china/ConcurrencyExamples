package com.epam.bridge;

public class HappinessEmotion implements Emotion {
    public void cry() {
        System.out.println("it is crying because of happiness!");
    }

    public void happy() {
        System.out.println("it is happy because of happiness!");
    }

    public void sad() {
        System.out.println("it is not sad because of happiness!");
    }
}
