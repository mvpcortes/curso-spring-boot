package br.uff.sti.desafioinscricao;

import br.uff.sti.desafioinscricao.dao.ValorBancoDAO;
import br.uff.sti.desafioinscricao.model.ValorBanco;
import br.uff.sti.desafioinscricao.service.ValorBancoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Este teste explica as principais etapas e ferramentas para fazer testes
 */
//@Disabled
@DisplayName("Tutorial de testes, com explicações de testes usando spring-boot")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class Grupo00SpringBootTest {

    @Autowired
    private ValorBancoDAO valorBancoDAO;

    @Autowired
    private ValorBancoService valorBancoService;

    /**
     * Este teste é de integração, pois usa o banco para armazenar os valores de soma
     */
    @Test
    public void quando_usa_ValoresBanco_com_valores_120_n120_com_dependencia_com_banco_entao_retorna_zero(){
        //constrói os valores de teste;
        ValorBanco valorBancoA = new ValorBanco("A",  120);
        ValorBanco valorBancoB = new ValorBanco("B", -120);

        valorBancoDAO.incluir(valorBancoA);
        valorBancoDAO.incluir(valorBancoB);


        //realiza a operação sobre teste
        int resultado = valorBancoService.somaValores();


        //verifica o valor somado
        assertEquals(resultado, 0);

        //apaba os valores usados  no teste
        valorBancoDAO.excluir(valorBancoA.getNome());
        valorBancoDAO.excluir(valorBancoB.getNome());
    }

    /**
     * Este teste é de integração, pois usa o banco para armazenar os valores de soma
     */
    @Test
    @Transactional
    @Rollback
    public void quando_usa_ValoresBanco_com_valores_120_n120_com_dependencia_com_banco_entao_retorna_zero_transacional(){
        //constrói os valores de teste;
        ValorBanco valorBancoA = new ValorBanco("A",  120);
        ValorBanco valorBancoB = new ValorBanco("B", -120);

        valorBancoDAO.incluir(valorBancoA);
        valorBancoDAO.incluir(valorBancoB);


        //realiza a operação sobre teste
        int resultado = valorBancoService.somaValores();


        //verifica o valor somado
        assertEquals(resultado, 0);

        //não precisa apagar os valores do banco, pois eles sofrerão rollback
    }
}
