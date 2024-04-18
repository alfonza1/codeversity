// In CourseRepository.java
package com.CourseDeliveryService.repository;

import com.CourseDeliveryService.constants.CareerPath;
import com.CourseDeliveryService.constants.Framework;
import com.CourseDeliveryService.constants.Language;
import com.CourseDeliveryService.constants.Subject;
import com.CourseDeliveryService.model.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {


    List<Course> findByLanguage(Language language);

    List<Course> findByFramework(Framework framework);

    List<Course> findByCareerPath(CareerPath careerPath);

    List<Course> findBySubjectsContaining(Subject subject);

    List<Course> findByTitleContaining(String title);
}