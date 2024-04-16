package com.CourseDeliveryService.service;

import com.CourseDeliveryService.model.Course;
import com.CourseDeliveryService.repository.CourseRepository;
import com.CourseDeliveryService.exception.NullFieldException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course saveCourse(Course course) {
        validateCourse(course);
        return courseRepository.save(course);
    }

    public Course updateCourse(Course course) {
        validateCourse(course);
        return courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    public List<Course> getAllCourses() {
        return (List<Course>) courseRepository.findAll();
    }

    private void validateCourse(Course course) {
        if (course.getTitle() == null) throw new NullFieldException("title");

        if (course.getLanguage() == null) throw new NullFieldException("language");

        if (course.getCareerPath() == null) throw new NullFieldException("careerPath");

        if (course.getDescription() == null) throw new NullFieldException("description");

        if (course.getModules() == null) throw new NullFieldException("modules");

        if (course.getSubjects() == null || course.getSubjects().isEmpty()) throw new NullFieldException("subjects");

        if (course.getModules().size() < 4) throw new IllegalArgumentException("Module list needs at least 4 modules.");


    }
}
