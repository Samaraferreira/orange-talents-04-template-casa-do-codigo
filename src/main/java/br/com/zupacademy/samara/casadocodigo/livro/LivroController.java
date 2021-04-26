package br.com.zupacademy.samara.casadocodigo.livro;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid LivroRequest livroRequest) {
        Livro livro = livroRequest.toModel(entityManager);

        entityManager.persist(livro);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<LivroResponse> listar() {
        List<Livro> livros = entityManager.createQuery("select l from Livro l").getResultList();

        return livros.stream().map(LivroResponse::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDetalhesResponse> detalhe(@PathVariable Long id) {

        Livro livro = entityManager.find(Livro.class, id);

        if (livro == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new LivroDetalhesResponse(livro));
    }
}
