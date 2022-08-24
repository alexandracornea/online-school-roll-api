package com.dbcloudschool.course7.controller;

import com.dbcloudschool.course7.model.Course;
import com.dbcloudschool.course7.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("courses")
public class CourseController {
    private final CourseService courseService;

    @GetMapping
    public List<Course> getAllCoursesWithMoreThanThreeCredits() {
        return courseService.getAllCoursesWithMoreThanThreeCredits();
    }
}
