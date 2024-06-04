package com.example.class_register_server.model.request;

public class LoginReq {
    private String email;
    private String password;
    private String studentIndex;

    public LoginReq(String email, String password, String studentIndex) {
        this.email = email;
        this.password = password;
        this.studentIndex = studentIndex;
    }

    // TODO LoginReq with StudentIndex to make queries and also save it on Login.
    // public LoginReq(String email, String password, String studentIndex) {
    //     this.email = email;
    //     this.password = password;
    //     this.studentIndex = studentIndex;
    // }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStudentIndex(String studentIndex) {
        this.studentIndex = studentIndex;
    }

    public String getStudentIndex() {
        return studentIndex;
    }
    
}
