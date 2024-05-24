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
        nomeHardware = null;
        unidadeCaptacao = "Gb";
        valorTotal = Double.valueOf(looca.getMemoria().getTotal());

        String queryInfoHardware = "INSERT INTO infoHardware (tipoHardware, nomeHardware, unidadeCaptacao, valorTotal, fkMaquina)" +
                "VALUES (?, ?, ?, ? , ?)";
        con.update(queryInfoHardware, tipoHardware.getNome(), nomeHardware, unidadeCaptacao, valorTotal, fkMaquina);

        String queryIdHardware = "SELECT LAST_INSERT_ID()";
        Integer fkHardware = con.queryForObject(queryIdHardware, Integer.class);

        String queryRegistro = "INSERT INTO registro (valorRegistro, tempoCapturas, fkHardware) " +
                "VALUES (?, CURRENT_TIMESTAMP, ?)";
        con.update(queryRegistro, looca.getMemoria().getEmUso(), fkHardware);
    }
}
