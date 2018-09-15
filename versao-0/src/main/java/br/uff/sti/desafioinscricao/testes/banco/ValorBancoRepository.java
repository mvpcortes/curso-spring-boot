package br.uff.sti.desafioinscricao.testes.banco;

public interface ValorBancoRepository {

    ValorBanco incluir(ValorBanco valorBanco);

    void excluir(String nome);

    ValorBanco getValorA();

    ValorBanco getValorB();
}
