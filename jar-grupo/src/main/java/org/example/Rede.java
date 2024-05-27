package org.example;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.RedeInterface;
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
                JdbcTemplate con) {
        super(org.example.tipoHardware.REDE, nomeHardware, unidadeCaptacao, valorTotal, fkMaquina, looca, conexao, con);
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
                String queryRegistro = "INSERT INTO registro (valorRegistro, tempoCapturas, fkHardware) " +
                        "VALUES (?, CURRENT_TIMESTAMP, ?)";
                con.update(queryRegistro, interfaces.get(interfaceCorreta).getPacotesEnviados(), fkHardware);

                queryRegistro = "INSERT INTO registro (valorRegistro, tempoCapturas, fkHardware) " +
                        "VALUES (?, CURRENT_TIMESTAMP, ?)";
                con.update(queryRegistro, interfaces.get(interfaceCorreta).getPacotesRecebidos(), fkHardware);
            }
        };
        timer.schedule(tarefa, 1000, 2000);
    }
}
