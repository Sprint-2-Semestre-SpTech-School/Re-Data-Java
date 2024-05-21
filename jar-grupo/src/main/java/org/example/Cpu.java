package org.example;

import com.github.britooo.looca.api.core.Looca;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.swing.*;

public class Cpu extends Hardware{
    public Cpu(org.example.tipoHardware tipoHardware,
               String nomeHardware,
               String unidadeCaptacao,
               Double valorTotal,
               Integer fkMaquina,
               Looca looca,
               Conexao conexao,
               JdbcTemplate con) {
        super(org.example.tipoHardware.CPU, nomeHardware, unidadeCaptacao, valorTotal, fkMaquina, looca, conexao, con);
    }

    public Cpu() {
    }

    @Override
    public void capturarDados() {
        nomeHardware = looca.getProcessador().getNome();
        valorTotal = Double.valueOf(looca.getProcessador().getFrequencia());

        String queryInfoHardware = "INSERT INTO infoHardware (tipoHardware, nomeHardware, unidadeCaptacao, valorTotal, fkMaquina)" +
                "VALUES (?, ?, ?, ? ,?)";
        con.update(queryInfoHardware, tipoHardware.getNome(), nomeHardware, unidadeCaptacao, valorTotal, fkMaquina);

        String queryIdHardware = "SELECT LAST_INSERT_ID()";
        Integer fkHardware = con.queryForObject(queryIdHardware, Integer.class); // Espera que o retorno seja int

        String queryRegistro = "INSERT INTO registro (valorRegistro, tempoCapturas, fkHardware) " +
                "VALUES (?, CURRENT_TIMESTAMP, ?)";
            con.update(queryRegistro, looca.getProcessador().getUso(), fkHardware);
}
}
