package com.dbcloudschool.course7.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String subject;

    @NotNull
    private Integer credits;

    @NotNull
    @ManyToOne
    private Professor professor;

    @NotNull
    @ManyToOne
    private Specialty specialty;
}
