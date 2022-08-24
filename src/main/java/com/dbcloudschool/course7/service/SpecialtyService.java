package com.dbcloudschool.course7.service;

import com.dbcloudschool.course7.exception.NoGradesException;
import com.dbcloudschool.course7.exception.SpecialtyNotFoundException;
import com.dbcloudschool.course7.model.Specialty;
import com.dbcloudschool.course7.model.Student;
import com.dbcloudschool.course7.repository.SpecialtyRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpecialtyService {
    private final SpecialtyRepository specialtyRepository;

    public List<Student> getAllStudents(Integer specialtyId) throws SpecialtyNotFoundException {
        Optional<Specialty> specialty = specialtyRepository.findById(specialtyId);

        if (specialty.isEmpty()) {
            throw new SpecialtyNotFoundException("specialty not found");
        }
        return specialty.get().getStudents();
    }

    // homework
    public Specialty getSpecialtyWithMostStudents() throws SpecialtyNotFoundException {
        Optional<Specialty> specialty = specialtyRepository.findAll().stream()
                .max(Comparator.comparingInt(o -> o.getStudents().size()));

        if (specialty.isEmpty()) {
            throw new SpecialtyNotFoundException("specialty not found");
        }
        return specialty.get();
    }

    // homework
    public double getAverage(Integer specialtyId) throws Exception {
        List<Student> students;
        try {
            students = getAllStudents(specialtyId);
        } catch (SpecialtyNotFoundException e) {
            throw new SpecialtyNotFoundException("specialty not found");
        }
        double sum = 0d;
        int count = 0; // count students with valid grades

        for (Student student : students) {
            try {
                sum += student.getAnnualAverage();
            } catch (NoGradesException e) {
                continue;
            }
            count++;
        }
        if (count == 0) {
            throw new Exception("no student from list has grades");
        }
        return sum / count;
    }

    // homework
    public Student getStudentWithHighestAverage(Integer specialtyId) throws Exception {
        List<Student> students;
        try {
            students = getAllStudents(specialtyId);
        } catch (SpecialtyNotFoundException e) {
            throw new SpecialtyNotFoundException("specialty not found");
        }
        Optional<Student> optionalStudent = students.stream()
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
