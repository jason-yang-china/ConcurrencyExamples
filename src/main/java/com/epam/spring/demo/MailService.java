package com.epam.spring.demo;



public class MailService {

    private final String hostname;
    private final Integer port;
    private final String username;

    public MailService(String hostname, Integer port, String username) {
        this.hostname = hostname;
        this.port = port;
        this.username = username;
    }
    public void sendWelcomeEmail(User user) {
        System.out.println("Sending out welcome email to user :"+user.getName()+", your email is "+user.getEmail());
    }
}
