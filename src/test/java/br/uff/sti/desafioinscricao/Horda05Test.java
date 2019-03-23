package br.uff.sti.desafioinscricao;

import br.uff.sti.desafioinscricao.dao.InscricaoDAO;
import br.uff.sti.desafioinscricao.model.Turma;
import io.github.bonigarcia.seljup.SeleniumExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@Disabled
@DisplayName("Horda-05 vendo telas de turmas atuais e de inscrever aluno em turma")
@ExtendWith({SeleniumExtension.class, SpringExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Horda05Test {

    @LocalServerPort
    public int port;

    @Autowired
    private InscricaoDAO inscricaoDAO;

    ChromeDriver get(ChromeDriver webDriver, String path) {
        //resize window to mobile form (mobile-first
        webDriver.manage().window().setSize(new Dimension(360, 640));
        webDriver.get("http://localhost:" + port + "/" + path);

        //implicit wait
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        return webDriver;
    }

    @DisplayName("Verificando toString de turma")
    @Nested
    public class VerificaToStringTurma{

        @Test
        public void codigo_da_disciplina_e_da_turma_gera_toString_da_turma(){
            Turma turma = new Turma( 1L,  "A1",  "XXX00004",  20182,  10);

            assertEquals("XXX00004(A1)", turma.toString());
        }
    }
    @DisplayName("Verificando tela de turma")
    @Nested
    public class VerificaTelaDeTurma {

        @Test
        public void quando_acessa_tela_de_turmas_atuais_entao_atribui_texto_com_ano_semeswtre(ChromeDriver webDriver) {
            get(webDriver, "turma");

            WebElement webElement = webDriver.findElementById("titulo_ano_semestre");

            assertEquals("Turmas de 20182", webElement.getText());

        }

        @Test
        public void quando_acessa_tela_de_turmas_atuais_entao_gera_tabela(ChromeDriver webDriver) {
            get(webDriver, "turma");

            final List<WebElement> lisTurmas = webDriver.findElementsByClassName("row_turma");

            assertEquals(8, lisTurmas.size());

            assertTurma(lisTurmas.get(0), 17L, "C1", "COM0001", 20);
            assertTurma(lisTurmas.get(1), 18L, "C2", "COM0001", 20);
            assertTurma(lisTurmas.get(2), 19L, "C1", "COM0002", 40);
            assertTurma(lisTurmas.get(3), 20L, "C1", "COM0003", 30);

            assertTurma(lisTurmas.get(4), 21L, "M1", "MAT0001", 60);
            assertTurma(lisTurmas.get(5), 22L, "M2", "MAT0001", 60);
            assertTurma(lisTurmas.get(6), 23L, "M1", "MAT0002", 90);
            assertTurma(lisTurmas.get(7), 24L, "M1", "MAT0003", 30);

        }

        private void assertTurma(WebElement webElement, long id, String codigoTurma, String codigoDisciplina, int ch) {
            assertEquals(webElement.findElement(By.className("id_turma")).getText(), Long.toString(id));
            assertEquals(webElement.findElement(By.className("codigo_turma")).getText(), codigoTurma);
            assertEquals(webElement.findElement(By.className("codigo_disciplina")).getText(), codigoDisciplina);
            assertEquals(webElement.findElement(By.className("carga_horaria")).getText(), Integer.toString(ch));
        }
    }


    @DisplayName("Verificando tela de inscricao")
    @Nested
    public class VerificaTelaDeInscricao {

        private final long ID_TURMA = 20L;

        private final String MATRICULA = "1114";
        @AfterEach
        public void destroy(){
            inscricaoDAO.desinscrever(MATRICULA, ID_TURMA);
        }

        @Test
        public void quando_inscreve_aluno_em_turma_entao_mostra_sucesso(ChromeDriver webDriver){
            get(webDriver, "inscricao");

            //preenche campos
            webDriver.findElementById("input_matricula").sendKeys(MATRICULA);

            Select select = new Select(webDriver.findElementById("input_turmas"));

            select.selectByValue(Long.toString(ID_TURMA));

            //clica em submeter
            webDriver.findElementById("submit_form").click();

            //verifica se está na tela de sucesso
            assertEquals("A inscrição do aluno '1114' na turma COM0003(C1) foi realizada com sucesso!", webDriver.findElementById("mensagem").getText());
        }

        @Test
        public void quando_inscreve_aluno_inexistente_em_turma_entao_falha(ChromeDriver webDriver){
            get(webDriver, "inscricao");

            //preenche campos
            webDriver.findElementById("input_matricula").sendKeys("-10");

            Select select = new Select(webDriver.findElementById("input_turmas"));

            select.selectByValue(Long.toString(ID_TURMA));

            //clica em submeter
            webDriver.findElementById("submit_form").click();

            //verifica se está na tela de sucesso
            assertEquals("O aluno '-10' não existe", webDriver.findElementById("erro").getText());
        }
    }
}