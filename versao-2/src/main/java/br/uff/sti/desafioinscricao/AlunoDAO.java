package br.uff.sti.desafioinscricao;

import br.uff.sti.desafioinscricao.model.AnoSemestre;

public interface AlunoDAO {

    long getCargaHorariaTotal(String matricula, AnoSemestre anoSemestre);
}
