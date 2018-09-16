package br.uff.sti.desafioinscricao.dao;

import br.uff.sti.desafioinscricao.model.ValorBanco;

public interface ValorBancoDAO {

    ValorBanco incluir(ValorBanco valorBanco);

    void excluir(String nome);

    ValorBanco getValorA();

    ValorBanco getValorB();
}
