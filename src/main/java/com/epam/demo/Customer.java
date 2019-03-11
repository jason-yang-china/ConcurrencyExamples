package com.epam.demo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Customer {
    private BlockingQueue<Customer> queue1;
    private BlockingQueue<Customer> queue2;
    private Account account;
    private double amount;
    private String name;
    private TransactionType type;

    enum TransactionType{
        BUY,SELL
    }
    public Customer(String name, Account account, double amount, TransactionType type) {
        this.name = name;
        this.account = account;
        this.amount = amount;
        this.type= type;
    }



    class CustomerMananger extends Thread {

        private Customer customer;
        public CustomerMananger(Customer customer) {
            this.customer = customer;
        }
        public void run() {
            while(true) {
                try {
                    if (type == TransactionType.BUY) {
                        queue1.put(customer);
                    } else {
                        queue2.put(customer);
                    }

                    if(queue1.size()==0||queue2.size()==0) {
                        break;
                    }
                }
                catch (InterruptedException ex) {
                }
            }
        }
    }


    class Trading extends Thread {


        public void sellOrBuy(Account account, double amount, TransactionType type) {
            if (type == TransactionType.BUY) {
                account.deposit(amount);
            } else if(type == TransactionType.SELL){
                account.withdraw(amount);
            }
        }

        public String toString() {
            return "Customer "+name+ "'s balance is "+account.getBalance();
        }
    }




}
