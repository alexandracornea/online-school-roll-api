package com.dbcloudschool.course7.repository;

import com.dbcloudschool.course7.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> { }
