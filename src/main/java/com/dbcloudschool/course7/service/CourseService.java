package com.dbcloudschool.course7.service;

import com.dbcloudschool.course7.model.Course;
import com.dbcloudschool.course7.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    public List<Course> getAllCoursesWithMoreThanThreeCredits() {
        return courseRepository.findAll().stream()
                .filter(course -> course.getCredits() >= 3)
                .collect(Collectors.toList());
    }
}
