package org.example;

import com.github.britooo.looca.api.core.Looca;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.List;

public class Disco extends Hardware{
    public Disco(org.example.tipoHardware tipoHardware,
                 String nomeHardware,
                 String unidadeCaptacao,
                 Double valorTotal,
                 Integer fkMaquina,
                 Looca looca,
                 Conexao conexao,
                 JdbcTemplate con) {
        super(org.example.tipoHardware.DISCO, nomeHardware, unidadeCaptacao, valorTotal, fkMaquina, looca, conexao, con);
    }

    public Disco() {

    }

    @Override
    public void capturarDados() {
        tipoHardware = org.example.tipoHardware.DISCO;
        nomeHardware = looca.getGrupoDeDiscos().getDiscos().get(0).getModelo();
        unidadeCaptacao = "Gb";
        valorTotal = Double.valueOf(looca.getGrupoDeDiscos().getDiscos().get(0).getTamanho());

        String queryInfoHardware = "INSERT INTO infoHardware (tipoHardware, nomeHardware, unidadeCaptacao, valorTotal, fkMaquina)" +
                "VALUES (?, ?, ?, ? , ?)";
        con.update(queryInfoHardware, tipoHardware.getNome(), nomeHardware, unidadeCaptacao, valorTotal, fkMaquina);

        String queryIdHardware = "SELECT LAST_INSERT_ID()";
        Integer fkHardware = con.queryForObject(queryIdHardware, Integer.class);

        String queryRegistro = "INSERT INTO registro (valorRegistro, tempoCapturas, fkHardware) " +
                "VALUES (?, CURRENT_TIMESTAMP, ?)";
        con.update(queryRegistro, looca.getGrupoDeDiscos().getDiscos().get(0).getBytesDeLeitura(), fkHardware);

        queryRegistro = "INSERT INTO registro (valorRegistro, tempoCapturas, fkHardware) " +
                "VALUES (?, CURRENT_TIMESTAMP, ?)";
        con.update(queryRegistro, looca.getGrupoDeDiscos().getDiscos().get(0).getBytesDeEscritas(), fkHardware);

        queryRegistro = "INSERT INTO registro (valorRegistro, tempoCapturas, fkHardware) " +
                "VALUES (?, CURRENT_TIMESTAMP, ?)";
        con.update(queryRegistro, looca.getGrupoDeDiscos().getDiscos().get(0).getLeituras(), fkHardware);

        queryRegistro = "INSERT INTO registro (valorRegistro, tempoCapturas, fkHardware) " +
                "VALUES (?, CURRENT_TIMESTAMP, ?)";
        con.update(queryRegistro, looca.getGrupoDeDiscos().getDiscos().get(0).getEscritas(), fkHardware);
    }
}
