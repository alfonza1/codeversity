package com.CourseDeliveryService.repository;

import com.CourseDeliveryService.model.Module;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepository extends CrudRepository<Module, Long> {
    List<Module> findByCourseId(Long courseId);
}
