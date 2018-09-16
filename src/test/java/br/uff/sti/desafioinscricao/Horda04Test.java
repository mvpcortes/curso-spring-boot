package br.uff.sti.desafioinscricao;

import br.uff.sti.desafioinscricao.model.Turma;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@Disabled
@DisplayName("Horda-04 fazendo REST apis para consultar turmas e realizar a inscrição :D")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Horda04Test {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * Despois destes testes passando você pode iniciar a aplicação com o comando ./mvnw spring-boot:run e acessar a url
     * http://localhost:8080/api/turma e http://localhost:8080/api/turma?atual para ver o resultado
     *
     * Usamos também o manual [https://spring.io/guides/gs/testing-web/]
     */
    @Nested
    @DisplayName("Verificando comportamento de TurmaApi")
    class TurmaApiTest{

        @Test
        public void quando_pede_para_a_api_todas_as_turmas_entao_retorna_todas_elas(){

            Turma[] turmas = restTemplate.getForObject("/api/turma", Turma[].class);

            List<Turma> lisTurmas= Stream.of(turmas).sorted(Comparator.comparing(Turma::getId)).collect(Collectors.toList());

            assertEquals(24, lisTurmas.size());

            assertTurma(lisTurmas.get( 0),  1L, "C1", "COM0001", 20172, 20);
            assertTurma(lisTurmas.get( 1),  2L, "C2", "COM0001", 20172, 20);
            assertTurma(lisTurmas.get( 2),  3L, "C1", "COM0002", 20172, 40);
            assertTurma(lisTurmas.get( 3),  4L, "C1", "COM0003", 20172, 30);

            assertTurma(lisTurmas.get( 4),  5L, "M1", "MAT0001", 20172, 60);
            assertTurma(lisTurmas.get( 5),  6L, "M2", "MAT0001", 20172, 60);
            assertTurma(lisTurmas.get( 6),  7L, "M1", "MAT0002", 20172, 90);
            assertTurma(lisTurmas.get( 7),  8L, "M1", "MAT0003", 20172, 30);

            assertTurma(lisTurmas.get( 8),  9L, "C1", "COM0001", 20181, 20);
            assertTurma(lisTurmas.get( 9), 10L, "C2", "COM0001", 20181, 20);
            assertTurma(lisTurmas.get(10), 11L, "C1", "COM0002", 20181, 40);
            assertTurma(lisTurmas.get(11), 12L, "C1", "COM0003", 20181, 30);

            assertTurma(lisTurmas.get(12), 13L, "M1", "MAT0001", 20181, 60);
            assertTurma(lisTurmas.get(13), 14L, "M2", "MAT0001", 20181, 60);
            assertTurma(lisTurmas.get(14), 15L, "M1", "MAT0002", 20181, 90);
            assertTurma(lisTurmas.get(15), 16L, "M1", "MAT0003", 20181, 30);

            assertTurma(lisTurmas.get(16), 17L, "C1", "COM0001", 20182, 20);
            assertTurma(lisTurmas.get(17), 18L, "C2", "COM0001", 20182, 20);
            assertTurma(lisTurmas.get(18), 19L, "C1", "COM0002", 20182, 40);
            assertTurma(lisTurmas.get(19), 20L, "C1", "COM0003", 20182, 30);

            assertTurma(lisTurmas.get(20), 21L, "M1", "MAT0001", 20182, 60);
            assertTurma(lisTurmas.get(21), 22L, "M2", "MAT0001", 20182, 60);
            assertTurma(lisTurmas.get(22), 23L, "M1", "MAT0002", 20182, 90);
            assertTurma(lisTurmas.get(23), 24L, "M1", "MAT0003", 20182, 30);
        }

        @Test
        public void quando_pede_para_a_api_so_turmas_atuais_entao_retorna_so_turmas_de_20182(){

            Turma[] turmas = restTemplate.getForObject("/api/turma?atual=true", Turma[].class);

            List<Turma> lisTurmas= Stream.of(turmas).sorted(Comparator.comparing(Turma::getId)).collect(Collectors.toList());

            assertEquals(8, lisTurmas.size());

            assertTurma(lisTurmas.get(0), 17L, "C1", "COM0001", 20182, 20);
            assertTurma(lisTurmas.get(1), 18L, "C2", "COM0001", 20182, 20);
            assertTurma(lisTurmas.get(2), 19L, "C1", "COM0002", 20182, 40);
            assertTurma(lisTurmas.get(3), 20L, "C1", "COM0003", 20182, 30);

            assertTurma(lisTurmas.get(4), 21L, "M1", "MAT0001", 20182, 60);
            assertTurma(lisTurmas.get(5), 22L, "M2", "MAT0001", 20182, 60);
            assertTurma(lisTurmas.get(6), 23L, "M1", "MAT0002", 20182, 90);
            assertTurma(lisTurmas.get(7), 24L, "M1", "MAT0003", 20182, 30);
        }

        private void assertTurma(Turma turma, long id, String codigoTurma, String codigoDisciplina, int intAnoSemestre, int intCargaHoraria) {
            assertEquals(id, turma.getId().longValue());
            assertEquals(codigoTurma, turma.getCodigoTurma());
            assertEquals(codigoDisciplina, turma.getCodigoDisciplina());
            assertEquals(intAnoSemestre, turma.getAnoSemestre().intValue());
            assertEquals(intCargaHoraria, turma.getCargaHoraria());
        }

    }
}
