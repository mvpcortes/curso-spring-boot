package br.uff.sti.desafioinscricao.dao.impl;

import br.uff.sti.desafioinscricao.dao.ValorBancoDAO;
import br.uff.sti.desafioinscricao.model.ValorBanco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ValorBancoDAOImpl implements ValorBancoDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public ValorBanco incluir(ValorBanco valorBanco){

        jdbcTemplate.update("INSERT INTO valor_banco (nome, valor) VALUES (?, ?)",
                valorBanco.getNome(),
                valorBanco.getValor());
        return valorBanco;
    }

    @Override
    public void excluir(String nome){
        jdbcTemplate.update("DELETE FROM valor_banco WHERE nome = ?", nome);
    }

    @Override
    public ValorBanco getValorA() {
        return jdbcTemplate.queryForObject("SELECT nome, valor FROM valor_banco WHERE nome = 'A'", new RowMapper<ValorBanco>(){

            @Override
            public ValorBanco mapRow(ResultSet resultSet, int i) throws SQLException {
                return new ValorBanco(
                        resultSet.getString(1),
                        resultSet.getInt(2)
                );
            }
        });
    }

    /**
     * Observe que este método utiliza a notação nova do java 8 para FunctionalInterface
     */
    @Override
    public ValorBanco getValorB() {
        return jdbcTemplate.queryForObject("SELECT nome, valor FROM valor_banco WHERE nome = 'B'", (resultSet, i) ->
                new ValorBanco(
                resultSet.getString(1),
                resultSet.getInt(2)
        ));
    }


}
