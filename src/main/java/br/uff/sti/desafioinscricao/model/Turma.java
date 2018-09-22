package br.uff.sti.desafioinscricao.model;

public class Turma {

    private Long id;

    private String codigoTurma;

    private String codigoDisciplina;

    private int anoSemestre;

    private int cargaHoraria;

    public Turma(){

    }

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

    public AnoSemestre getAnoSemestre() {
        return new AnoSemestre(anoSemestre);
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    @Override
    public String toString(){
        return String.format("%s(%s)", codigoDisciplina, codigoTurma);
    }
}
