package com.dbcloudschool.course7.repository;

import com.dbcloudschool.course7.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> { }
