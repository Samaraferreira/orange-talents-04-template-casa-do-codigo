package br.com.zupacademy.samara.casadocodigo.categoria;

import br.com.zupacademy.samara.casadocodigo.autor.Autor;
import br.com.zupacademy.samara.casadocodigo.autor.AutorRequest;
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
@RequestMapping("/categorias")
public class CategoriaController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CategoriaRequest categoriaRequest) {
        Categoria categoria = categoriaRequest.toModel();

        entityManager.persist(categoria);

        return ResponseEntity.ok().build();
    }
}
