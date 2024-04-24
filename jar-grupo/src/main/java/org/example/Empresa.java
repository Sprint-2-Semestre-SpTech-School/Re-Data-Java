package org.example;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;

public class Empresa {
    private Integer idEmpresa;
    private String nomeEmpresa;
    private String CNPJ;
    private String CEP;
    private String estado;
    private String logradouro;
    private String numero;
    private String bairro;
    private String complemento;
    private Conexao conexao = new Conexao();
    private JdbcTemplate con = conexao.getConexaoBanco();

    public Empresa(Integer idEmpresa,
                   String nomeEmpresa,
                   String CNPJ,
                   String CEP,
                   String estado,
                   String logradouro,
                   String numero,
                   String bairro,
                   String complemento) {
        this.idEmpresa = idEmpresa;
        this.nomeEmpresa = nomeEmpresa;
        this.CNPJ = CNPJ;
        this.CEP = CEP;
        this.estado = estado;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.complemento = complemento;

        this.conexao = conexao;
        this.con = con;
    }

    public Empresa() {
    }

    public void adicionarEmpresa(){
        nomeEmpresa = "ReData.inc";
        CNPJ = "53719031000163";

        con.update("INSERT INTO Empresa (nomeEmpresa, CNPJ) values (?, ?)", nomeEmpresa, CNPJ);
    }

    public List<Integer> obterIdsEmpresa(){
            String comandoSql = ("SELECT idEmpresa from Empresa");
            List<Integer> idsEmpresa = con.queryForList(comandoSql, Integer.class);
            return idsEmpresa;
    }

    public void adicionarLocalizacaoEmpresa(){
        CEP = "02587043";
        estado = "São Paulo";
        logradouro = "Rua Haddock Lobo";
        numero = "595";
        complemento = "Andar 15";


        con.update("INSERT INTO LocalizacaoEmpresa (CEP," +
                "estado," +
                "logradouro," +
                "numero," +
                "bairro," +
                "complemento," +
                "fkEmpresa) values (?, ?, ?, ?, ?, ?, ?)",  CEP,
                estado,
                logradouro,
                numero,
                bairro,
                complemento,
                1);
    }
}
