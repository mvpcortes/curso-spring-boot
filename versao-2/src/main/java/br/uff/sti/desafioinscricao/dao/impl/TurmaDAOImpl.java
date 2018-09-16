package br.uff.sti.desafioinscricao.dao.impl;

import br.uff.sti.desafioinscricao.dao.TurmaDAO;
import br.uff.sti.desafioinscricao.model.AnoSemestre;
import br.uff.sti.desafioinscricao.model.Turma;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TurmaDAOImpl implements TurmaDAO {

    private final JdbcTemplate jdbcTemplate;

    public TurmaDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * (rs, rowNum) -> new Turma(
     *                         rs.getLong(1),
     *                         rs.getString(2),
     *                         rs.getString(3),
     *                         rs.getInt(4),
     *                         rs.getInt(5)),
     * @param matricula
     * @param anoSemestre
     * @return
     */
    @Override
    public List<Turma> findTurmasInscritas(String matricula, AnoSemestre anoSemestre) {
        return jdbcTemplate.query("SELECT t.id, t.codigo_turma, t.codigo_disciplina, t.ano_semestre, t.carga_horaria FROM turma t "
                        + " INNER JOIN inscricao insc ON insc.id_turma = t.id "
                        + " WHERE insc.matricula_aluno = ? AND t.ano_semestre = ?",
                new Object[]{
                        matricula,
                        anoSemestre.intValue()
                },
                (rs, rowNum) -> new Turma(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5)
                )
        );
    }

    @Override
    public Optional<Turma> findTurma(long idTurma) {
        try{
            return Optional.of(jdbcTemplate.queryForObject("SELECT id, codigo_turma, codigo_disciplina, ano_semestre, carga_horaria FROM turma " +
                            " WHERE id = ?",
                    new Object[]{idTurma},
                    (rs, rowNum) -> new Turma(
                            rs.getLong(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getInt(5)
                    )));
        }catch(EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }
}
