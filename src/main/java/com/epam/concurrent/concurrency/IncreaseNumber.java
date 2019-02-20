package com.epam.concurrent.concurrency;

public class IncreaseNumber implements Increasement{

    private int number = 0;
    public IncreaseNumber(int number){
        this.number = number;
    }
    public int getNumber() {return number;}

    public void increaseNumber() {
        this.number = this.number + 1;
    }
}
