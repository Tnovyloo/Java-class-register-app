package com.example.class_register_server.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String studentName;
    private String subject;
    private String grade;
    private String studentIndex;

    public Grade() {
    }

    public Grade(String studentName, String subject, String grade, String index) {
        this.studentName = studentName;
        this.subject = subject;
        this.grade = grade;
        this.studentIndex = index;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setStudentIndex(String index) {
        this.studentIndex = index;
    }

    public String getStudentIndex() {
        return this.studentIndex;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", studentIndex='" + studentIndex + '\'' +
                ", studentName='" + studentName + '\'' +
                ", subject='" + subject + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}
