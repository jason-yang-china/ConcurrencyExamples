package com.epam.concurrent.concurrency;


public class SyncIncreaseNumber implements Increasement {

    private int number;

    public SyncIncreaseNumber(int number){
        this.number = number;
    }
    public synchronized void increaseNumber() { this.number = this.number + 1;}

    public int getNumber(){
        return this.number;
    }

}
