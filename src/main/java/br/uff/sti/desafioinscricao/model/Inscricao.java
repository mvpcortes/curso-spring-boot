package br.uff.sti.desafioinscricao.model;

public class Inscricao {

    private String matriculaAluno;

    private Long idTurma;

    public Inscricao(String matriculaAluno, Long idTurma) {
        this.matriculaAluno = matriculaAluno;
        this.idTurma = idTurma;
    }
}
