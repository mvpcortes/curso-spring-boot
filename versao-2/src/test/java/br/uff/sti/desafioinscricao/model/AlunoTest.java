package br.uff.sti.desafioinscricao.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlunoTest {

    @Test
    public void quando_chama_toString_entao_retorna_matricula_e_nome(){
        final String nome = "MARIO SILVA";
        final String matricula = "111111111";

        final String resultado = new Aluno(matricula, nome, "curso").toString(); //chame o resultado aqui
        assertEquals(matricula + " - " + nome, resultado);
    }
}