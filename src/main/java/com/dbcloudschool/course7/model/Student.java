package com.dbcloudschool.course7.model;

import com.dbcloudschool.course7.exception.NoGradesException;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany
    private List<Grade> grades = new ArrayList<>();

    @NotNull
    @ManyToOne
    private Specialty specialty;

    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    public double getAnnualAverage() throws NoGradesException {
        if (grades.isEmpty()) {
            throw new NoGradesException("student has no grades");
        }
        double sum = 0;

        for (Grade grade : grades) {
            sum += grade.getValue();
        }
        return sum / grades.size();
    }
}
