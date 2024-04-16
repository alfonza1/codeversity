package com.CourseDeliveryService.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 255)
    private String video;

    @Column(nullable = false, length = 255)
    private String description;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public Module(String title, String video, String description) {
        this.title = title;
        this.description = description;
        this.video = video;
    }
}
