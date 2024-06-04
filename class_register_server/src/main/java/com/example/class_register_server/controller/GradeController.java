package com.example.class_register_server.controller;

import com.example.class_register_server.auth.JwtUtil;
import com.example.class_register_server.model.Grade;
import com.example.class_register_server.model.User;
import com.example.class_register_server.service.CustomUserDetailsService;
import com.example.class_register_server.service.GradeService;

import io.jsonwebtoken.Claims;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/grades")
public class GradeController {

    // Use Grade service to make all queries that are handled by Repository.
    @Autowired
    private GradeService gradeService;

    @Autowired
    private CustomUserDetailsService userService;

    @Autowired
    private JwtUtil jwtUtil;


    // If user is student, then could only read his grades. Except user is teacher, then he sees all grades in DB
    @GetMapping
    public List<Grade> getAllGrades(HttpServletRequest request) {
        User authenticatedUser = jwtUtil.getCurrentUser(request);

        if (authenticatedUser.getIsTeacher()) {
            return gradeService.getAllGrades();
        } else {
            return gradeService.getAllGradesByStudentIndex(authenticatedUser.getStudentIndex());
        }

    }

    // Get grade by Id if user is teacher
    @GetMapping("/{id}")
    public ResponseEntity<Grade> getGradeById(@PathVariable Long id, HttpServletRequest request) {
        User authenticatedUser = jwtUtil.getCurrentUser(request);

        if (authenticatedUser.getIsTeacher()) {
            Optional<Grade> grade = gradeService.getGradeById(id);
            if (grade.isPresent()) {
                return ResponseEntity.ok(grade.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Get all grades that contains studentIndex, only for Teacher.
    @GetMapping("/student/search")
    public List<Grade> getGradesByStudentIndex(@RequestParam String studentIndex, HttpServletRequest request) {
        User authenticatedUser = jwtUtil.getCurrentUser(request);
        if (authenticatedUser.getIsTeacher()) {
            return gradeService.getAllGradesByStudentIndex(studentIndex);
        } else {
            return Collections.emptyList();
        }
    }
    

    @PostMapping
    public ResponseEntity<Grade> createGrade(@RequestBody Grade grade, HttpServletRequest request) {
        User authenticatedUser = jwtUtil.getCurrentUser(request);
        if (authenticatedUser.getIsTeacher()) {
            Grade savedGrade = gradeService.saveGrade(grade);
            return ResponseEntity.ok(savedGrade);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grade> updateGrade(@PathVariable Long id, @RequestBody Grade gradeDetails, HttpServletRequest request) {
        User authenticatedUser = jwtUtil.getCurrentUser(request);
        if (authenticatedUser.getIsTeacher()) {
            Optional<Grade> grade = gradeService.getGradeById(id);
            if (grade.isPresent()) {
                Grade updatedGrade = grade.get();
                updatedGrade.setStudentName(gradeDetails.getStudentName());
                updatedGrade.setSubject(gradeDetails.getSubject());
                updatedGrade.setGrade(gradeDetails.getGrade());
                gradeService.saveGrade(updatedGrade);
                return ResponseEntity.ok(updatedGrade);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Long id, HttpServletRequest request) {
        User authenticatedUser = jwtUtil.getCurrentUser(request);
        if (authenticatedUser.getIsTeacher()) {
            if (gradeService.getGradeById(id).isPresent()) {
                gradeService.deleteGrade(id);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
