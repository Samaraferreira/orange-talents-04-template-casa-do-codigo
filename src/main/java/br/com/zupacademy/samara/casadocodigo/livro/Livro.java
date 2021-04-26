package br.com.zupacademy.samara.casadocodigo.livro;

import br.com.zupacademy.samara.casadocodigo.autor.Autor;
import br.com.zupacademy.samara.casadocodigo.categoria.Categoria;
import org.hibernate.validator.constraints.ISBN;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotBlank
    @Column(unique = true)
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
    @ISBN
    @Column(unique = true)
    private String isbn;

    @NotNull
    @Future
    private LocalDate dataPublicacao;

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Categoria categoria;

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Autor autor;

    @Deprecated
    public Livro() {
    }

    public Livro(@NotBlank String titulo, @NotBlank String resumo, String sumario,
                 @NotNull @DecimalMin("20") BigDecimal preco, @NotNull @Min(100) Integer numeroPaginas, @NotBlank @ISBN String isbn,
                 @NotNull @Future LocalDate dataPublicacao, @NotNull Categoria categoria, @NotNull Autor autor) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.categoria = categoria;
        this.autor = autor;
    }
}
