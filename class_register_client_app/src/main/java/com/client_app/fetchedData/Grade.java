package com.client_app.fetchedData;


public class Grade {
    private String studentIndex;
    private String studentName;
    private String subject;
    private String grade;

    public Grade(String studentIndex, String studentName, String subject, String grade) {
        this.studentIndex = studentIndex;
        this.studentName = studentName;
        this.subject = subject;
        this.grade = grade;
    }

    public String getStudentIndex() {
        return this.studentIndex;
    }

    public void setStudentIndex(String studentIndex) {
        this.studentIndex = studentIndex;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    
    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGrade() {
        return this.grade;
    }
}

