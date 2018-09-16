package br.uff.sti.desafioinscricao.model;

public class ValorBanco {

    private String nome;
    private int valor;

    public ValorBanco(String nome, int valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public int getValor() {
        return valor;
    }
}
