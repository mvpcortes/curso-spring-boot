package br.uff.sti.desafioinscricao.dao;

public interface InscricaoDAO {

    void inscrever(String matricula, long idTurma);

    void desinscrever(String matricula, long idTurma);

    boolean estaInscrito(String matricula, long idTurma);
}
