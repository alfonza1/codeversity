package com.CourseDeliveryService.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class MinModuleCountValidator implements ConstraintValidator<MinModuleCount, List<Module>> {
    private int minCount;

    @Override
    public void initialize(MinModuleCount constraint) {
        this.minCount = constraint.value();
    }

    @Override
    public boolean isValid(List<Module> modules, ConstraintValidatorContext context) {
        return modules != null && modules.size() >= minCount;
    }
}
