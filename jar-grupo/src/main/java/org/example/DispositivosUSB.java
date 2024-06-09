package org.example;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.dispositivos.DispositivoUsb;
import org.example.Jdbc.Conexao;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
//        dispositivosUSB.monitorarPortas();
        dispositivosUSB.bloquearPortas();
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

                        if(dispositivosUsb.get(i).getIdDispositivoUsbExclusivo().equals(idDevice)){
                            System.out.println("Dispositivo já inserido");
                        } else {
                            con.update(queryInsert, idDevice, descricao);
                        }
                    }
//                    System.out.println(looca.getDispositivosUsbGrupo().getDispositivosUsb().size());
                }
            };
            timer.schedule(tarefa, 2000, 5000);
        } catch (RuntimeException e) {
            System.out.println("Id já existente na tabela");
        }
    }

    public void bloquearPortas(){
        try {
            Timer timer = new Timer();
            TimerTask tarefa = new TimerTask() {
                @Override
                public void run() {

                    System.out.println("Executando Comando");
                    try {
                        String powerShellAdm = "powershell.exe -Verb runAs";
                        String desabilitarDespositivo = "Disable-PnpDevice -InstanceId 'HID\\VID_1A2C&PID_0042\6&7946341&0&0000'";
                        idDevice = "'HID\\VID_1A2C&PID_0042\\6&7946341&0&0000'";
                        String comandoCompleto = String.format("powershell.exe -Command Start-Process powershell -Verb runAs -ArgumentList \"-Command Disable-PnpDevice -InstanceId '%s'\"", idDevice);

                        Process processo = Runtime.getRuntime().exec(comandoCompleto);
                        BufferedReader reader = new BufferedReader(new InputStreamReader(processo.getInputStream()));
                        String line;
                        while ((line = reader.readLine()) != null) {
                            System.out.println(line);
                        }
                        int exitCode = processo.waitFor();
                        System.out.println("Encerrando cóidgo");
                        System.out.println("Comando executado com código de saída: " + exitCode);
                        processo.destroy(); // Encerra o processo
                    } catch (IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            };
            timer.schedule(tarefa, 1000, 5000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}