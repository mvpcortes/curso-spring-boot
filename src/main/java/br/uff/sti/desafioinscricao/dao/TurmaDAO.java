package br.uff.sti.desafioinscricao.dao;

import br.uff.sti.desafioinscricao.model.AnoSemestre;
import br.uff.sti.desafioinscricao.model.Turma;

import java.util.List;
import java.util.Optional;

public interface TurmaDAO {

    List<Turma> findTurmasInscritas(String matricula, AnoSemestre anoSemestre);

    Optional<Turma> findTurma(long idTurma);
}
