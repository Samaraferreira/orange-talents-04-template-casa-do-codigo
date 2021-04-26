package br.com.zupacademy.samara.casadocodigo.utils.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExistsValueValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsValue {

    String message() default "Objeto não encontrado";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

    String fieldName();

    Class<?> domainClass();
}
