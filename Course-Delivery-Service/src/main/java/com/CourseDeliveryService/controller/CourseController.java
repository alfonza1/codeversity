package com.CourseDeliveryService.controller;

import com.CourseDeliveryService.constants.CareerPath;
import com.CourseDeliveryService.constants.Framework;
import com.CourseDeliveryService.constants.Language;
import com.CourseDeliveryService.constants.Subject;
import com.CourseDeliveryService.model.Course;
import com.CourseDeliveryService.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    @PostMapping
    public Course addCourse(@RequestBody Course course) {
        try {
            return courseService.saveCourse(course);
        } catch (Exception e) {
            logger.error("Error occurred while adding course: ", e);
            throw e;
        }
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public Optional<Course> getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    @PutMapping("/{id}")
    public Course updateCourse(@RequestBody Course course, @PathVariable Long id) {
        course.setId(id);
        return courseService.updateCourse(course);
    }

    @GetMapping("/language/{language}")
    public List<Course> getCoursesByLanguage(@PathVariable Language language) {
        return courseService.getCoursesByLanguage(language);
    }

    @GetMapping("/framework/{framework}")
    public List<Course> getCoursesByFramework(@PathVariable Framework framework) {
        return courseService.getCoursesByFramework(framework);
    }

    @GetMapping("/careerpath/{careerPath}")
    public List<Course> getCoursesByCareerPath(@PathVariable CareerPath careerPath) {
        return courseService.getCoursesByCareerPath(careerPath);
    }

    @GetMapping("/subject/{subject}")
    public List<Course> getCoursesBySubject(@PathVariable Subject subject) {
        return courseService.getCoursesBySubject(subject);
    }

    @GetMapping("/title/{title}")
    public List<Course> getCoursesByTitle(@PathVariable String title) {
        return courseService.getCoursesByTitle(title);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }
}
