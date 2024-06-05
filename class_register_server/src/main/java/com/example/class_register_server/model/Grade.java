package com.example.class_register_server.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String studentName;
    private String subject;
    private String grade;
    private String studentIndex;
    
    @ManyToOne
    @JoinColumn(name = "assessingTeacher", referencedColumnName = "email")
    @JsonIgnoreProperties({"password", "firstName", "lastName", "studentIndex", "isTeacher"})
    private User assessingTeacher;

    public Grade() {
    }

    public Grade(String studentName, String subject, String grade, String index, User assessingTeacher) {
        this.studentName = studentName;
        this.subject = subject;
        this.grade = grade;
        this.studentIndex = index;
        this.assessingTeacher = assessingTeacher;
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

    public User getAssessingTeacher() {
        return assessingTeacher;
    }

    public void setAssessingTeacher(User assessingTeacher) {
        this.assessingTeacher = assessingTeacher;
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
