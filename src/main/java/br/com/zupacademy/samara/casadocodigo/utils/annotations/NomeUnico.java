package br.com.zupacademy.samara.casadocodigo.utils.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NomeUnicoValidador.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NomeUnico {

    String message() default "Já existe um nome cadastrado com o mesmo valor";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
