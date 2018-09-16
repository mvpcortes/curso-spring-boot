package br.uff.sti.desafioinscricao.service;

import br.uff.sti.desafioinscricao.dao.ValorBancoDAO;
import org.springframework.stereotype.Service;

@Service
public class ValorBancoService {

    private ValorBancoDAO valorBancoRepository;

    public ValorBancoService(ValorBancoDAO valorBancoRepository) {
        this.valorBancoRepository = valorBancoRepository;
    }

    public int somaValores(){
        return valorBancoRepository.getValorA().getValor() + valorBancoRepository.getValorB().getValor();
    }
}
