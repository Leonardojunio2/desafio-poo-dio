package br.com.dio.desafio.dominio;

//Diferente de interface, que funciona como um contrato envolvendo metodos
//A classe abstrata pode funcionar melhor como classe pai e filha, com parametros e metodos expandidos - Herança
public abstract class Conteudo {

    protected static final double XP_PADRAO = 10d;

    private String titulo;
    private String descricao;

    //Implica que as classes filhas tem de implementar
    public abstract double calcularXp();

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
