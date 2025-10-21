package com.miniorm.annotations;
import com.miniorm.enums.GenerationType;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface GeneratedValue {
    GenerationType strategy() default GenerationType.AUTO_INCREMENT;
}