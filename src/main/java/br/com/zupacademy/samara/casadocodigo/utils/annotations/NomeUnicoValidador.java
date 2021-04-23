package br.com.zupacademy.samara.casadocodigo.utils.annotations;

import br.com.zupacademy.samara.casadocodigo.autor.AutorRepository;
import br.com.zupacademy.samara.casadocodigo.categoria.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NomeUnicoValidador implements ConstraintValidator<NomeUnico, String> {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !categoriaRepository.findByNome(value).isPresent();
    }
}
