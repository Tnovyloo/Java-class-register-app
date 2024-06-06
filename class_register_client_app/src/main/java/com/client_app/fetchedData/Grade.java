package com.client_app.fetchedData;


public class Grade {

    private String id;
    private String studentIndex;
    private String studentName;
    private String subject;
    private String grade;
    private String assessingTeacher;
    private String description;

    public Grade(String id, String studentIndex, String studentName, String subject, String grade, String description) {
        this.id = id;
        this.studentIndex = studentIndex;
        this.studentName = studentName;
        this.subject = subject;
        this.grade = grade;
        this.description = description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
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

    public String getAssessingTeacher() {
        return assessingTeacher;
    }

    public void setAssessingTeacher(String assessingTeacher) {
        this.assessingTeacher = assessingTeacher;
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

