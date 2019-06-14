package com.epam.spring.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import javax.xml.crypto.Data;


@Component
public class UserService {

    @Autowired
    private MailService mailService;
    @Autowired
    private UserDao userDao;

    @Autowired
    private DataSource dataSource;

    @Transactional
    public void register(User user) {
        System.out.println("Transaction open? : "+ TransactionSynchronizationManager.isActualTransactionActive());
        //new MailService("localhost", 463, "magic@smtp.com").sendWelcomeEmail(user);

        new UserDao(dataSource).save(user);
        System.out.println("Transaction open? : "+ TransactionSynchronizationManager.isActualTransactionActive());
    }

    public void closeAccount(User user) {
        System.out.println("Open up a database connection");

        System.out.println("Close database connection");

    }
}
