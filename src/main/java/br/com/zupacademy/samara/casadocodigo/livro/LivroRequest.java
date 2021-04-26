package br.com.zupacademy.samara.casadocodigo.livro;

import br.com.zupacademy.samara.casadocodigo.autor.Autor;
import br.com.zupacademy.samara.casadocodigo.categoria.Categoria;
import br.com.zupacademy.samara.casadocodigo.utils.annotations.ExistsValue;
import br.com.zupacademy.samara.casadocodigo.utils.annotations.UniqueValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import org.hibernate.validator.constraints.ISBN;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class LivroRequest {

    @NotBlank
    @UniqueValue(domainClass = Livro.class, fieldName = "titulo")
    private String titulo;

    @NotBlank
    @Size(max = 500)
    private String resumo;

    private String sumario;

    @NotNull
    @DecimalMin("20")
    private BigDecimal preco;

    @NotNull
    @Min(100)
    private Integer numeroPaginas;

    @NotBlank
    @UniqueValue(domainClass = Livro.class, fieldName = "isbn")
    @ISBN
    private String isbn;

    @NotNull
    @Future
    @JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
    private LocalDate dataPublicacao;

    @NotNull
    @ExistsValue(domainClass = Categoria.class, fieldName = "id", message = "Categoria não cadastrada")
    private Long categoriaId;

    @NotNull
    @ExistsValue(domainClass = Autor.class, fieldName = "id", message = "Autor(a) não cadastrado(a)")
    private Long autorId;

    public LivroRequest(@NotBlank String titulo, @NotBlank String resumo, String sumario,
                 @NotNull @DecimalMin("20") BigDecimal preco, @NotNull @Min(100) Integer numeroPaginas, @NotBlank @ISBN String isbn,
                 @NotNull @Future LocalDate dataPublicacao, @NotNull Long categoriaId, @NotNull Long autorId) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.categoriaId = categoriaId;
        this.autorId = autorId;
    }

    public Livro toModel(EntityManager manager) {
        Categoria categoria = manager.find(Categoria.class, categoriaId);
        Autor autor = manager.find(Autor.class, autorId);

        return new Livro(
                titulo,
                resumo,
                sumario,
                preco,
                numeroPaginas,
                isbn,
                dataPublicacao,
                categoria,
                autor);
    }
}
