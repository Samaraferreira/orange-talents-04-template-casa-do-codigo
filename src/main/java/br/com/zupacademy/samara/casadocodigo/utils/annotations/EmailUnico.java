package br.com.zupacademy.samara.casadocodigo.utils.annotations;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = EmailUnicoValidador.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailUnico {

    String message() default "Já existe um email cadastrado com o mesmo valor";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
