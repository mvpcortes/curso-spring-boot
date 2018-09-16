package br.uff.sti.desafioinscricao.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Representa um anosemestre na inscrição. Esta classe é imutável, de tal forma que a cada operação é gerada uma nova versão da classe.
 */
public class AnoSemestre extends Number implements Comparable<Number>{

    private int intAnoSemestre;

    private static Set<Integer> setSemestresValidos = new HashSet<>();
    static {
        setSemestresValidos.add(1);
        setSemestresValidos.add(2);
    }
    public AnoSemestre(int anoSemestre) {
        this.intAnoSemestre = anoSemestre;

        if(!setSemestresValidos.contains(getSemestre())){
            throw new IllegalArgumentException("O anosemestre " + anoSemestre + " não possui um semestre válido");
        }
    }

    @Override
    public int intValue() {
        return intAnoSemestre;
    }

    @Override
    public long longValue() {
        return intAnoSemestre;
    }

    @Override
    public float floatValue() {
        return intAnoSemestre;
    }

    @Override
    public double doubleValue() {
        return intAnoSemestre;
    }

    @Override
    public String toString(){
        return Integer.toString(intAnoSemestre);
    }

    @Override
    public int compareTo(Number anoSemestre) {
        return Integer.compare(intAnoSemestre, anoSemestre.intValue());
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof  Number){
            return compareTo((Number)obj) == 0;
        }else{
            return false;
        }
    }

    public int getSemestre(){
        return intAnoSemestre - ((intAnoSemestre/10)*10);
    }

    public int getAno(){
        return intAnoSemestre/10;
    }

    public AnoSemestre incSemestre(){
        if(getSemestre() >=2){
            return new AnoSemestre((getAno()+1)*10 + 1);
        }else{
            return new AnoSemestre((getAno()*10)+getSemestre()+1);
        }
    }

    public AnoSemestre decSemestre(){
        if(getSemestre() <= 1){
            return new AnoSemestre((getAno()-1)*10 + 2);
        }else{
            return new AnoSemestre((getAno()*10)+getSemestre()-1);
        }
    }
}
