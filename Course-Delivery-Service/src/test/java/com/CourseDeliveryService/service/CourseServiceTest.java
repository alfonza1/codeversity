package com.CourseDeliveryService.service;

import com.CourseDeliveryService.constants.CareerPath;
import com.CourseDeliveryService.constants.Framework;
import com.CourseDeliveryService.constants.Language;
import com.CourseDeliveryService.constants.Subject;
import com.CourseDeliveryService.exception.NullFieldException;
import com.CourseDeliveryService.model.Course;
import com.CourseDeliveryService.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CourseServiceTest {

    @InjectMocks
    CourseService courseService;

    @Mock
    CourseRepository courseRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void saveCourse_withValidCourse() {
        Course course = new Course("Test Title", Arrays.asList(Subject.CODE_FOUNDATIONS), Language.JAVA, Framework.SPRING_BOOT, CareerPath.BACK_END_ENGINEER, "Test Description", new ArrayList<>());
        when(courseRepository.save(course)).thenReturn(course);
        assertEquals(course, courseService.saveCourse(course));
    }

    @Test
    void saveCourse_withNullTitle_shouldThrowException() {
        Course course = new Course(null, Arrays.asList(Subject.CODE_FOUNDATIONS), Language.JAVA, Framework.SPRING_BOOT, CareerPath.BACK_END_ENGINEER, "Test Description", new ArrayList<>());
        assertThrows(NullFieldException.class, () -> courseService.saveCourse(course));
    }

    @Test
    void saveCourse() {
        Course course = new Course("Test Title", Arrays.asList(Subject.CODE_FOUNDATIONS), Language.JAVA, Framework.SPRING_BOOT, CareerPath.BACK_END_ENGINEER, "Test Description", new ArrayList<>());
        when(courseRepository.save(course)).thenReturn(course);
        assertEquals(course, courseService.saveCourse(course));
    }

    @Test
    void updateCourse() {
        Course course = new Course("Test Title", Arrays.asList(Subject.CODE_FOUNDATIONS), Language.JAVA, Framework.SPRING_BOOT, CareerPath.BACK_END_ENGINEER, "Test Description", new ArrayList<>());
        when(courseRepository.save(course)).thenReturn(course);
        assertEquals(course, courseService.updateCourse(course));
    }

    @Test
    void deleteCourse() {
        Long id = 1L;
        courseService.deleteCourse(id);
        verify(courseRepository, times(1)).deleteById(id);
    }

    @Test
    void getCourseById() {
        Long id = 1L;
        Course course = new Course("Test Title", Arrays.asList(Subject.CODE_FOUNDATIONS), Language.JAVA, Framework.SPRING_BOOT, CareerPath.BACK_END_ENGINEER, "Test Description", new ArrayList<>());
        when(courseRepository.findById(id)).thenReturn(Optional.of(course));
        assertEquals(Optional.of(course), courseService.getCourseById(id));
    }

    @Test
    void getAllCourses() {
        Course course1 = new Course("Test Title", Arrays.asList(Subject.CODE_FOUNDATIONS), Language.JAVA, Framework.SPRING_BOOT, CareerPath.BACK_END_ENGINEER, "Test Description", new ArrayList<>());
        Course course2 = new Course("Test Title", Arrays.asList(Subject.CODE_FOUNDATIONS), Language.JAVA, Framework.SPRING_BOOT, CareerPath.BACK_END_ENGINEER, "Test Description", new ArrayList<>());
        when(courseRepository.findAll()).thenReturn(Arrays.asList(course1, course2));
        assertEquals(2, courseService.getAllCourses().size());
    }
}