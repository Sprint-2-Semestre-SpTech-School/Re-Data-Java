package org.example;

import com.github.britooo.looca.api.core.Looca;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class Hardware {
    protected Looca looca = new Looca();
    protected Integer idHardware;
    protected String nome;
    protected String unidadeCaptacao;
    protected Double valorTotal;
    protected Integer fkMaquina;
    protected Conexao conexao = new Conexao();
    protected JdbcTemplate con = conexao.getConexaoBanco();

    public Hardware(Integer idHardware, String nome, String unidadeCaptacao, Double valorTotal, Integer fkMaquina) {
        this.idHardware = idHardware;
        this.nome = nome;
        this.unidadeCaptacao = unidadeCaptacao;
        this.valorTotal = valorTotal;
        this.fkMaquina = fkMaquina;
    }

    public Hardware() {
    }

    public abstract capturarDados(){}
}
