package com.example.class_register_server.service;

import com.example.class_register_server.model.Grade;
import com.example.class_register_server.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }

    public List<Grade> getAllGradesByStudentIndex(String studentIndex) {
        // return gradeRepository.find();
        return gradeRepository.findByStudentIndex(studentIndex);
    }

    public List<Grade> getAllByEmail(String teacherEmail) {
        return gradeRepository.findByAssessingTeacher_Email(teacherEmail);
    }

    public Optional<Grade> getGradeById(Long id) {
        return gradeRepository.findById(id);
    }

    public Grade saveGrade(Grade grade) {
        System.out.println("Saving " + grade);
        return gradeRepository.save(grade);
    }

    public void deleteGrade(Long id) {
        gradeRepository.deleteById(id);
    }
}
