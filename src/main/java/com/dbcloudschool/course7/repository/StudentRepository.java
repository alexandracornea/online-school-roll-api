package com.dbcloudschool.course7.repository;

import com.dbcloudschool.course7.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> { }
