package org.example;

import org.springframework.jdbc.core.JdbcTemplate;

public class Main {
    public static void main(String[] args) {
        Empresa empresaDemo = new Empresa("ReData.INC",
                "53719031000163",
                "03325764",
                "São Paulo",
                "Rua Database",
                "777",
                "DataLake",
                "Camada de Load");
        empresaDemo.inserirDadosEmpresa(); // Inserindo dados no MySql

        System.out.println(empresaDemo.consultarId()); // Consultar o Id Atual
        Integer fkEmpresa = empresaDemo.consultarId(); // Definindo o Id para as futuras operações

        System.out.println(empresaDemo);

        Contato contatoDemo = new Contato("James Heat Field",
                "James@gmail.com",
                "11974216702",
                fkEmpresa);
        contatoDemo.inserirDadosContato();

        System.out.println(contatoDemo);

    }
}
