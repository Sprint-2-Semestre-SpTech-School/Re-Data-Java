package org.example;

import com.github.britooo.looca.api.core.Looca;
import org.example.logging.GeradorLog;
import org.example.logging.Modulo;
import org.example.logging.Tabelas;
import org.example.logging.TagNiveisLog;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Looca looca = new Looca();

//        Inovacao testeInova = new Inovacao();
        Locale defaultLocale = Locale.getDefault();
        GeradorLog.log(TagNiveisLog.INFO, "Language: " + defaultLocale.getDisplayLanguage(), Modulo.GERAL);
        GeradorLog.log(TagNiveisLog.INFO, "OS name: " + looca.getSistema().getSistemaOperacional(), Modulo.GERAL);
        GeradorLog.log(TagNiveisLog.INFO, "Arch: " + looca.getSistema().getArquitetura() + "x bits", Modulo.GERAL);
        GeradorLog.log(TagNiveisLog.INFO, "Iniciando a aplicação...", Modulo.GERAL);

        Empresa empresaDemo = new Empresa("ReData.INC",
                "53719031000123",
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
                "1197421670", fkEmpresa);
        contatoDemo.inserirDadosContato();

        Login validarLogin = new Login();
        validarLogin.validacaoLogin();
        System.out.println(validarLogin);

        try {
            JSONObject json = new JSONObject();
            json.put("text", "Login feito no JAVA" + "Teste para saber se eu posso dividir");
            Slack.sendMessage(json);
        } catch (IOException e) {
            System.out.println("Deu ruim no slack" + e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


//        System.out.println(contatoDemo);
//        System.out.println("\n");

        Projeto projetoDemo = new Projeto("Venda de Coca-Cola",
                "Aumentar vendas de Coca-Cola na Zona Norte",
                "Julia", 1);
        projetoDemo.inserirDadosProjeto();
        Integer fkProjeto = projetoDemo.consultarId();
        System.out.println(projetoDemo);

        Maquina maquinaDemo = new Maquina(fkProjeto, 1);
        maquinaDemo.capturarDadosMaquina();
        maquinaDemo.inserirDadosMaquina();
        Integer fkMaquina = maquinaDemo.consultarId();
        System.out.println("fkMaquina: " + fkMaquina);
        System.out.println(maquinaDemo);

        Cpu cpu = new Cpu();
        cpu.capturarDados();
        cpu.inserirDados();

        Ram ram = new Ram();
        ram.capturarDados();
        ram.inserirDados();

        Disco disco = new Disco();
        disco.capturarDados();
        disco.inserirDados();

       Rede rede = new Rede();
       rede.capturarDados();
       rede.inserirDados();


        // METODO PARA INSTANCIA, CRIAR E PLOTAR O ALERTA NO SLACK:
//        if(temperatura > metrica) {
        try {
            JSONObject json = new JSONObject();
            json.put("text", "Aqui colocaremos os alertas!!");
            Slack.sendMessage(json);
        } catch (IOException e) {
            System.out.println("Deu ruim no slack" + e);
        }
//    } É SOBRE ISSO
    }


//        testeInova.setarSenha();
//        while (true) {
//            testeInova.ejetarUsb();
//        }
}
