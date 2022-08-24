package com.dbcloudschool.course7.controller;

import com.dbcloudschool.course7.exception.ProfessorNotFoundException;
import com.dbcloudschool.course7.model.Course;
import com.dbcloudschool.course7.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("professors")
public class ProfessorController {
    private final ProfessorService professorService;

    @GetMapping("courses/{professorId}")
    public List<Course> getAllCourses(@PathVariable Integer professorId)
            throws ProfessorNotFoundException {
        return professorService.getAllCourses(professorId);
    }
}
