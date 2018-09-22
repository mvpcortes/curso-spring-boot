package br.uff.sti.desafioinscricao.service;

import br.uff.sti.desafioinscricao.dao.ValorBancoDAO;
import org.springframework.stereotype.Service;

@Service
public class ValorBancoService {

    private ValorBancoDAO valorBancoDAO;

    public ValorBancoService(ValorBancoDAO valorBancoDAO) {
        this.valorBancoDAO = valorBancoDAO;
    }

    public int somaValores(){
        return valorBancoDAO.getValorA().getValor() + valorBancoDAO.getValorB().getValor();
    }
}
