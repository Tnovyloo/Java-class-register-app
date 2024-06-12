package com.client_app.component;

public class EmailValidator {
    private String email;
    
    public EmailValidator(String email) {
        this.email = email;
    }

    public boolean checkEmailDomain(String domain) {
        if (domain.contains(getDomainFromEmail(email))) {
            System.out.println(getDomainFromEmail(email) + " == " + domain);
            return true;
        } else {
            System.out.println(getDomainFromEmail(email) + " != " + domain);
        }
        return false;
    } 

    public static String getDomainFromEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email address.");
        }

        String[] parts = email.split("@");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid email address.");
        }

        return parts[1];
    }
}
