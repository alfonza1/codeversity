package com.CourseDeliveryService.service;

import com.CourseDeliveryService.exception.NullFieldException;
import com.CourseDeliveryService.model.Module;
import com.CourseDeliveryService.repository.ModuleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ModuleServiceTest {

    @InjectMocks
    ModuleService moduleService;

    @Mock
    ModuleRepository moduleRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveModule_withValidModule() {
        Module module = new Module("Test Title", "Test Video", "Test Description");
        when(moduleRepository.save(any(Module.class))).thenReturn(module);
        assertEquals(module, moduleService.saveModule(module));
    }

    @Test
    void saveModule_withNullTitle_shouldThrowException() {
        Module module = new Module(null, "Test Video", "Test Description");
        assertThrows(NullFieldException.class, () -> moduleService.saveModule(module));
    }

    @Test
    void saveModule() {
        Module module = new Module("Test Title", "Test Video", "Test Description");
        when(moduleRepository.save(any(Module.class))).thenReturn(module);
        assertEquals(module, moduleService.saveModule(module));
    }

    @Test
    void updateModule() {
        Module module = new Module("Test Title", "Test Video", "Test Description");
        when(moduleRepository.save(any(Module.class))).thenReturn(module);
        assertEquals(module, moduleService.updateModule(module));
    }

    @Test
    void deleteModule() {
        Long id = 1L;
        moduleService.deleteModule(id);
        verify(moduleRepository, times(1)).deleteById(id);
    }

    @Test
    void getModuleById() {
        Long id = 1L;
        Module module = new Module("Test Title", "Test Video", "Test Description");
        when(moduleRepository.findById(id)).thenReturn(Optional.of(module));
        assertEquals(Optional.of(module), moduleService.getModuleById(id));
    }

    @Test
    void getAllModules() {
        Module module1 = new Module("Test Title 1", "Test Video 1", "Test Description 1");
        Module module2 = new Module("Test Title 2", "Test Video 2", "Test Description 2");
        when(moduleRepository.findAll()).thenReturn(Arrays.asList(module1, module2));
        assertEquals(2, moduleService.getAllModules().size());
    }

    @Test
    void getModulesByCourseId() {
        Long courseId = 1L;
        Module module1 = new Module("Test Title 1", "Test Video 1", "Test Description 1");
        Module module2 = new Module("Test Title 2", "Test Video 2", "Test Description 2");
        when(moduleRepository.findByCourseId(courseId)).thenReturn(Arrays.asList(module1, module2));
        assertEquals(2, moduleService.getModulesByCourseId(courseId).size());
    }


}