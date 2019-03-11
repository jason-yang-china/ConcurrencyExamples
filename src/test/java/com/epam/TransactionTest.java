package com.epam;

import com.epam.demo.Customer;
import org.junit.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class TransactionTest {

    @Test
    public void transactionTest() {
        BlockingQueue<Customer> sellQueue = new LinkedBlockingDeque<Customer>();
        BlockingQueue<Customer> buyQueue = new LinkedBlockingDeque<Customer>();



    }

}
