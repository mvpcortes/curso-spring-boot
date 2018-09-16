package br.uff.sti.desafioinscricao;

public interface InscricaoDAO {

    void inscrever(String matricula, long idTurma);

    void desinscrever(String matricula, long idTurma);
}
