package br.uff.sti.desafioinscricao.model;

public class Turma {

    private Long id;

    private String codigoTurma;

    private String codigoDisciplina;

    private int anoSemestre;

    private int cargaHoraria;

    public Turma(Long id, String codigoTurma, String codigoDisciplina, int anoSemestre, int cargaHoraria) {
        this.id = id;
        this.codigoTurma = codigoTurma;
        this.codigoDisciplina = codigoDisciplina;
        this.anoSemestre = anoSemestre;
        this.cargaHoraria = cargaHoraria;
    }

    public Long getId() {
        return id;
    }

    public String getCodigoTurma() {
        return codigoTurma;
    }

    public String getCodigoDisciplina() {
        return codigoDisciplina;
    }

    public int getAnoSemestre() {
        return anoSemestre;
    }
}
