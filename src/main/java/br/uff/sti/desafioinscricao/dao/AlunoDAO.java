package br.uff.sti.desafioinscricao.dao;

import br.uff.sti.desafioinscricao.model.AnoSemestre;

public interface AlunoDAO {

    long getCargaHorariaTotal(String matricula, AnoSemestre anoSemestre);

    boolean existeAluno(String matricula);
}
