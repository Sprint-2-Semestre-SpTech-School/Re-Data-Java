package org.example;

import com.github.britooo.looca.api.core.Looca;
import org.springframework.jdbc.core.JdbcTemplate;

public class Maquina {
    Looca looca = new Looca();
    private Integer idMaquina;
    private String usuario;
    private String destino;
    private String sistemaOperacional;
    private Double temperatura;
    private Long tempoAtividade;
    private Integer fkProjeto;
    private Integer fkEmpresa;
    private Conexao conexao = new Conexao();
    private JdbcTemplate con = conexao.getConexaoBanco();

    public Maquina(String usuario,
                   String destino,
                   String sistemaOperacional,
                   Double temperatura,
                   Long tempoAtividade,
                   Integer fkProjeto,
                   Integer fkEmpresa) {
        this.looca = looca;
        this.idMaquina = idMaquina;
        this.usuario = usuario;
        this.destino = destino;
        this.sistemaOperacional = sistemaOperacional;
        this.temperatura = temperatura;
        this.tempoAtividade = tempoAtividade;
        this.fkProjeto = fkProjeto;
        this.fkEmpresa = fkEmpresa;
    }
    public Maquina(Integer fkProjeto, Integer fkEmpresa) {
        this.fkProjeto = fkProjeto;
        this.fkEmpresa = fkEmpresa;
    }
    public Maquina() {
    }

    public void capturarDadosMaquina(){
        usuario = System.getProperty("user.name");
        destino = "Extract";
        sistemaOperacional = looca.getSistema().getSistemaOperacional();
        temperatura = looca.getTemperatura().getTemperatura();
        tempoAtividade = looca.getSistema().getTempoDeAtividade() / 3600; // Valor em horas
    }
    public void inserirDadosMaquina(){
        con.update("INSERT INTO Maquina (usuario, destino, sistemaOperacional, temperatura, tempoAtividade, " +
                "fkProjeto, fkEmpresa) values (?, ?, ?, ?, ?, ?, ?)", usuario, destino, sistemaOperacional,
                temperatura, tempoAtividade, fkProjeto, fkEmpresa);
    }
}
