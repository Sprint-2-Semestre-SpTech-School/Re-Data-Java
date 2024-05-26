package org.example;

import com.github.britooo.looca.api.core.Looca;
import com.mysql.cj.log.Log;
import org.springframework.jdbc.core.JdbcTemplate;


public class Ram extends Hardware {
    public Ram(org.example.tipoHardware tipoHardware,
               String nomeHardware,
               String unidadeCaptacao,
               Double valorTotal,
               Integer fkMaquina,
               Looca looca,
               Conexao conexao,
               JdbcTemplate con) {
        super(org.example.tipoHardware.RAM, nomeHardware, unidadeCaptacao, valorTotal, fkMaquina, looca, conexao, con);
    }

    public Ram() {
    }

    @Override
    public void capturarDados() {
        tipoHardware = org.example.tipoHardware.RAM;
        nomeHardware = null;
        unidadeCaptacao = "Gb";
        valorTotal = (double) Math.round(looca.getMemoria().getTotal() / 1e9);
        fkMaquina = 500;

        String queryInfoHardware = "INSERT INTO infoHardware (tipoHardware, nomeHardware, unidadeCaptacao, valorTotal, fkMaquina)" +
                "VALUES (?, ?, ?, ? , ?)";
        con.update(queryInfoHardware, tipoHardware.getNome(), nomeHardware, unidadeCaptacao, valorTotal, fkMaquina);
    }

    @Override
    public void inserirDados() {
        String queryIdHardware = "SELECT LAST_INSERT_ID()";
        Integer fkHardware = con.queryForObject(queryIdHardware, Integer.class);

        String queryRegistro = "INSERT INTO registro (valorRegistro, tempoCapturas, fkHardware) " +
                "VALUES (?, CURRENT_TIMESTAMP, ?)";
        con.update(queryRegistro, looca.getMemoria().getEmUso(), fkHardware);
    }
}
