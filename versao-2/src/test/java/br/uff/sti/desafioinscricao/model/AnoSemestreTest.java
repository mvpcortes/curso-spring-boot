package br.uff.sti.desafioinscricao.model;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnoSemestreTest {

    @Test
    public void quando_constroi_um_anosemestre_com_ano_semestre_valido_entao_ok(){

        AnoSemestre anoSemestre = new AnoSemestre(20101);

        assertEquals(20101, anoSemestre.intValue());
    }

    @Test
    public void quando_obtem_o_valor_int_do_anosemestre_entao_ok(){

        AnoSemestre anoSemestre = new AnoSemestre(20121);

        assertEquals(20121, anoSemestre.intValue());
    }

    @Test
    public void quando_obtem_o_valor_long_do_anosemestre_entao_ok(){

        AnoSemestre anoSemestre = new AnoSemestre(20121);

        assertEquals(20121L, anoSemestre.intValue());
    }

    @Test
    public void quando_obtem_o_valor_double_do_anosemestre_entao_ok(){

        AnoSemestre anoSemestre = new AnoSemestre(20121);

        assertEquals(20121.0, anoSemestre.intValue());
    }

    @Test
    public void quando_obtem_o_valor_float_do_anosemestre_entao_ok(){

        AnoSemestre anoSemestre = new AnoSemestre(20121);

        assertEquals(20121.0F, anoSemestre.intValue());
    }

    /**
     * Anos semestres diferentes de 1 e 2 devem gerar IllegalStateException
     */
    @Test()
    public void quando_cria_ano_semestre_ano_semestre_invalido_entao_gera_falha(){
        assertThrows(IllegalArgumentException.class,
                ()->{
                    new AnoSemestre(20105);
                });
    }

    @Test
    public void quando_compara_ano_semestre_com_um_ano_mais_novo_entao_retorna_menor_negativo(){
        final int intAnoSemestreA = 20101;
        final int intAnoSemestreB = 20102;

        final int valorComparacao = new AnoSemestre(intAnoSemestreA).compareTo(intAnoSemestreB);

        assertTrue(valorComparacao < 0);
    }

    @Test
    public void quando_compara_ano_semestre_com_um_ano_mais_velho_entao_retorna_maior_positivo(){
        final int intAnoSemestreA = 20151;
        final int intAnoSemestreB = 20102;

        final int valorComparacao = new AnoSemestre(intAnoSemestreA).compareTo(intAnoSemestreB);

        assertTrue(valorComparacao > 0);
    }

    @Test
    public void quando_compara_ano_semestre_com_um_ano_igual_entao_retorna_zeo(){
        final int intAnoSemestreA = 20301;
        final int intAnoSemestreB = 20301;

        final int valorComparacao = new AnoSemestre(intAnoSemestreA).compareTo(intAnoSemestreB);

        assertEquals(0, valorComparacao);
    }

    @Nested
    class quando_anosemestre_20101_e_incrementa_semestre{

        final AnoSemestre anoSemestre = new AnoSemestre(20101);

        @Test
        public void entao_os_objetos_sao_diferentes(){

            AnoSemestre anoSemestreIncrementado = anoSemestre.incSemestre();

            assertNotSame(anoSemestre, anoSemestreIncrementado);
        }

        @Test
        public void entao_o_ano_semestre_eh_20102(){
            AnoSemestre anoSemestreIncrementado = anoSemestre.incSemestre();

            assertEquals(20102, anoSemestreIncrementado.intValue());
        }

    }

    @Nested
    class quando_anosemestre_20122_e_incrementa_semestre{

        final AnoSemestre anoSemestre = new AnoSemestre(20122);

        final AnoSemestre anoSemestreIncrementado = anoSemestre.incSemestre();
        @Test
        public void entao_os_objetos_sao_diferentes(){

            assertNotSame(anoSemestre, anoSemestreIncrementado);
        }

        @Test
        public void entao_o_ano_semestre_eh_20131(){
            assertEquals(20131, anoSemestreIncrementado.intValue());
        }

    }

    @Nested
    class quando_anosemestre_20182_e_decrementa_semestre{

        final AnoSemestre anoSemestre = new AnoSemestre(20182);

        final AnoSemestre anoSemestreDecrementado = anoSemestre.decSemestre();

        @Test
        public void entao_os_objetos_sao_diferentes(){

            assertNotSame(anoSemestre, anoSemestreDecrementado);
        }

        @Test
        public void entao_o_ano_semestre_eh_20181(){

            assertEquals(20181, anoSemestreDecrementado.intValue());
        }

    }

    @Nested
    class quando_anosemestre_20201_e_decrementa_semestre{

        final AnoSemestre anoSemestre = new AnoSemestre(20201);

        AnoSemestre anoSemestreDecrementado = anoSemestre.decSemestre();
        @Test
        public void entao_os_objetos_sao_diferentes(){


            assertNotSame(anoSemestre, anoSemestreDecrementado);
        }

        @Test
        public void entao_o_ano_semestre_eh_20192(){

            assertEquals(20192, anoSemestreDecrementado.intValue());
        }

    }
}