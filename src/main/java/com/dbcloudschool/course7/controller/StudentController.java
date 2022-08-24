package com.dbcloudschool.course7.controller;

import com.dbcloudschool.course7.exception.StudentNotFountException;
import com.dbcloudschool.course7.model.Grade;
import com.dbcloudschool.course7.model.Student;
import com.dbcloudschool.course7.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("students")
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public Student saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @PutMapping("add/grade/{studentId}/{grade}")
    public void addGrade(@PathVariable Integer studentId, @PathVariable double grade)
            throws StudentNotFountException {
        studentService.addGrade(studentId, new Grade(grade));
    }

    @GetMapping("grades/{studentId}")
    public List<Grade> getAllGrades(@PathVariable Integer studentId)
            throws StudentNotFountException {
        return studentService.getAllGrades(studentId);
    }

    @GetMapping
    public List<Student> getAllStudentsWithAverageGreaterThanEight() {
        return studentService.getAllStudentsWithAverageGreaterThanEight();
    }

    // homework
    @GetMapping("smartest")
    public Student getStudentWithHighestAverage() throws Exception {
        return studentService.getStudentWithHighestAverage();
    }
}
