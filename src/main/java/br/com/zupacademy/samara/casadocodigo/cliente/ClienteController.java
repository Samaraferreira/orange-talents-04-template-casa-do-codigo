package br.com.zupacademy.samara.casadocodigo.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private EstadoValidator estadoValidator;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(estadoValidator);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Long> cadastrar(@RequestBody @Valid ClienteRequest clienteRequest) {
        Cliente cliente = clienteRequest.toModel(entityManager);

        entityManager.persist(cliente);

        return ResponseEntity.ok().body(cliente.getId());
    }
}
