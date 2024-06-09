package org.example;

import com.github.britooo.looca.api.core.Looca;
import org.example.Jdbc.Conexao;
import org.example.Jdbc.ConexaoServer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.w3c.dom.ls.LSOutput;

import java.util.List;

public class Maquina {
    Looca looca = new Looca();
    private Integer idMaquina;
    private String usuario;
    private String sistemaOperacional;
    private Double temperatura;
    private Long tempoAtividade;
    private Integer fkProjeto;
    private Integer fkEmpresa;
    private Conexao conexao = new Conexao();
    private ConexaoServer conexao02 = new ConexaoServer();
    private JdbcTemplate con = conexao.getConexaoBanco();
    private JdbcTemplate con02 = conexao02.getConexaoBanco();

    public Maquina(
            Looca looca,
            Integer idMaquina,
            String usuario,
            String sistemaOperacional,
            Double temperatura,
            Long tempoAtividade,
            Integer fkProjeto,
            Integer fkEmpresa,
            Conexao conexao,
            ConexaoServer conexao02
    ) {
        this.looca = looca;
        this.idMaquina = idMaquina;
        this.usuario = usuario;
        this.sistemaOperacional = sistemaOperacional;
        this.temperatura = temperatura;
        this.tempoAtividade = tempoAtividade;
        this.fkProjeto = fkProjeto;
        this.fkEmpresa = fkEmpresa;
        this.conexao = conexao;
        this.conexao02 = conexao02;
    }

    public Maquina(Integer fkProjeto, Integer fkEmpresa) {
        this.fkProjeto = fkProjeto;
        this.fkEmpresa = fkEmpresa;
    }

    public Maquina() {

    }

    public void capturarDadosMaquina() {

        usuario = System.getProperty("user.name");
        sistemaOperacional = looca.getSistema().getSistemaOperacional();
        temperatura = looca.getTemperatura().getTemperatura();
        tempoAtividade = looca.getSistema().getTempoDeAtividade() / 3600; // Valor em horas

    }

    public void inserirDadosMaquina() {
        try {
            con02.update("INSERT INTO Maquina (usuario, sistemaOperacional, temperatura, tempoAtividade, " +
                            "fkProjeto, fkEmpresa) values (?, ?, ?, ?, ?, ?)", usuario, sistemaOperacional,
                    temperatura, tempoAtividade, 400, 1);

            con.update("INSERT INTO Maquina (usuario, sistemaOperacional, temperatura, tempoAtividade, " +
                            "fkProjeto, fkEmpresa) values (?, ?, ?, ?, ?, ?)", usuario, sistemaOperacional,
                    temperatura, tempoAtividade, 400, 1);

        } catch (RuntimeException e) {
            System.out.println("Erro de conexão 'Maquina' sql" + e.getMessage());
        }
    }

    public Integer consultarId() {
        List<Integer> idsMaquina;

        String comandoSql = ("SELECT idMaquina from Maquina");
        idsMaquina = con.queryForList(comandoSql, Integer.class);
        return idsMaquina.get(idsMaquina.size() - 1);
    }

    public boolean consultarUsuario() {
        List<String> idsMaquina;

        String comandoSql = ("SELECT usuario from Maquina");
        idsMaquina = con.queryForList(comandoSql, String.class);

        for (int i = 0; i < idsMaquina.size(); i++) {
            if (idsMaquina.get(i).equals(usuario)){
                System.out.println("Usuario Existe no Banco");
                return true;
            }
            else System.out.println("Usuario não existe no banco, por favor cadastrar");
        }
        return false;
    }
    public Looca getLooca() {
        return looca;
    }

    public void setLooca(Looca looca) {
        this.looca = looca;
    }

    public Integer getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(Integer idMaquina) {
        this.idMaquina = idMaquina;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public Long getTempoAtividade() {
        return tempoAtividade;
    }

    public void setTempoAtividade(Long tempoAtividade) {
        this.tempoAtividade = tempoAtividade;
    }

    public Integer getFkProjeto() {
        return fkProjeto;
    }

    public void setFkProjeto(Integer fkProjeto) {
        this.fkProjeto = fkProjeto;
    }

    public Integer getFkEmpresa() {
        return fkEmpresa;
    }

    public void setFkEmpresa(Integer fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }

    @Override
    public String toString() {
        return """
                idMaquina: %d
                usuario: '%s'
                sistemaOperacional: '%s'
                temperatura: %.2f
                tempoAtividade: %d
                fkProjeto: %d
                fkEmpresa: %d""".formatted(idMaquina,
                usuario,
                sistemaOperacional,
                temperatura,
                tempoAtividade,
                fkProjeto,
                fkEmpresa);
    }
}
