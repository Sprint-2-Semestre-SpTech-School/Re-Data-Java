package org.example;

import org.example.Jdbc.Conexao;
import org.example.Negocio.Projeto;
import org.json.JSONObject;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        Conexao conexao = new Conexao();

//        Locale defaultLocale = Locale.getDefault();
//        GeradorLog.log(TagNiveisLog.INFO, "Language: " + defaultLocale.getDisplayLanguage(), Modulo.GERAL);
//        GeradorLog.log(TagNiveisLog.INFO, "OS name: " + looca.getSistema().getSistemaOperacional(), Modulo.GERAL);
//        GeradorLog.log(TagNiveisLog.INFO, "Arch: " + looca.getSistema().getArquitetura() + "x bits", Modulo.GERAL);
//        GeradorLog.log(TagNiveisLog.INFO, "Iniciando a aplicação...", Modulo.GERAL);

        Login validarLogin = new Login();
        validarLogin.validacaoLogin();
        System.out.println(validarLogin);

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


        if (maquinaDemo.consultarUsuario() == false){
            System.out.println("Necessário criar uma maquina na Dashboard");
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

        Inovacao testeInova = new Inovacao();
        testeInova.setarSenha();
        testeInova.ejetarUsb();


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
