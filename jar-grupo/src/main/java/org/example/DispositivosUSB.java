package org.example;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.dispositivos.DispositivoUsb;
import org.example.Jdbc.Conexao;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class DispositivosUSB {
    static Looca looca = new Looca();
    private static String idDevice;
    private static String descricao;
    private static Conexao conexao = new Conexao();
    private static JdbcTemplate con = conexao.getConexaoBanco();

    public DispositivosUSB(String idDevice, String descricao, Conexao conexao, JdbcTemplate con) {
        this.idDevice = idDevice;
        this.descricao = descricao;
        this.conexao = conexao;
        this.con = con;
    }

    public DispositivosUSB() {
    }

    public static void main(String[] args) {
        DispositivosUSB dispositivosUSB = new DispositivosUSB();
        dispositivosUSB.monitorarPortas();
//        dispositivosUSB.bloquearPortas();
    }

    public void monitorarPortas() {
        String queryInsert = "INSERT INTO dispositivoUsb (idDevice, descricao) values (?, ?)";
//        String queryCheck = "SELECT "
        try {
            Timer timer = new Timer();
            TimerTask tarefa = new TimerTask() {
                @Override
                public void run() {
                    List<DispositivoUsb> dispositivosUsb = looca.getDispositivosUsbGrupo().getDispositivosUsb();

                    System.out.println("");
                    System.out.println("");
//                    System.out.println(dispositivosUsb);

                    for (int i = 0; i < looca.getDispositivosUsbGrupo().getDispositivosUsb().size(); i++) {
                        idDevice = looca.getDispositivosUsbGrupo().getDispositivosUsb().get(i).getIdDispositivoUsbExclusivo();
                        descricao = looca.getDispositivosUsbGrupo().getDispositivosUsb().get(i).getNome();

//                        Integer qtdDispositivos = con.queryForObject(queryCheck, Integer.class);

//                        if(dispositivosUsb.get(i).getIdDispositivoUsbExclusivo().equals(idDevice)){
//                            System.out.println("Dispositivo já inserido");
//                        } else {
                            con.update(queryInsert, idDevice, descricao);
                        }
                    }
//                    System.out.println(looca.getDispositivosUsbGrupo().getDispositivosUsb().size());
//                }
            };
            timer.schedule(tarefa, 2000, 5000);
        } catch (RuntimeException e) {
            System.out.println("Id já existente na tabela");
        }
    }

    public void bloquearPortas() {
        try {
            Timer timer = new Timer();
            TimerTask tarefa = new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Executando Comando");
                    try {
                        idDevice = "HID\\VID_1A2C&PID_0042\\6&7946341&0&0000";

                        // Cria um script PowerShell temporário com a extensão .ps1 correta
                        Path pathTemporario = Files.createTempFile("disable_device", ".ps1");
                        System.out.println("Arquivo temporário criado em: " + pathTemporario.toAbsolutePath());

                        pathTemporario.toFile().deleteOnExit(); // Garante que o arquivo será deletado na saída

                        try (FileWriter writer = new FileWriter(pathTemporario.toFile())) {
                            writer.write("Disable-PnpDevice -InstanceId '" + idDevice + "' -Confirm:$false");
                            System.out.println("Script PowerShell escrito no arquivo temporário.");
                        } catch (Exception e) {
                            System.err.println("Erro ao escrever no arquivo temporário.");
                            e.printStackTrace();
                        }

                        String comandoCompleto = String.format(
                                "powershell.exe -Command \"Start-Process powershell -Verb RunAs -ArgumentList '-File %s'\"",
                                pathTemporario.toAbsolutePath().toString()
                        );

                        System.out.println("Comando PowerShell: " + comandoCompleto);

                        Process processo = Runtime.getRuntime().exec(comandoCompleto);

                        // Captura a saída do processo
                        BufferedReader reader = new BufferedReader(new InputStreamReader(processo.getInputStream()));
                        BufferedReader errorReader = new BufferedReader(new InputStreamReader(processo.getErrorStream()));
                        String line;

                        System.out.println("Saída do comando:");
                        while ((line = reader.readLine()) != null) {
                            System.out.println(line);
                        }

                        System.out.println("Erros do comando:");
                        while ((line = errorReader.readLine()) != null) {
                            System.out.println(line);
                        }

                        // Aguarda o término do processo
                        int exitCode = processo.waitFor();
                        System.out.println("Comando executado com código de saída: " + exitCode);

                        // Encerra o processo
                        processo.destroy();

                    } catch (IOException | InterruptedException e) {
                        System.err.println("Erro durante a execução do comando PowerShell.");
                        e.printStackTrace();
                    }
                }
            };
            timer.schedule(tarefa, 1000, 5000);

        } catch (Exception e) {
            System.err.println("Erro no agendamento da tarefa.");
            e.printStackTrace();
        }
    }
}