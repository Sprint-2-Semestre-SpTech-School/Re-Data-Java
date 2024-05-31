package org.example;

import org.example.logging.GeradorLog;
import org.example.logging.Modulo;
import org.example.logging.TagNiveisLog;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

public class Empresa {
    private List<Integer> idsEmpresa;
    private String nomeEmpresa;
    private String CNPJ;
    private String CEP;
    private String estado;
    private String logradouro;
    private String numero;
    private String bairro;
    private String complemento;
    private Conexao conexao = new Conexao();
    private ConexaoServer conexao02 = new ConexaoServer();
    private JdbcTemplate con = conexao.getConexaoBanco();
    // private JdbcTemplate con02 = conexao02.getConexaoBanco();

    public Empresa(String nomeEmpresa,
                   String CNPJ,
                   String CEP,
                   String estado,
                   String logradouro,
                   String numero,
                   String bairro,
                   String complemento) {
        this.nomeEmpresa = nomeEmpresa;
        this.CNPJ = CNPJ;
        this.CEP = CEP;
        this.estado = estado;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.complemento = complemento;
    }

    public Empresa() {
    }

    private void adicionarEmpresa(){
        try{
        con.update("INSERT INTO Empresa (nomeEmpresa, CNPJ) values (?, ?)", nomeEmpresa, CNPJ);
<<<<<<< HEAD

            GeradorLog.log(TagNiveisLog.INFO, "Dados inseridos com sucesso! Re;Data Local/MySQL DB:", Modulo.ENVIO_DADOS);
            GeradorLog.log(TagNiveisLog.INFO, "SQL column new value - Nome: %s for ID.Empresa.redata: %d".formatted(nomeEmpresa, consultarId()), Modulo.ENVIO_DADOS);
            GeradorLog.log(TagNiveisLog.INFO, "SQL column new value - CNPJ: %s for ID.Empresa.redata: %s".formatted(CNPJ, consultarId()), Modulo.ENVIO_DADOS);

        con02.update("INSERT INTO Empresa (nomeEmpresa, CNPJ) values (?, ?)", nomeEmpresa, CNPJ);
=======
        // con02.update("INSERT INTO Empresa (nomeEmpresa, CNPJ) values (?, ?)", nomeEmpresa, CNPJ);
>>>>>>> a76cc749a0eda1e3b0f61342362acabb8e03e1fc
        consultarId();

            GeradorLog.log(TagNiveisLog.INFO, "Dados inseridos com sucesso! Re;Data SQL Server DB:", Modulo.ENVIO_DADOS);
            GeradorLog.log(TagNiveisLog.INFO, "SQL column new value - Nome: %s for ID.Empresa.redata: %d".formatted(nomeEmpresa, consultarId()), Modulo.ENVIO_DADOS);
            GeradorLog.log(TagNiveisLog.INFO, "SQL column new value - CNPJ: %s for ID.Empresa.redata: %s".formatted(CNPJ, consultarId()), Modulo.ENVIO_DADOS);

        }catch (RuntimeException e){
            System.out.println("Erro de conexão 'Empresa' sql " + e.getMessage());
            GeradorLog.log(TagNiveisLog.ERROR, "Erro de conexão SQL.", Modulo.ALERTA);
        }

    }

    public Integer consultarId(){
            String comandoSql = ("SELECT idEmpresa from Empresa");
            idsEmpresa = con.queryForList(comandoSql, Integer.class);
            return idsEmpresa.get(idsEmpresa.size() - 1);
    }

    private void adicionarLocalizacaoEmpresa() {
        try {
            con.update("INSERT INTO localizacaoEmpresa (CEP," +
                            "estado," +
                            "logradouro," +
                            "numero," +
                            "bairro," +
                            "complemento," +
                            "fkEmpresa) values (?, ?, ?, ?, ?, ?, ?)", CEP,
                    estado,
                    logradouro,
                    numero,
                    bairro,
                    complemento,
                    idsEmpresa.get(idsEmpresa.size() - 1));

//            con02.update("INSERT INTO localizacaoEmpresa (CEP," +
//                            "estado," +
//                            "logradouro," +
//                            "numero," +
//                            "bairro," +
//                            "complemento," +
//                            "fkEmpresa) values (?, ?, ?, ?, ?, ?, ?)", CEP,
//                    estado,
//                    logradouro,
//                    numero,
//                    bairro,
//                    complemento,
//                    idsEmpresa.get(idsEmpresa.size() - 1));

    }catch (RuntimeException e){
        System.out.println("Erro de conexão 'Empresa' sql " + e.getMessage());
        GeradorLog.log(TagNiveisLog.ERROR, "Erro de conexão SQL: localizacao.redata", Modulo.ALERTA);
        }
    }
    public void inserirDadosEmpresa(){
        adicionarEmpresa();
        adicionarLocalizacaoEmpresa();
    }

    public List<Integer> getIdsEmpresa() {
        return idsEmpresa;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Conexao getConexao() {
        return conexao;
    }

    public JdbcTemplate getCon() {
        return con;
    }

    @Override
    public String toString() {
        int idAtual = idsEmpresa.get(idsEmpresa.size() - 1);
        return """
                IdEmpresa: %d
                Nome da empresa: %s
                CNPJ: %s
                CEP: %s
                Estado: %s
                Logradouro: %s
                Numero: %s
                Bairro: %s
                Complemento: %s
                fkEmpresa: %d""".formatted(
                        idAtual, nomeEmpresa, CNPJ, CEP,estado, logradouro, numero, bairro, complemento, idAtual
        );
    }
}