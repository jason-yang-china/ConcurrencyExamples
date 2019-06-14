package com.epam.spring.demo;

import org.h2.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

@Configuration
@ComponentScan
@EnableTransactionManagement
public class ApplicationContextConfiguration {

//    @Bean
//    public UserDao userDao() {
//        return new UserDao();
//    }
//
    @Bean MailService mailService() {
        return new MailService("localhost", 463, "Jason");
    }


    @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource ds = new SimpleDriverDataSource();
        ds.setDriver(new Driver());
        ds.setUrl("jdbc:h2:mem:");
        return ds;
    }

}
