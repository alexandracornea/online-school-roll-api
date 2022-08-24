package com.dbcloudschool.course7.controller;

import com.dbcloudschool.course7.exception.SpecialtyNotFoundException;
import com.dbcloudschool.course7.model.Specialty;
import com.dbcloudschool.course7.model.Student;
import com.dbcloudschool.course7.service.SpecialtyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("specialties")
public class SpecialtyController {
    private final SpecialtyService specialtyService;

    @GetMapping("students/{specialtyId}")
    public List<Student> getAllStudents(@PathVariable Integer specialtyId)
            throws SpecialtyNotFoundException {
        return specialtyService.getAllStudents(specialtyId);
    }

    // homework
    @GetMapping
    public Specialty getSpecialtyWithMostStudents() throws SpecialtyNotFoundException {
        return specialtyService.getSpecialtyWithMostStudents();
    }

    // homework
    @GetMapping("average/{specialtyId}")
    public double getAverage(@PathVariable Integer specialtyId) throws Exception {
        return specialtyService.getAverage(specialtyId);
    }

    // homework
    @GetMapping("student/{specialtyId}")
    public Student getStudentWithHighestAverage(@PathVariable Integer specialtyId) throws Exception {
        return specialtyService.getStudentWithHighestAverage(specialtyId);
    }
}
