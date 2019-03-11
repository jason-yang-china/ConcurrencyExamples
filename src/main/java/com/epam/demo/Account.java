package com.epam.demo;

public class Account {

    private double balance = 10.00;

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }

    public static void transfer(Account account1, Account account2, double amount) {
        account1.withdraw(amount);
        account2.deposit(amount);
    }

    public double getBalance() {
        return this.balance;
    }
}
