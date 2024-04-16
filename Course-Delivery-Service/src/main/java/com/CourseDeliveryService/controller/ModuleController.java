package com.CourseDeliveryService.controller;

import com.CourseDeliveryService.model.Module;
import com.CourseDeliveryService.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/module")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @PostMapping
    public Module addModule(@RequestBody Module module) {
        return moduleService.saveModule(module);
    }

    @GetMapping
    public List<Module> getAllModules() {
        return moduleService.getAllModules();
    }

    @GetMapping("/{id}")
    public Optional<Module> getModuleById(@PathVariable Long id) {
        return moduleService.getModuleById(id);
    }

    @PutMapping("/{id}")
    public Module updateModule(@RequestBody Module module, @PathVariable Long id) {
        module.setId(id);
        return moduleService.updateModule(module);
    }

    @DeleteMapping("/{id}")
    public void deleteModule(@PathVariable Long id) {
        moduleService.deleteModule(id);
    }

    @GetMapping("/courses/{courseId}")
    public List<Module> getModulesByCourseId(@PathVariable Long courseId) {
        return moduleService.getModulesByCourseId(courseId);
    }
}
