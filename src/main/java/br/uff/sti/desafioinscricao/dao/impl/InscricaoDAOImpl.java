package br.uff.sti.desafioinscricao.dao.impl;

import br.uff.sti.desafioinscricao.dao.InscricaoDAO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InscricaoDAOImpl implements InscricaoDAO {

    private final JdbcTemplate jdbcTemplate;

    public InscricaoDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void inscrever(String matricula, long idTurma) {
        jdbcTemplate.update("INSERT INTO inscricao VALUES (?, ?)", matricula, idTurma);
    }

    @Override
    public void desinscrever(String matricula, long idTurma) {
        jdbcTemplate.update("DELETE FROM inscricao WHERE matricula_aluno = ? AND ID_TURMA = ?", matricula, idTurma);
    }
}
