package org.example;

import org.example.Capturas.Cpu;
import org.example.Capturas.Disco;
import org.example.Capturas.Ram;
import org.example.Capturas.Rede;
import org.example.Jdbc.Conexao;
import org.json.JSONObject;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        Login validarLogin = new Login();
        validarLogin.validacaoLogin();
        System.out.println(validarLogin);

        Inovacao testeInova = new Inovacao();
        testeInova.setarSenha();
        testeInova.ejetarUsb();

        try {
            JSONObject json = new JSONObject();
            json.put("text", "Login feito no JAVA " + "Teste para saber se eu posso dividir");
            Slack.sendMessage(json);
        } catch (IOException e) {
            System.out.println("Deu ruim no slack" + e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Maquina idMaquina = new Maquina();

        Maquina maquinaDemo = new Maquina(400, 1);
        maquinaDemo.capturarDadosMaquina();
        maquinaDemo.inserirDadosMaquina();
        Integer maquina = idMaquina.getIdMaquina();


        if (!maquinaDemo.consultarUsuario()){
            System.out.println("É necessário ter uma máquina associada ao projeto. Por favor insira-a no website");
            System.exit(0);
        }

        Integer fkMaquina = maquinaDemo.consultarId();
        System.out.println("fkMaquina: " + fkMaquina);
        System.out.println(maquinaDemo);

        Cpu cpu = new Cpu(maquina);
        cpu.capturarDados();
        cpu.inserirDados();

        Ram ram = new Ram(maquina);
        ram.capturarDados();
        ram.inserirDados();

        Disco disco = new Disco(maquina);
        disco.capturarDados();
        disco.inserirDados();

        Rede rede = new Rede(maquina);
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
    }


}
