package com.learning.SpringSecurity.controllers;

import com.learning.SpringSecurity.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    // This list is initialized once when the controller is created
    private final List<Student> students = new ArrayList<>();

    public StudentController() {
        students.add(new Student(1, "adhiraj", 80));
        students.add(new Student(2, "abhi", 85));
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return students;
    }

    @GetMapping("/csrf-token") // just to get the csrf-token
    public CsrfToken getCSRFToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student std) {
        students.add(std);
        return std;
    }
}
