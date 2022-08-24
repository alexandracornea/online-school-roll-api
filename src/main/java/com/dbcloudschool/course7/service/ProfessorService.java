package com.dbcloudschool.course7.service;

import com.dbcloudschool.course7.exception.ProfessorNotFoundException;
import com.dbcloudschool.course7.model.Course;
import com.dbcloudschool.course7.model.Professor;
import com.dbcloudschool.course7.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfessorService {
    private final ProfessorRepository professorRepository;

    public List<Course> getAllCourses(Integer professorId) throws ProfessorNotFoundException {
        Optional<Professor> professor = professorRepository.findById(professorId);

        if (professor.isEmpty()) {
            throw new ProfessorNotFoundException("professor not found");
        }
        return professor.get().getCourses();
    }
}
