package com.CourseDeliveryService.service;

import com.CourseDeliveryService.model.Module;
import com.CourseDeliveryService.repository.ModuleRepository;
import com.CourseDeliveryService.exception.NullFieldException;
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

    public Module saveModule(Module module) {
        validateModule(module);
        return moduleRepository.save(module);
    }

    public Module updateModule(Module module) {
        validateModule(module);
        return moduleRepository.save(module);
    }

    public void deleteModule(Long id) {
        moduleRepository.deleteById(id);
    }

    public Optional<Module> getModuleById(Long id) {
        return moduleRepository.findById(id);
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
