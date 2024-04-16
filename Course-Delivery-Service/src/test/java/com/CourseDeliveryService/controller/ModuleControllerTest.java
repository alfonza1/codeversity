package com.CourseDeliveryService.controller;

import com.CourseDeliveryService.model.Module;
import com.CourseDeliveryService.service.ModuleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ModuleControllerTest {

    @InjectMocks
    ModuleController moduleController;

    @Mock
    ModuleService moduleService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(moduleController).build();
    }

    @Test
    void addModule() throws Exception {
        Module module = new Module("Test Title", "Test Video", "Test Description");
        when(moduleService.saveModule(module)).thenReturn(module);
        mockMvc.perform(post("/module")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllModules() throws Exception {
        Module module1 = new Module("Test Title", "Test Video", "Test Description");
        Module module2 = new Module("Test Title", "Test Video", "Test Description");
        when(moduleService.getAllModules()).thenReturn(Arrays.asList(module1, module2));
        mockMvc.perform(get("/module")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getModuleById() throws Exception {
        Long id = 1L;
        Module module = new Module("Test Title", "Test Video", "Test Description");
        when(moduleService.getModuleById(id)).thenReturn(Optional.of(module));
        mockMvc.perform(get("/module/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateModule() throws Exception {
        Module module = new Module("Test Title", "Test Video", "Test Description");
        Long id = 1L;
        when(moduleService.updateModule(module)).thenReturn(module);
        mockMvc.perform(put("/module/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteModule() throws Exception {
        Long id = 1L;
        mockMvc.perform(delete("/module/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getModulesByCourseId() throws Exception {
        Long courseId = 1L;
        Module module1 = new Module("Test Title", "Test Video", "Test Description");
        Module module2 = new Module("Test Title", "Test Video", "Test Description");
        when(moduleService.getModulesByCourseId(courseId)).thenReturn(Arrays.asList(module1, module2));
        mockMvc.perform(get("/module/courses/" + courseId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}