package org.example;

import org.springframework.jdbc.core.JdbcTemplate;

public class Contato {
    private Integer idContato;
    private String nome;
    private String email;
    private String telefone;
    private Integer fkEmpresa;
    private Conexao conexao = new Conexao();
    private JdbcTemplate con = conexao.getConexaoBanco();

    public Contato(String nome, String email, String telefone, Integer fkEmpresa) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.fkEmpresa = fkEmpresa;
    }

    public Contato() {
    }

    public void inserirDadosContato(){
        con.update("INSERT INTO Contato (nome, email, telefone, fkEmpresa) values (?, ?, ?, ?)",
                nome, email, telefone, fkEmpresa);
    }
    public Integer getIdContato() {
        return idContato;
    }

    public Conexao getConexao() {
        return conexao;
    }

    public JdbcTemplate getCon() {
        return con;
    }

    public Integer getFkEmpresa() {
        return fkEmpresa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setFkEmpresa(Integer fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }

    @Override
    public String toString() {
        return """
                idContato: %d
                Nome: %s
                Telefone: %s
                fkEmpresa: %d""".formatted(idContato, nome, telefone, fkEmpresa);
    }
}


