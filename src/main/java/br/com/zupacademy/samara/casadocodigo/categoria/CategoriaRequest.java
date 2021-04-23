package br.com.zupacademy.samara.casadocodigo.categoria;

import br.com.zupacademy.samara.casadocodigo.utils.annotations.NomeUnico;

import javax.validation.constraints.NotBlank;

public class CategoriaRequest {

    @NotBlank
    @NomeUnico
    private String nome;

    public String getNome() {
        return nome;
    }

    public Categoria toModel() {
        return new Categoria(this.nome);
    }
}
