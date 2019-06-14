package com.epam.spring.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;


@Repository
public class UserDao {

    private final DataSource dataSource;

    @Autowired
    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public void save(User user) {
        System.out.println("saving user to the database via datasource "+ dataSource+", user.getEmail() = "+user.getEmail());
    }
}
