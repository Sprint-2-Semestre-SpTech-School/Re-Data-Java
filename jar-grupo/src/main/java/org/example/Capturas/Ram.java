package org.example.Capturas;

import com.github.britooo.looca.api.core.Looca;
import org.example.Jdbc.Conexao;
import org.example.Jdbc.ConexaoServer;
import org.example.Maquina;
import org.example.logging.GeradorLog;
import org.example.logging.Modulo;
import org.example.logging.TagNiveisLog;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Timer;
import java.util.TimerTask;


public class Ram extends Hardware {

    public Ram(org.example.tipoHardware tipoHardware,
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

    public Ram(Integer fkMaquina){
        this.fkMaquina = fkMaquina;
    }

    public Ram() {
    }

    @Override
    public void capturarDados() {
        Maquina maquina = new Maquina();

        tipoHardware = org.example.tipoHardware.RAM;
        nomeHardware = null;
        unidadeCaptacao = "Gb";
        valorTotal = (double) Math.round(looca.getMemoria().getTotal() / 1e9);
        fkMaquina = 500;

        String queryInfoHardware = "INSERT INTO infoHardware (tipoHardware, nomeHardware, unidadeCaptacao, valorTotal, fkMaquina)" +
                "VALUES (?, ?, ?, ? , ?)";
        con.update(queryInfoHardware, tipoHardware.getNome(), nomeHardware, unidadeCaptacao, valorTotal, fkMaquina);
        try{
            con02.update(queryInfoHardware, tipoHardware.getNome(), nomeHardware, unidadeCaptacao, valorTotal, fkMaquina);
        } catch (RuntimeException e) {
            e.getMessage();
        }

        GeradorLog.log(TagNiveisLog.INFO,"Iniciando captura de dados ...", Modulo.CAPTURA_HARDWARE);

//        GeradorLog.log(TagNiveisLog.INFO, "Type: %s".formatted(tipoHardware), Modulo.CAPTURA_HARDWARE);
//        GeradorLog.log(TagNiveisLog.INFO, "Name: %s".formatted(nomeHardware), Modulo.CAPTURA_HARDWARE);
//        GeradorLog.log(TagNiveisLog.INFO, "Capture unit: %s".formatted(unidadeCaptacao), Modulo.CAPTURA_HARDWARE);
//        GeradorLog.log(TagNiveisLog.INFO, "Total value: %.2f".formatted(valorTotal), Modulo.CAPTURA_HARDWARE);
//        GeradorLog.log(TagNiveisLog.INFO, "Dados enviados com sucesso! Re;Data Local/MySQL DB: Table: %s".formatted(Tabelas.INFO_HARDWARE.getDescricaoTabela()), Modulo.ENVIO_DADOS);

    }

    @Override
    public void inserirDados() {
        String queryIdHardware = "SELECT LAST_INSERT_ID()";
        Integer fkHardware = con.queryForObject(queryIdHardware, Integer.class);

        String nomeRegistro = "usoRam";

        Timer timer = new Timer();
        TimerTask tarefa = new TimerTask() {
            @Override
            public void run() {
                String queryRegistro = "INSERT INTO registro (nomeRegistro, valorRegistro, tempoCapturas, fkHardware) " +
                        "VALUES (?, ?, CURRENT_TIMESTAMP, ?)";
                con.update(queryRegistro, nomeRegistro, (looca.getMemoria().getEmUso() / 1e9) * 100 / valorTotal, fkHardware);
                try {
                    con02.update(queryRegistro, nomeRegistro, (looca.getMemoria().getEmUso() / 1e9) * 100 / valorTotal, fkHardware);
                } catch (RuntimeException e){
                    e.getMessage();
                }

//                GeradorLog.log(TagNiveisLog.INFO,"Iniciando captura de dados: Máquina: %d...".formatted(fkMaquina), Modulo.CAPTURA_HARDWARE);
//                GeradorLog.log(TagNiveisLog.INFO, "Name: %s".formatted(nomeRegistro), Modulo.CAPTURA_HARDWARE);
//                GeradorLog.log(TagNiveisLog.INFO, "RAM usage: %s".formatted(looca.getMemoria().getEmUso()), Modulo.CAPTURA_HARDWARE);
//                GeradorLog.log(TagNiveisLog.INFO, "Dados enviados com sucesso! Re;Data Local/MySQL DB: Table: %s".formatted(Tabelas.REGISTRO.getDescricaoTabela()), Modulo.ENVIO_DADOS);

            }
        };
        timer.schedule(tarefa, 1000, 5000);
    }
}
