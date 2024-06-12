package com.example.class_register_server.repository;


import com.example.class_register_server.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


// https://docs.spring.io/spring-data/jpa/docs/1.6.0.RELEASE/reference/html/jpa.repositories.html
@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByStudentIndex(String studentIndex);
    List<Grade> findByAssessingTeacher_Email(String teacherEmail);
}
