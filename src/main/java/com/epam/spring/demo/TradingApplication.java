package com.epam.spring.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class TradingApplication {


    public static void main(String[] args) {
        User user = new User("jasonyang.epam@gmail.com", "jason");

        System.out.println("Transaction open? : "+ TransactionSynchronizationManager.isActualTransactionActive());
        AnnotationConfigApplicationContext ctx = new  AnnotationConfigApplicationContext(ApplicationContextConfiguration.class);
        UserService userService = ctx.getBean(UserService.class);

        userService.register(user);
        System.out.println("Transaction open? : "+ TransactionSynchronizationManager.isActualTransactionActive());

    }
}
