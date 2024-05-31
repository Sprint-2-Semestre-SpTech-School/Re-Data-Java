package org.example;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.dispositivos.DispositivosUsbGrupo;
import org.example.logging.GeradorLog;
import org.springframework.jdbc.core.JdbcTemplate;

public class Main {
    public static void main(String[] args) {
        Looca looca = new Looca();
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
        System.out.println("\n");

        Contato contatoDemo = new Contato("James Heat Field",
                "James@gmail.com",
                "11974216702",
                fkEmpresa);
        contatoDemo.inserirDadosContato();

        Conta contaDemo = new Conta("ReData2024", "ReData100", "ADM", fkEmpresa);
        contaDemo.inserirDadosConta();
        System.out.println(contaDemo);

        System.out.println(contatoDemo);
        System.out.println("\n");

        Projeto projetoDemo = new Projeto("Venda de Coca-Cola",
                "Aumentar vendas de Coca-Cola na Zona Norte",
                "Julia", fkEmpresa);
        projetoDemo.inserirDadosProjeto();
        Integer fkProjeto = projetoDemo.consultarId();
        System.out.println(projetoDemo);

        Maquina maquinaDemo = new Maquina(fkProjeto, fkEmpresa);
        maquinaDemo.capturarDadosMaquina();
        maquinaDemo.inserirDadosMaquina();
        Integer fkMaquina = maquinaDemo.consultarId();
        System.out.println("fkMaquina: " + fkMaquina);
        System.out.println(maquinaDemo);

        //Cpu cpu = new Cpu();
        //cpu.capturarDados();
        //cpu.inserirDados();

        //Ram ram = new Ram();
        //ram.capturarDados();
        //ram.inserirDados()O;

       Disco disco = new Disco();
       disco.capturarDados();
       disco.inserirDados();

      //Rede rede = new Rede();
      //rede.capturarDados();
      //rede.inserirDados();
    }
}
