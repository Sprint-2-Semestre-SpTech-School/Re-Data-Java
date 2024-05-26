package org.example;

import com.github.britooo.looca.api.core.Looca;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class Hardware {
    protected tipoHardware tipoHardware;
    protected String nomeHardware;
    protected String unidadeCaptacao;
    protected Double valorTotal;
    protected Integer fkMaquina;
    protected Looca looca = new Looca();
    protected Conexao conexao = new Conexao();
    protected JdbcTemplate con = conexao.getConexaoBanco();

    public Hardware(org.example.tipoHardware tipoHardware,
                    String nomeHardware,
                    String unidadeCaptacao,
                    Double valorTotal,
                    Integer fkMaquina,
                    Looca looca,
                    Conexao conexao,
                    JdbcTemplate con) {
        this.tipoHardware = tipoHardware;
        this.nomeHardware = nomeHardware;
        this.unidadeCaptacao = unidadeCaptacao;
        this.valorTotal = valorTotal;
        this.fkMaquina = fkMaquina;
        this.looca = looca;
        this.conexao = conexao;
        this.con = con;
    }

    public Hardware() {
    }

    public abstract void capturarDados();
    public abstract void inserirDados();
}
