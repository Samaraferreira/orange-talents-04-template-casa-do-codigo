package br.com.zupacademy.samara.casadocodigo.pais;

import br.com.zupacademy.samara.casadocodigo.livro.Livro;
import br.com.zupacademy.samara.casadocodigo.livro.LivroRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/pais")
public class PaisController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid PaisRequest paisRequest) {
        Pais pais = paisRequest.toModel();

        entityManager.persist(pais);

        return ResponseEntity.ok().build();
    }

}
