package br.uff.sti.desafioinscricao.model;

public class Aluno {

    private String matricula;

    private String nome;

    private String codigoCurso;


    public Aluno(String matricula, String nome, String codigoCurso) {
        this.matricula = matricula;
        this.nome = nome;
        this.codigoCurso = codigoCurso;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public String getCodigoCurso() {
        return codigoCurso;
    }

    @Override
    public String toString(){
        return String.format("%s - %s", matricula, nome);
    }
}
