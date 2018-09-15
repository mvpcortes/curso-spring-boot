package br.uff.sti.desafioinscricao.testes.banco;

import org.springframework.stereotype.Service;

@Service
public class ValorBancoService {

    private ValorBancoRepository valorBancoRepository;

    public ValorBancoService(ValorBancoRepository valorBancoRepository) {
        this.valorBancoRepository = valorBancoRepository;
    }

    public int somaValores(){
        return valorBancoRepository.getValorA().getValor() + valorBancoRepository.getValorB().getValor();
    }
}
