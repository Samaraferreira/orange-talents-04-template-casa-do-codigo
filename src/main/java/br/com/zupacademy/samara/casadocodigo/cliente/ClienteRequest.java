package br.com.zupacademy.samara.casadocodigo.cliente;

import br.com.zupacademy.samara.casadocodigo.estado.Estado;
import br.com.zupacademy.samara.casadocodigo.pais.Pais;
import br.com.zupacademy.samara.casadocodigo.utils.annotations.CpfOrCnpj;
import br.com.zupacademy.samara.casadocodigo.utils.annotations.ExistsValue;
import br.com.zupacademy.samara.casadocodigo.utils.annotations.UniqueValue;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ClienteRequest {

    @NotBlank
    @Email
    @UniqueValue(domainClass = Cliente.class, fieldName = "email")
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String sobrenome;

    @NotBlank
    @CpfOrCnpj
    @UniqueValue(domainClass = Cliente.class, fieldName = "email")
    private String documento;

    @NotBlank
    private String endereco;

    @NotBlank
    private String complemento;

    @NotBlank
    private String cidade;

    @NotBlank
    private String telefone;

    @NotBlank
    private String cep;

    @NotNull
    @ExistsValue(domainClass = Pais.class, fieldName = "id", message = "País não cadastrado")
    private Long paisId;

    private Long estadoId;

    public ClienteRequest(@NotBlank @Email String email, @NotBlank String nome, @NotBlank String sobrenome,
                          @NotBlank String documento, @NotBlank String endereco, @NotBlank String complemento,
                          @NotBlank String cidade, @NotBlank String telefone, @NotBlank String cep, @NotNull Long paisId, Long estadoId) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.telefone = telefone;
        this.cep = cep;
        this.paisId = paisId;
        this.estadoId = estadoId;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }

    public Long getPaisId() {
        return paisId;
    }

    public Long getEstadoId() {
        return estadoId;
    }

    public Cliente toModel(EntityManager manager) {
        Pais pais = manager.find(Pais.class, paisId);
        Estado estado = manager.find(Estado.class, estadoId);

        Cliente cliente = new Cliente(email, nome, sobrenome, documento, endereco, complemento,
                cidade, telefone, cep, pais, estado);

//        if (estadoId != null) {
//            Query query = manager.createQuery("select estado from Estado estado join estado.pais pais where estado.id = :estadoId and pais.id = :paisId");
//            query.setParameter("estadoId", estadoId);
//            query.setParameter("paisId", paisId);
//
//            List<?> resultList = query.getResultList();
//
////            Assert.state(resultList.size() >= 1, "Estado não encontrado");
//            if (resultList.size() < 1) {
////                throw new ResponseStatusException(
////                        HttpStatus.NOT_FOUND, "Estado não encontrado");
//            }
//
//            Estado estado = (Estado) resultList.get(0);
//
//            cliente.setEstado(estado);
//        }

        return cliente;
    }

//    public Cliente toModel(Pais pais, Estado estado) {
//        return new Cliente(email, nome, sobrenome, documento, endereco, complemento,
//                cidade, telefone, cep, pais, estado);
//    }
}
