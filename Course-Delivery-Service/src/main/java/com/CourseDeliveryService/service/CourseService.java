package com.CourseDeliveryService.service;

import com.CourseDeliveryService.constants.CareerPath;
import com.CourseDeliveryService.constants.Framework;
import com.CourseDeliveryService.constants.Language;
import com.CourseDeliveryService.constants.Subject;
import com.CourseDeliveryService.exception.ContentNotFoundException;
import com.CourseDeliveryService.model.Course;
import com.CourseDeliveryService.repository.CourseRepository;
import com.CourseDeliveryService.exception.NullFieldException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseService.class);

    public Course saveCourse(Course course) {
        try {
            validateCourse(course);
            return courseRepository.save(course);
        } catch (Exception e) {
            LOGGER.error("Error saving course: " + e.getMessage());
            throw e;
        }
    }

    public Course updateCourse(Course course) {
        if (!courseRepository.existsById(course.getId())) {
            LOGGER.error("Attempted to update non-existent course with ID " + course.getId());
            throw new ContentNotFoundException("No course found with ID " + course.getId());
        }
        validateCourse(course);
        return courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            LOGGER.error("Attempted to delete non-existent course with ID " + id);
            throw new ContentNotFoundException("No course found with ID " + id);
        }
        courseRepository.deleteById(id);
    }

    public Optional<Course> getCourseById(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if (!course.isPresent()) {
            LOGGER.error("No course found with ID " + id);
            throw new ContentNotFoundException("No course found with ID " + id);
        }
        return course;
    }

    public List<Course> getAllCourses() {
        try {
            return (List<Course>) courseRepository.findAll();
        } catch (Exception e) {
            LOGGER.error("Error retrieving all courses: " + e.getMessage());
            throw e;
        }
    }

    public List<Course> getCoursesByLanguage(Language language) {
        LOGGER.info("Fetching courses by language: {}", language);
        return courseRepository.findByLanguage(language);
    }

    public List<Course> getCoursesByFramework(Framework framework) {
        LOGGER.info("Fetching courses by framework: {}", framework);
        return courseRepository.findByFramework(framework);
    }

    public List<Course> getCoursesByCareerPath(CareerPath careerPath) {
        LOGGER.info("Fetching courses by career path: {}", careerPath);
        return courseRepository.findByCareerPath(careerPath);
    }

    public List<Course> getCoursesBySubject(Subject subject) {
        LOGGER.info("Fetching courses by subject: {}", subject);
        return courseRepository.findBySubjectsContaining(subject);
    }

    public List<Course> getCoursesByTitle(String title) {
        LOGGER.info("Fetching courses by title: {}", title);
        return courseRepository.findByTitleContaining(title);
    }

    private void validateCourse(Course course) {
        try {
            LOGGER.info("Validating course: {}", course.getTitle());
            if (course.getTitle() == null) throw new NullFieldException("title");
            if (course.getLanguage() == null) throw new NullFieldException("language");
            if (course.getCareerPath() == null) throw new NullFieldException("careerPath");
            if (course.getDescription() == null) throw new NullFieldException("description");
            if (course.getModules() == null) throw new NullFieldException("modules");
            if (course.getAuthor() == null) throw new NullFieldException("author");
            if (course.getSubjects() == null || course.getSubjects().isEmpty())
                throw new NullFieldException("subjects");
            if (course.getModules().size() < 4)
                throw new IllegalArgumentException("Module list needs at least 4 modules.");
        } catch (Exception e) {
            LOGGER.info("Validation error for course '{}': {}", course.getTitle(), e.getMessage());
            throw e;
        }
    }
}
