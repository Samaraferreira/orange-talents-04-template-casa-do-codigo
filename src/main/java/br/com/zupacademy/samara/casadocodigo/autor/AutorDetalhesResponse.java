package br.com.zupacademy.samara.casadocodigo.autor;

public class AutorDetalhesResponse {
    private String nome;
    private String descricao;

    public AutorDetalhesResponse(Autor autor) {
        nome = autor.getNome();
        descricao = autor.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
