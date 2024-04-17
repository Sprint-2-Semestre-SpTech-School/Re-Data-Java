package org.example;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processador.Processador;
import org.springframework.jdbc.core.JdbcTemplate;

public class Cpu {
    Looca looca = new Looca();
    Processador processador = looca.getProcessador();
    private String fabricante;
    public String obterFabricante(){
        Processador processador = looca.getProcessador();
        processador.getFabricante();
        fabricante = processador.getFabricante();
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoBanco();
        String CNPJ = "00000000";
        System.out.println(fabricante);
        con.update("INSERT INTO Empresa (nomeEmpresa, CNPJ) values (?, ?)", fabricante, CNPJ);
        return fabricante;
    }

}
