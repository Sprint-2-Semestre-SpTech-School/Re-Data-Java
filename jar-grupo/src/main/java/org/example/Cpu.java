package org.example;

import com.github.britooo.looca.api.core.Looca;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Cpu extends Hardware {
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
        tipoHardware = org.example.tipoHardware.CPU;
        nomeHardware = looca.getProcessador().getNome();
        unidadeCaptacao = "%";
        valorTotal = (double) looca.getProcessador().getFrequencia() / 1e9;
        fkMaquina = 500;

        String queryInfoHardware = "INSERT INTO infoHardware (tipoHardware, nomeHardware, unidadeCaptacao, valorTotal, fkMaquina)" +
                "VALUES (?, ?, ?, ? , ?)";
        con.update(queryInfoHardware, tipoHardware.getNome(), nomeHardware, unidadeCaptacao, valorTotal, fkMaquina);
    }

    @Override
    public void inserirDados() {
        String queryIdHardware = "SELECT LAST_INSERT_ID()";
        Integer fkHardware = con.queryForObject(queryIdHardware, Integer.class); // Espera que o retorno seja inteiro

        Timer timer = new Timer();
        TimerTask tarefa = new TimerTask() {
            @Override
            public void run() {
                String queryRegistro = "INSERT INTO registro (valorRegistro, tempoCapturas, fkHardware) " +
                        "VALUES (?, CURRENT_TIMESTAMP, ?)";
                con.update(queryRegistro, looca.getProcessador().getUso(), fkHardware);
            }
        };
        timer.schedule(tarefa, 3000, 4000);
    }
}

