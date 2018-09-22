package br.uff.sti.desafioinscricao.model;

public class Inscricao {

    private String matriculaAluno;

    private Long idTurma;

    public Inscricao() {
    }

    public Inscricao(String matriculaAluno, Long idTurma) {
        this.matriculaAluno = matriculaAluno;
        this.idTurma = idTurma;
    }

    public void setMatriculaAluno(String matriculaAluno) {
        this.matriculaAluno = matriculaAluno;
    }

    public void setIdTurma(Long idTurma) {
        this.idTurma = idTurma;
    }

    public String getMatriculaAluno() {
        return matriculaAluno;
    }

    public Long getIdTurma() {
        return idTurma;
    }
}
