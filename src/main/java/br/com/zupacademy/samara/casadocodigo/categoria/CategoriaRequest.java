package br.com.zupacademy.samara.casadocodigo.categoria;

import br.com.zupacademy.samara.casadocodigo.utils.annotations.UniqueValue;

import javax.validation.constraints.NotBlank;

public class CategoriaRequest {

    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria toModel() {
        return new Categoria(this.nome);
    }
}
