package br.com.zupacademy.samara.casadocodigo.cliente;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class EstadoValidator implements Validator {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public boolean supports(Class<?> clazz) {
        return ClienteRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) return;

        final ClienteRequest dto = (ClienteRequest) target;

        Query query = manager.createQuery("select estado from Estado estado join estado.pais pais where estado.id = :estadoId and pais.id = :paisId");
        query.setParameter("estadoId", dto.getEstadoId());
        query.setParameter("paisId", dto.getPaisId());

        List<?> resultList = query.getResultList();

        if (resultList.size() < 1) {
            errors.rejectValue("estadoId", "404", "não foi encontrado nenhum estado com esse id");
        }
    }
}
