package com.CourseDeliveryService.model;

import com.CourseDeliveryService.constants.CareerPath;
import com.CourseDeliveryService.constants.Framework;
import com.CourseDeliveryService.constants.Language;
import com.CourseDeliveryService.constants.Subject;
import com.CourseDeliveryService.validators.MinModuleCount;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.ArrayList;

@Entity
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 50)
    private String author;

    @ElementCollection(fetch = FetchType.LAZY)
    @Column(nullable = false, length = 255)
    private List<Subject> subjects;

    @Column(nullable = false, length = 25)
    private Language language;

    @Column(nullable = true, length = 25)
    private Framework framework;

    @Column(nullable = false, length = 25)
    private CareerPath careerPath;

    @Column(nullable = false, length = 255)
    private String description;

    @MinModuleCount(value = 4, message = "A course must have at least 4 modules")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Module> modules = new ArrayList<>();

    public Course(String title, String author, List<Subject> subjects, Language language, Framework framework, CareerPath careerPath, String description, List<Module> modules) {
        this.title = title;
        this.subjects = subjects;
        this.language = language;
        this.framework = framework;
        this.careerPath = careerPath;
        this.description = description;
        this.modules = modules;
        this.author = author;
    }

    public Course() {
    }
}
