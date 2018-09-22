package br.uff.sti.desafioinscricao;

import br.uff.sti.desafioinscricao.dao.ValorBancoDAO;
import br.uff.sti.desafioinscricao.model.ValorBanco;
import br.uff.sti.desafioinscricao.service.SomadorSimples;
import br.uff.sti.desafioinscricao.service.ValorBancoService;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

//@Disabled
@DisplayName("Grupo-Tutorial de testes, com explicações básicas de java e testes unitários")
public class Grupo00Test {

    @Nested
    public class AprendendoTestesUnitariosTest {

        @Test
        public void quando_soma_1_mais_1_entao_igual_a_2(){

            //constrói os valores e dependências de teste
            int valorA = 1;
            int valorB = 1;

            //realiza teste sobre o item testado (SUT - system under test)
            int soma = valorA + valorB;


            //verifica o resultado do teste
            assertEquals(2, soma);

            //detrói os valores e dependências do teste
            //Não tem nada para apagar, pois valorA, valorB e soma serão destruídos quando a função sair da pilha de execução.
        }

        @Test
        public void quando_usa_somador_com_valores_10_e_20_e_soma_entao_entao_retorna_a_soma(){

            //constrói os valores e dependências de teste
            SomadorSimples somador = new SomadorSimples(10, 20);

            //realiza teste sobre o item testado (SUT - system under test)
            int soma = somador.soma();


            //verifica o resultado do teste
            assertEquals( 30, soma);

            //detrói os valores e dependências do teste
            //Não tem nada para apagar, pois somador será limpado pelo garbage collector
        }

        @Test
        public void quando_usa_ValoresBanco_com_valores_300_e_301_em_teste_unitario_entao_retorna_601(){

            //constrói os valores e dependências de teste;
            ValorBanco valorBancoA = new ValorBanco("A", 300);
            ValorBanco valorBancoB = new ValorBanco("B", 301);


            //faz dependências dublês (doubles) para simular valores do banco
            ValorBancoDAO valorBancoDAO = mock(ValorBancoDAO.class);
            doReturn(valorBancoA).when(valorBancoDAO).getValorA();
            doReturn(valorBancoB).when(valorBancoDAO).getValorB();

            //realiza a associação do objeto em teste com o stub
            ValorBancoService valorBancoService = new ValorBancoService(valorBancoDAO);


            //realiza teste chamando método do br.uff.sti.desafioinscricao.service
            int soma = valorBancoService.somaValores();


            //verifica o resultado do teste
            assertEquals( 601, soma);

            //detrói os valores e dependências do teste
            //Não tem nada para apagar, pois somador será limpado pelo garbage collector
        }

        //Agora usando before e after para realizar os testes
        @Nested
        class UsandoBeforeAfter{

            ValorBanco valorBancoA;
            ValorBanco valorBancoB;
            ValorBancoDAO valorBancoRepository;

            //constrói os valores e dependências de teste;
            @BeforeEach
            public void init(){
                valorBancoA = new ValorBanco("A", 5);
                valorBancoB = new ValorBanco("B", 6);


                //faz dependências dublês (doubles) para simular valores do banco
                valorBancoRepository = mock(ValorBancoDAO.class);
                doReturn(valorBancoA).when(valorBancoRepository).getValorA();
                doReturn(valorBancoB).when(valorBancoRepository).getValorB();
            }

            @Test
            public void quando_usa_ValoresBanco_com_valores_5_6_em_teste_unitario_entao_retorna_11(){
                //realiza a associação do objeto em teste com o stub
                ValorBancoService valorBancoService = new ValorBancoService(valorBancoRepository);


                //realiza teste chamando método do br.uff.sti.desafioinscricao.service
                int soma = valorBancoService.somaValores();


                //verifica o resultado do teste
                assertEquals( 11, soma);
            }

            @AfterEach
            public void destroy(){
                //Não tem nada para apagar, pois somador será limpado pelo garbage collector
            }

        }

    }
}
