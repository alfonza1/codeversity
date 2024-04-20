package com.CourseDeliveryService.service;

import com.CourseDeliveryService.constants.CareerPath;
import com.CourseDeliveryService.constants.Framework;
import com.CourseDeliveryService.constants.Language;
import com.CourseDeliveryService.constants.Subject;
import com.CourseDeliveryService.exception.NullFieldException;
import com.CourseDeliveryService.model.Course;
import com.CourseDeliveryService.model.Module;
import com.CourseDeliveryService.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseServiceTest {

    @InjectMocks
    private CourseService courseService;

    @Mock
    private CourseRepository courseRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private Course createValidCourse() {
        List<Module> modules = new ArrayList<>(Arrays.asList(
                new Module("Module 1", "URL1", "Description1"),
                new Module("Module 2", "URL2", "Description2"),
                new Module("Module 3", "URL3", "Description3"),
                new Module("Module 4", "URL4", "Description4")
        ));
        return new Course("title", "author", Arrays.asList(Subject.CODE_FOUNDATIONS), Language.JAVA, Framework.SPRING_BOOT, CareerPath.BACK_END_ENGINEER, "Valid Description", modules);
    }

    @Test
    void saveCourse_withValidCourse_ShouldPass() {
        Course course = createValidCourse();
        when(courseRepository.save(course)).thenReturn(course);
        Course savedCourse = courseService.saveCourse(course);
        assertNotNull(savedCourse);
        assertEquals(course, savedCourse);
        verify(courseRepository).save(course);
    }

    @Test
    void saveCourse_withInsufficientModules_ShouldThrowException() {
        Course course = createValidCourse();
        course.setModules(course.getModules().subList(0, 3)); // Set to only 3 modules
        assertThrows(IllegalArgumentException.class, () -> courseService.saveCourse(course),
                "Module list needs at least 4 modules.");
    }

    @Test
    void saveCourse_withNullTitle_shouldThrowException() {
        Course course = createValidCourse();
        course.setTitle(null);
        assertThrows(NullFieldException.class, () -> courseService.saveCourse(course));
    }

    @Test
    void updateCourse_withValidData() {
        Course course = createValidCourse();
        when(courseRepository.existsById(course.getId())).thenReturn(true); // Ensure the course exists
        when(courseRepository.save(course)).thenReturn(course);
        assertEquals(course, courseService.updateCourse(course));
        verify(courseRepository).save(course);
    }

    @Test
    void deleteCourse() {
        Long id = 1L;
        when(courseRepository.existsById(id)).thenReturn(true);
        doNothing().when(courseRepository).deleteById(id);
        courseService.deleteCourse(id);
        verify(courseRepository).deleteById(id);
    }


    @Test
    void getCourseById() {
        Long id = 1L;
        Course course = createValidCourse();
        when(courseRepository.findById(id)).thenReturn(Optional.of(course));
        assertEquals(Optional.of(course), courseService.getCourseById(id));
    }

    @Test
    void getAllCourses() {
        Course course1 = createValidCourse();
        Course course2 = createValidCourse();
        when(courseRepository.findAll()).thenReturn(Arrays.asList(course1, course2));
        List<Course> courses = courseService.getAllCourses();
        assertEquals(2, courses.size());
    }
}
