package br.com.zupacademy.samara.casadocodigo.utils.annotations;

import br.com.zupacademy.samara.casadocodigo.autor.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailUnicoValidador implements ConstraintValidator<EmailUnico, String> {

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !autorRepository.findByEmail(value).isPresent();
    }
}
