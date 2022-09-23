package com.rainy.common.core.annotation;

import com.rainy.common.core.entity.CustomResult;
import com.rainy.common.core.entity.Results;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomResponse {
    Class<? extends Results> values() default CustomResult.class;
}
