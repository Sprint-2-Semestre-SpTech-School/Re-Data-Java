package org.example.Capturas;

import com.github.britooo.looca.api.core.Looca;
import org.example.Jdbc.Conexao;
import org.example.Jdbc.ConexaoServer;
import org.springframework.jdbc.core.JdbcTemplate;

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
               ConexaoServer conexao02,
               JdbcTemplate con,
               JdbcTemplate con02) {
        super(tipoHardware, nomeHardware, unidadeCaptacao, valorTotal, fkMaquina, looca, conexao, conexao02, con, con02);
    }

    public Cpu(Integer fkMaquina){
        this.fkMaquina = fkMaquina;
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

    try {
        JdbcTemplate con = conexao.getConexaoBanco();


        String queryInfoHardware = "INSERT INTO infoHardware (tipoHardware, nomeHardware, unidadeCaptacao, valorTotal, fkMaquina)" +
                "VALUES (?, ?, ?, ? , ?)";
        con.update(queryInfoHardware, tipoHardware.getNome(), nomeHardware, unidadeCaptacao, valorTotal, fkMaquina);

        try{
            JdbcTemplate con02 = conexao02.getConexaoBanco();
            con02.update(queryInfoHardware, tipoHardware.getNome(), nomeHardware, unidadeCaptacao, valorTotal, fkMaquina);
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }

//        GeradorLog.log(TagNiveisLog.INFO, "Dados enviados com sucesso! Re;Data Local/MySQL DB: Table: %s".formatted(Tabelas.INFO_HARDWARE.getDescricaoTabela()), Modulo.ENVIO_DADOS);

    }catch (RuntimeException e){
        System.out.println("Erro de conexão 'Cpu' sql" + e.getMessage());
//        GeradorLog.log(TagNiveisLog.ERROR, "Erro de conexão SQL: %s".formatted(Tabelas.INFO_HARDWARE.getDescricaoTabela()), Modulo.ALERTA);
    }
    }

    @Override
    public void inserirDados() {
        try {
            String nomeRegistro = "usoCpu";

            String queryIdHardware = "SELECT LAST_INSERT_ID()";
            Integer fkHardware = con.queryForObject(queryIdHardware, Integer.class); // Espera que o retorno seja inteiro

            Timer timer = new Timer();
            TimerTask tarefa = new TimerTask() {
                @Override
                public void run() {

                    String queryRegistro = "INSERT INTO registro (nomeRegistro, valorRegistro, tempoCapturas, fkHardware) " +
                            "VALUES (?, ?, CURRENT_TIMESTAMP, ?)";
                    con.update(queryRegistro, nomeRegistro, looca.getProcessador().getUso(), fkHardware);
                    try {
                        con02.update(queryRegistro, nomeRegistro, looca.getProcessador().getUso(), fkHardware);
                    } catch (RuntimeException e){
                        System.out.println("Erro de Conexão sql Server" + e.getMessage());
                    }

//                    GeradorLog.log(TagNiveisLog.INFO,"Iniciando captura de dados: Máquina: %d...".formatted(fkMaquina), Modulo.CAPTURA_HARDWARE);
//                    GeradorLog.log(TagNiveisLog.INFO, "Name: %s".formatted(nomeRegistro), Modulo.CAPTURA_HARDWARE);
//                    GeradorLog.log(TagNiveisLog.INFO, "CPU usage: %s".formatted(looca.getProcessador().getUso()), Modulo.CAPTURA_HARDWARE);
//                    GeradorLog.log(TagNiveisLog.INFO, "Dados enviados com sucesso! Re;Data Local/MySQL DB: Table: %s".formatted(Tabelas.REGISTRO.getDescricaoTabela()), Modulo.ENVIO_DADOS);

                }
            };
            timer.schedule(tarefa, 1000, 5000);
        }catch (RuntimeException e){
            System.out.println("Erro de conexão 'Cpu' mysql" + e.getMessage());
        }
    }
}

