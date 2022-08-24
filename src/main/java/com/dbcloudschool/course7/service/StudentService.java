package com.dbcloudschool.course7.service;

import com.dbcloudschool.course7.exception.NoGradesException;
import com.dbcloudschool.course7.exception.StudentNotFountException;
import com.dbcloudschool.course7.model.Grade;
import com.dbcloudschool.course7.model.Student;
import com.dbcloudschool.course7.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public void addGrade(Integer studentId, Grade grade) throws StudentNotFountException {
        Optional<Student> student = studentRepository.findById(studentId);

        if (student.isEmpty()) {
            throw new StudentNotFountException("student not found");
        }
        student.get().addGrade(grade);
        studentRepository.save(student.get());
    }

    public List<Grade> getAllGrades(Integer studentId) throws StudentNotFountException {
        Optional<Student> student = studentRepository.findById(studentId);

        if (student.isEmpty()) {
            throw new StudentNotFountException("student not found");
        }
        return student.get().getGrades();
    }

    public List<Student> getAllStudentsWithAverageGreaterThanEight() {
        return studentRepository.findAll().stream()
                .filter(student -> {
                    try {
                        return student.getAnnualAverage() >= 8d;
                    } catch (NoGradesException e) {
                        throw new RuntimeException();
                    }
                }).collect(Collectors.toList());
    }

    // homework
    public Student getStudentWithHighestAverage() throws Exception {
        Optional<Student> optionalStudent = studentRepository.findAll().stream()
                .filter(student -> {
                    try {
                        student.getAnnualAverage();
                    } catch (NoGradesException e) {
                        return false;
                    }
                    return true;
                }).max(new Comparator<>() {
                    @SneakyThrows
                    @Override
                    public int compare(Student o1, Student o2) {
                        return Double.compare(o1.getAnnualAverage(), o2.getAnnualAverage());
                    }
                });
        if (optionalStudent.isEmpty()) {
            throw new Exception("no student from list has grades");
        }
        return optionalStudent.get();
    }
}
