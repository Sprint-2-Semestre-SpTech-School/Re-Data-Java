package org.example;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.RedeInterface;
import org.example.logging.GeradorLog;
import org.example.logging.Modulo;
import org.example.logging.Tabelas;
import org.example.logging.TagNiveisLog;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Rede extends Hardware {
    public Rede(org.example.tipoHardware tipoHardware,
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

    public Rede() {
    }

    @Override
    public void capturarDados() {
        tipoHardware = org.example.tipoHardware.REDE;
        nomeHardware = looca.getRede().getGrupoDeInterfaces().getInterfaces().get(0).getNomeExibicao();
        unidadeCaptacao = "pacotes";
        valorTotal = null;
        fkMaquina = 500;

        String queryInfoHardware = "INSERT INTO infoHardware (tipoHardware, nomeHardware, unidadeCaptacao, valorTotal, fkMaquina)" +
                "VALUES (?, ?, ?, ? , ?)";
        con.update(queryInfoHardware, tipoHardware.getNome(), nomeHardware, unidadeCaptacao, valorTotal, fkMaquina);

        GeradorLog.log(TagNiveisLog.INFO,"Iniciando captura de dados ...", Modulo.CAPTURA_HARDWARE);

        GeradorLog.log(TagNiveisLog.INFO, "Type: %s".formatted(tipoHardware), Modulo.CAPTURA_HARDWARE);
        GeradorLog.log(TagNiveisLog.INFO, "Name: %s".formatted(nomeHardware), Modulo.CAPTURA_HARDWARE);
        GeradorLog.log(TagNiveisLog.INFO, "Capture unit: %s".formatted(unidadeCaptacao), Modulo.CAPTURA_HARDWARE);
        GeradorLog.log(TagNiveisLog.INFO, "Total value: %.2f".formatted(valorTotal), Modulo.CAPTURA_HARDWARE);
        GeradorLog.log(TagNiveisLog.INFO, "Dados enviados com sucesso! Re;Data Local/MySQL DB: Table: %s".formatted(Tabelas.INFO_HARDWARE.getDescricaoTabela()), Modulo.ENVIO_DADOS);

        // con02.update(queryInfoHardware, tipoHardware.getNome(), nomeHardware, unidadeCaptacao, valorTotal, fkMaquina);
    }

    @Override
    public void inserirDados() {
        String queryIdHardware = "SELECT LAST_INSERT_ID()";
        Integer fkHardware = con.queryForObject(queryIdHardware, Integer.class); // Espera que o retorno seja inteiro

        Integer indiceInterfaceComIpv4 = null;
        List<RedeInterface> interfaces = looca.getRede().getGrupoDeInterfaces().getInterfaces();

        for (int i = 0; i < interfaces.size(); i++) {
            if (!interfaces.get(i).getEnderecoIpv4().isEmpty()) {
                indiceInterfaceComIpv4 = i;
                break;
            }
        }

        Timer timer = new Timer();
        Integer interfaceCorreta = indiceInterfaceComIpv4;
        TimerTask tarefa = new TimerTask() {
            @Override
            public void run() {
                String queryRegistro = "INSERT INTO registro (nomeRegistro, valorRegistro, tempoCapturas, fkHardware) " +
                        "VALUES (?, ?, CURRENT_TIMESTAMP, ?)";
                con.update(queryRegistro, interfaces.get(interfaceCorreta).getPacotesEnviados(), fkHardware);

                GeradorLog.log(TagNiveisLog.INFO,"Iniciando captura de dados: Máquina: %d...".formatted(fkMaquina), Modulo.CAPTURA_HARDWARE);
                GeradorLog.log(TagNiveisLog.INFO, "Name: %s".formatted(interfaceCorreta), Modulo.CAPTURA_HARDWARE);
                GeradorLog.log(TagNiveisLog.INFO, "sent packets: %s".formatted(interfaces.get(interfaceCorreta).getBytesEnviados()), Modulo.CAPTURA_HARDWARE);
                GeradorLog.log(TagNiveisLog.INFO, "Dados enviados com sucesso! Re;Data Local/MySQL DB: Table: %s".formatted(Tabelas.REGISTRO.getDescricaoTabela()), Modulo.ENVIO_DADOS);

                // con02.update(queryRegistro, interfaces.get(interfaceCorreta).getPacotesEnviados(), fkHardware);

                queryRegistro = "INSERT INTO registro (nomeRegistro, valorRegistro, tempoCapturas, fkHardware) " +
                        "VALUES (?, ?, CURRENT_TIMESTAMP, ?)";
                con.update(queryRegistro, interfaces.get(interfaceCorreta).getPacotesRecebidos(), fkHardware);

                GeradorLog.log(TagNiveisLog.INFO,"Iniciando captura de dados: Máquina: %d...".formatted(fkMaquina), Modulo.CAPTURA_HARDWARE);
                GeradorLog.log(TagNiveisLog.INFO, "Name: %s".formatted(interfaceCorreta), Modulo.CAPTURA_HARDWARE);
                GeradorLog.log(TagNiveisLog.INFO, "received packets: %s".formatted(interfaces.get(interfaceCorreta).getBytesRecebidos()), Modulo.CAPTURA_HARDWARE);
                GeradorLog.log(TagNiveisLog.INFO, "Dados enviados com sucesso! Re;Data Local/MySQL DB: Table: %s".formatted(Tabelas.REGISTRO.getDescricaoTabela()), Modulo.ENVIO_DADOS);

                // con02.update(queryRegistro, interfaces.get(interfaceCorreta).getPacotesRecebidos(), fkHardware);
            }
        };
        timer.schedule(tarefa, 1000, 2000);
    }
}
