package com.CourseDeliveryService.model;

import com.CourseDeliveryService.constants.CareerPath;
import com.CourseDeliveryService.constants.Framework;
import com.CourseDeliveryService.constants.Language;
import com.CourseDeliveryService.constants.Subject;
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

    @Column(nullable = false)
    private String title;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<Subject> subjects;

    @Column(nullable = false)
    private Language language;

    @Column(nullable = true)
    private Framework framework;

    @Column(nullable = false)
    private CareerPath careerPath;

    @Column(nullable = false)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Module> modules = new ArrayList<>();

}
