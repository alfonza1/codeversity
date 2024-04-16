package com.CourseDeliveryService.controller;

import com.CourseDeliveryService.constants.CareerPath;
import com.CourseDeliveryService.constants.Framework;
import com.CourseDeliveryService.constants.Language;
import com.CourseDeliveryService.constants.Subject;
import com.CourseDeliveryService.model.Course;
import com.CourseDeliveryService.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CourseControllerTest {

    @InjectMocks
    CourseController courseController;

    @Mock
    CourseService courseService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(courseController).build();
    }

    @Test
    void addCourse() throws Exception {
        Course course = new Course("Test Title", Arrays.asList(Subject.CODE_FOUNDATIONS), Language.JAVA, Framework.SPRING_BOOT, CareerPath.BACK_END_ENGINEER, "Test Description", new ArrayList<>());
        when(courseService.saveCourse(course)).thenReturn(course);
        mockMvc.perform(post("/api/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}")) // Replace with actual JSON representation of the course
                .andExpect(status().isOk());
    }

    @Test
    void getAllCourses() throws Exception {
        Course course1 = new Course("Test Title", Arrays.asList(Subject.CODE_FOUNDATIONS), Language.JAVA, Framework.SPRING_BOOT, CareerPath.BACK_END_ENGINEER, "Test Description", new ArrayList<>());
        Course course2 = new Course("Test Title", Arrays.asList(Subject.CODE_FOUNDATIONS), Language.JAVA, Framework.SPRING_BOOT, CareerPath.BACK_END_ENGINEER, "Test Description", new ArrayList<>());
        when(courseService.getAllCourses()).thenReturn(Arrays.asList(course1, course2));
        mockMvc.perform(get("/api/courses")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getCourseById() throws Exception {
        Long id = 1L;
        Course course = new Course("Test Title", Arrays.asList(Subject.CODE_FOUNDATIONS), Language.JAVA, Framework.SPRING_BOOT, CareerPath.BACK_END_ENGINEER, "Test Description", new ArrayList<>());
        when(courseService.getCourseById(id)).thenReturn(Optional.of(course));
        mockMvc.perform(get("/api/courses/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateCourse() throws Exception {
        Course course = new Course("Test Title", Arrays.asList(Subject.CODE_FOUNDATIONS), Language.JAVA, Framework.SPRING_BOOT, CareerPath.BACK_END_ENGINEER, "Test Description", new ArrayList<>());
        Long id = 1L;
        when(courseService.updateCourse(course)).thenReturn(course);
        mockMvc.perform(put("/api/courses/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteCourse() throws Exception {
        Long id = 1L;
        mockMvc.perform(delete("/api/courses/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
