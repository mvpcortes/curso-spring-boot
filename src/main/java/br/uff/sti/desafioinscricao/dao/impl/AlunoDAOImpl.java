package br.uff.sti.desafioinscricao.dao.impl;

import br.uff.sti.desafioinscricao.dao.AlunoDAO;
import br.uff.sti.desafioinscricao.model.AnoSemestre;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AlunoDAOImpl implements AlunoDAO {

    private final JdbcTemplate jdbcTemplate;

    public AlunoDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long getCargaHorariaTotal(String matricula, AnoSemestre anoSemestre) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(
                " SELECT SUM(t.carga_horaria) FROM inscricao insc "
                + "  INNER JOIN turma t on t.id = insc.id_turma "
                + " WHERE t.ano_semestre = ? AND insc.matricula_aluno = ?",
                Long.class, anoSemestre.intValue(), matricula))
                .orElse(0L);
    }

    /**
     * Atente-se que queryForObject retornará nulo se não achar aluno nenhum neste caso. tente usar optional.
     * @param matricula
     * @return
     */
    @Override
    public boolean existeAluno(String matricula) {
        return Optional.of(jdbcTemplate.queryForObject(
                "SELECT count(*) FROM aluno WHERE matricula = ?",
                new Object[]{matricula},
                Long.class
        )).orElse(0L) > 0;
    }
}
