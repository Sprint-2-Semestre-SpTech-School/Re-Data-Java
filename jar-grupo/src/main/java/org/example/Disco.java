package org.example;

import com.github.britooo.looca.api.core.Looca;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Disco extends Hardware {
    public Disco(org.example.tipoHardware tipoHardware,
                 String nomeHardware,
                 String unidadeCaptacao,
                 Double valorTotal,
                 Integer fkMaquina,
                 Looca looca,
                 Conexao conexao,
                 ConexaoServer conexao02,
                 JdbcTemplate con,
                 JdbcTemplate con02) {
        super(tipoHardware, nomeHardware, unidadeCaptacao, valorTotal, fkMaquina, looca, conexao, conexao02, con, con02);
    }

    public Disco() {

    }

    @Override
    public void capturarDados() {
        tipoHardware = org.example.tipoHardware.DISCO;
        nomeHardware = looca.getGrupoDeDiscos().getDiscos().get(0).getModelo();
        unidadeCaptacao = "Gb";
        valorTotal = (double) Math.round(looca.getGrupoDeDiscos().getDiscos().get(0).getTamanho() / 1e9);
        fkMaquina = 500;

        try {
            String queryInfoHardware = "INSERT INTO infoHardware (tipoHardware, nomeHardware, unidadeCaptacao, valorTotal, fkMaquina)" +
                    "VALUES (?, ?, ?, ? , ?)";
            con.update(queryInfoHardware, tipoHardware.getNome(), nomeHardware, unidadeCaptacao, valorTotal, fkMaquina);
            con02.update(queryInfoHardware, tipoHardware.getNome(), nomeHardware, unidadeCaptacao, valorTotal, fkMaquina);

        } catch (RuntimeException e) {
            System.out.println("Erro de conexão 'Disco' sql" + e.getMessage());
        }
    }

    @Override
    public void inserirDados() {
        String queryIdHardware = "SELECT LAST_INSERT_ID()";
        Integer fkHardware = con.queryForObject(queryIdHardware, Integer.class);

        try {
            Timer timer = new Timer();
            TimerTask tarefa = new TimerTask() {
                @Override
                public void run() {
                    String queryRegistro = "INSERT INTO registro (valorRegistro, tempoCapturas, fkHardware) " +
                            "VALUES (?, CURRENT_TIMESTAMP, ?)";
                    con.update(queryRegistro, looca.getGrupoDeDiscos().getDiscos().get(0).getBytesDeLeitura(), fkHardware);
                    con02.update(queryRegistro, looca.getGrupoDeDiscos().getDiscos().get(0).getBytesDeLeitura(), fkHardware);

                    queryRegistro = "INSERT INTO registro (valorRegistro, tempoCapturas, fkHardware) " +
                            "VALUES (?, CURRENT_TIMESTAMP, ?)";
                    con.update(queryRegistro, looca.getGrupoDeDiscos().getDiscos().get(0).getBytesDeEscritas(), fkHardware);
                    con02.update(queryRegistro, looca.getGrupoDeDiscos().getDiscos().get(0).getBytesDeEscritas(), fkHardware);

                    queryRegistro = "INSERT INTO registro (valorRegistro, tempoCapturas, fkHardware) " +
                            "VALUES (?, CURRENT_TIMESTAMP, ?)";
                    con.update(queryRegistro, looca.getGrupoDeDiscos().getDiscos().get(0).getLeituras(), fkHardware);
                    con02.update(queryRegistro, looca.getGrupoDeDiscos().getDiscos().get(0).getLeituras(), fkHardware);

                    queryRegistro = "INSERT INTO registro (valorRegistro, tempoCapturas, fkHardware) " +
                            "VALUES (?, CURRENT_TIMESTAMP, ?)";
                    con.update(queryRegistro, looca.getGrupoDeDiscos().getDiscos().get(0).getEscritas(), fkHardware);
                    con02.update(queryRegistro, looca.getGrupoDeDiscos().getDiscos().get(0).getEscritas(), fkHardware);

                    queryRegistro = "INSERT INTO registro (valorRegistro, tempoCapturas, fkHardware) " +
                            "VALUES (?, CURRENT_TIMESTAMP, ?)";
                    con.update(queryRegistro, looca.getGrupoDeDiscos().getDiscos().get(0).getTempoDeTransferencia(), fkHardware);
                    con02.update(queryRegistro, looca.getGrupoDeDiscos().getDiscos().get(0).getTempoDeTransferencia(), fkHardware);

                    queryRegistro = "INSERT INTO registro (valorRegistro, tempoCapturas, fkHardware) " +
                            "VALUES (?, CURRENT_TIMESTAMP, ?)";
                    con.update(queryRegistro, looca.getGrupoDeDiscos().getVolumes().get(0).getDisponivel(), fkHardware);
                    con02.update(queryRegistro, looca.getGrupoDeDiscos().getVolumes().get(0).getDisponivel(), fkHardware);
                }
            };
            timer.schedule(tarefa, 3000, 4000);
        } catch (RuntimeException e){
            System.out.println("Erro de conexão 'Disco' sql" + e.getMessage());
        }
    }
}
