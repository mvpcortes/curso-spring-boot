package br.uff.sti.desafioinscricao;

import br.uff.sti.desafioinscricao.dao.InscricaoDAO;
import br.uff.sti.desafioinscricao.dao.TurmaDAO;
import br.uff.sti.desafioinscricao.model.AnoSemestre;
import br.uff.sti.desafioinscricao.service.InscricaoService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

//@Disabled
@DisplayName("Horda-03 fazendo as regras para realizar a inscrição de um aluno, checando a regra de CH")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class Horda03Test {

    @Autowired
    private InscricaoService inscricaoService;

    @Autowired
    private InscricaoDAO inscricaoDAO;


    @Autowired
    private TurmaDAO turmaDAO;

    final String MATRICULA = "1111";
    final long ID_TURMA_A  = 21;
    final long ID_TURMA_B  = 22;
    final long ID_TURMA_C  = 23;
    final long ID_TURMA_D  = 24;
    final AnoSemestre ANO_SEMESTRE = new AnoSemestre(20182);

    @DisplayName("Fazendo testes de igualdade do AnoSemestre que precisaremos para a funcionalidade de inscrição")
    @Nested
    public class AnoSemestreTest{

        @Nested
        class quando_ano_semestre_20101{
            final AnoSemestre anoSemestre = new AnoSemestre(20101);

            @Test
            public void entao_eh_igual_a_int_20101(){
                assertTrue(anoSemestre.equals(20101));
            }

            @Test
            public void entao_eh_diferente_de_int_20102(){
                assertFalse(anoSemestre.equals(20102));
            }

            @Test
            public void entao_eh_igual_a_anosemestre_20101(){
                assertTrue(new AnoSemestre(20101).equals(anoSemestre));
            }

            @Test
            public void entao_eh_diferente_de_anosemestre_20102(){
                assertFalse(new AnoSemestre(20102).equals( anoSemestre));
            }

            @Test
            public void entao_eh_diferente_de_string_20101(){
                assertFalse(anoSemestre.equals("20102"));
            }
        }
    }

    @Test
    public void quando_tenta_inscrever_mas_turma_nao_existe_entao_gera_excecao(){
        final String message = assertThrows(IllegalArgumentException.class, ()->
                inscricaoService.inscreveAluno(MATRICULA, -10L))
                .getLocalizedMessage();

        assertEquals("A turma -10 não existe", message);
    }

    @Nested
    class inscricao_na_turma_A {

        @BeforeEach
        public void init(){
            //realiza a inscrição
            inscricaoService.inscreveAluno(MATRICULA, ID_TURMA_A);
        }

        @AfterEach
        public void destroy(){
            //remove a inscricao
            inscricaoDAO.desinscrever(MATRICULA, ID_TURMA_A);
            inscricaoDAO.desinscrever(MATRICULA, ID_TURMA_B);
            inscricaoDAO.desinscrever(MATRICULA, ID_TURMA_C);
            inscricaoDAO.desinscrever(MATRICULA, ID_TURMA_D);
        }

        @Test
        public void quando_aluno_nao_existe_gera_excecao(){
            final String message = assertThrows(IllegalArgumentException.class, ()->
                    inscricaoService.inscreveAluno("xuxu_xaxa", ID_TURMA_B))
                    .getLocalizedMessage();

            assertEquals("O aluno 'xuxu_xaxa' não existe", message);
        }

        @Test
        public void quando_aluno_nao_possui_nenhuma_inscricao_entao_pode_inscrever_uma_turma() {
            //verifica se a inscrição foi feita
            List<Long> list = turmaDAO.findTurmasInscritas(MATRICULA, ANO_SEMESTRE).stream().map(t->t.getId()).collect(toList());
            assertEquals(1, list.size());
            assertTrue(list.contains(ID_TURMA_A));
        }

        @Nested
        public class E_inscricao_na_turma_B {
            @BeforeEach
            public void init(){
                //realiza a inscrição
                inscricaoService.inscreveAluno(MATRICULA, ID_TURMA_B);
            }
            @Test
            public void quando_aluno_possui_uma_turma_entao_pode_inscrever_mais_uma () {

                //verifica se as inscrições foi feita
                List<Long> list = turmaDAO.findTurmasInscritas(MATRICULA, ANO_SEMESTRE).stream().map(t->t.getId()).collect(toList());
                assertEquals(2, list.size());
                assertTrue(list.contains(ID_TURMA_A));
                assertTrue(list.contains(ID_TURMA_B));
            }

            @Nested
            public class E_inscricao_na_turma_C {
                @Test
                public void quando_aluno_duas_turmas_e_nao_resta_ch_para_a_nova_turma_entao_gera_excecao() {
                    final String message = assertThrows(IllegalArgumentException.class, ()->{
                        inscricaoService.inscreveAluno(MATRICULA, ID_TURMA_C);
                    }).getLocalizedMessage();

                    assertEquals("Não é possível inscrever 1111 na turma 23 porque a carga horária será excedida [120, 210]"
                            , message);
                }
            }
        }

    }

}
