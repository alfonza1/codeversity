package com.CourseDeliveryService.service;

import com.CourseDeliveryService.exception.ContentNotFoundException;
import com.CourseDeliveryService.model.Module;
import com.CourseDeliveryService.repository.ModuleRepository;
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
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseService.class);

    public Module saveModule(Module module) {
        try {
            validateModule(module);
            return moduleRepository.save(module);
        } catch (Exception e) {
            LOGGER.error("Error saving course: " + e.getMessage());
            throw e;
        }
    }

    public Module updateModule(Module module) {
        if (!moduleRepository.existsById(module.getId())) {
            LOGGER.error("Attempted to update non-existent module with ID " + module.getId());
            throw new ContentNotFoundException("No module found with ID " + module.getId());
        }
        validateModule(module);
        return moduleRepository.save(module);
    }

    public void deleteModule(Long id) {
        if (!moduleRepository.existsById(id)) {
            LOGGER.error("Attempted to delete non-existent module with ID " + id);
            throw new ContentNotFoundException("No course module with ID " + id);
        }
        moduleRepository.deleteById(id);
    }

    public Optional<Module> getModuleById(Long id) {
        Optional<Module> module = moduleRepository.findById(id);
        if (!module.isPresent()) {
            LOGGER.error("No module found with ID " + id);
            throw new ContentNotFoundException("No module found with ID " + id);
        }
        return module;
    }

    public List<Module> getAllModules() {
        return (List<Module>) moduleRepository.findAll();
    }

    public List<Module> getModulesByCourseId(Long courseId) {
        return moduleRepository.findByCourseId(courseId);
    }

    private void validateModule(Module module) {
        if (module.getTitle() == null) {
            throw new NullFieldException("title");
        }
        if (module.getVideo() == null) {
            throw new NullFieldException("video");
        }
        if (module.getDescription() == null) {
            throw new NullFieldException("description");
        }
    }

}
