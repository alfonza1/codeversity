package com.CourseDeliveryService.validators;

import com.CourseDeliveryService.model.Module;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MinModuleCountValidatorTest {

    @InjectMocks
    private MinModuleCountValidator validator;

    @Mock
    private ConstraintValidatorContext context;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        validator.initialize(new MinModuleCount() {
            @Override
            public String message() {
                return "The course must have at least 4 modules";
            }

            @Override
            public Class<?>[] groups() {
                return new Class[0];
            }

            @Override
            public Class[] payload() {
                return new Class[0];
            }

            @Override
            public int value() {
                return 4;
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return MinModuleCount.class;
            }
        });
    }

    @Test
    void isValid_withLessThanMinModules_returnsFalse() {
        List<Module> modules = Collections.nCopies(3, new Module("Module 1", "url1", "Intro"));
        assertFalse(validator.isValid(modules, context));
    }

    @Test
    void isValid_withMinModules_returnsTrue() {
        List<Module> modules = Collections.nCopies(4, new Module("Module 2", "url2", "Basics"));
        assertTrue(validator.isValid(modules, context));
    }

    @Test
    void isValid_withMoreThanMinModules_returnsTrue() {
        List<Module> modules = Collections.nCopies(5, new Module("Module 3", "url3", "Advanced"));
        assertTrue(validator.isValid(modules, context));
    }

    @Test
    void isValid_withNullModules_returnsFalse() {
        assertFalse(validator.isValid(null, context));
    }
}
