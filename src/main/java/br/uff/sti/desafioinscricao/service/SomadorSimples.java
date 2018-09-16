package br.uff.sti.desafioinscricao.service;

public class SomadorSimples {
    final int valorA;
    final int valorB;

    public SomadorSimples(int valorA, int valorB) {
        this.valorA = valorA;
        this.valorB = valorB;
    }

    public int soma(){
        return valorA + valorB;
    }
}
