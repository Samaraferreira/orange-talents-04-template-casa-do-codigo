package br.com.zupacademy.samara.casadocodigo.estado;

import br.com.zupacademy.samara.casadocodigo.pais.Pais;
import br.com.zupacademy.samara.casadocodigo.utils.annotations.ExistsValue;
import br.com.zupacademy.samara.casadocodigo.utils.annotations.UniqueValue;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EstadoRequest {

    @NotBlank
    @UniqueValue(domainClass = Estado.class, fieldName = "nome")
    private String nome;

    @NotNull
    @ExistsValue(domainClass = Pais.class, fieldName = "id", message = "País não cadastrado")
    private Long paisId;

    public EstadoRequest(@NotBlank String nome, @NotNull Long paisId) {
        this.nome = nome;
        this.paisId = paisId;
    }

    public Estado toModel(EntityManager manager) {
        Pais pais = manager.find(Pais.class, paisId);
        return new Estado(nome, pais);
    }
}
