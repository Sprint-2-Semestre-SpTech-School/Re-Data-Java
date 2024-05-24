package org.example;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.RedeInterface;
import org.springframework.jdbc.core.JdbcTemplate;

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
        nomeHardware = looca.getRede().getParametros().getHostName();
        unidadeCaptacao = "%";
        valorTotal = Double.valueOf(looca.getProcessador().getFrequencia());

        String queryInfoHardware = "INSERT INTO infoHardware (tipoHardware, nomeHardware, unidadeCaptacao, valorTotal, fkMaquina)" +
                "VALUES (?, ?, ?, ? , ?)";
        con.update(queryInfoHardware, tipoHardware.getNome(), nomeHardware, unidadeCaptacao, valorTotal, fkMaquina);

        String queryIdHardware = "SELECT LAST_INSERT_ID()";
        Integer fkHardware = con.queryForObject(queryIdHardware, Integer.class); // Espera que o retorno seja inteiro

        String queryRegistro = "INSERT INTO registro (valorRegistro, tempoCapturas, fkHardware) " +
                "VALUES (?, CURRENT_TIMESTAMP, ?)";
        con.update(queryRegistro, looca.getProcessador().getUso(), fkHardware);
    }
}
