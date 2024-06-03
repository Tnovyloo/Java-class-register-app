package com.example.class_register_server.controller;

import com.example.class_register_server.auth.JwtUtil;
import com.example.class_register_server.model.Grade;
import com.example.class_register_server.model.User;
import com.example.class_register_server.service.CustomUserDetailsService;
import com.example.class_register_server.service.GradeService;

import io.jsonwebtoken.Claims;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/grades")
public class GradeController {

    // Use service to make all queries.
    @Autowired
    private GradeService gradeService;
    @Autowired
    private CustomUserDetailsService userService;

    @Autowired
    private JwtUtil jwtUtil;


    // Get all grades
    @GetMapping
    public List<Grade> getAllGrades(HttpServletRequest request) {
        // TODO get User email
        System.out.println(request.getHeader("Authorization"));
        
        String  email = jwtUtil.getEmail(jwtUtil.resolveClaims(request));
        System.out.println(email);

        User authenticatedUser = userService.findByEmail(email);
        System.out.println("Authenticated user: " + authenticatedUser.getEmail());
        if (authenticatedUser.getIsTeacher()) {
            return gradeService.getAllGrades();
        } else {
            return gradeService.getAllGradesByStudentIndex(authenticatedUser.getStudentIndex());
        }

        // return gradeService.getAllGrades();
    }

    // Get grade by Id
    @GetMapping("/{id}")
    public ResponseEntity<Grade> getGradeById(@PathVariable Long id) {
        Optional<Grade> grade = gradeService.getGradeById(id);
        if (grade.isPresent()) {
            return ResponseEntity.ok(grade.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Get all grades that are studentName
    @GetMapping("/student/search")
    public List<Grade> getGradesByStudentIndex(@RequestParam String studentIndex) {
        return gradeService.getAllGradesByStudentIndex(studentIndex);
    }
    

    @PostMapping
    public Grade createGrade(@RequestBody Grade grade) {
        // TODO only teacher could create grades.
        return gradeService.saveGrade(grade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grade> updateGrade(@PathVariable Long id, @RequestBody Grade gradeDetails) {
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
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Long id) {
        if (gradeService.getGradeById(id).isPresent()) {
            gradeService.deleteGrade(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
