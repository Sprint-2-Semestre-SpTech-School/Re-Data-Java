package org.example;

import com.github.britooo.looca.api.core.Looca;
import org.springframework.jdbc.core.JdbcTemplate;

public class infoHardware {
    Looca looca = new Looca();
    private Integer codHardware;
    private String nomeCpu;
    private String nomeRam;
    private String nomeDisco;
    private String nomeRede;
    private String fkMaquina;
    private Conexao conexao = new Conexao();
    private JdbcTemplate con = conexao.getConexaoBanco();

    public infoHardware(Integer codHardware,
                        String nomeCpu,
                        String nomeRam,
                        String nomeDisco,
                        String nomeRede,
                        String fkMaquina) {
        this.codHardware = codHardware;
        this.nomeCpu = nomeCpu;
        this.nomeRam = nomeRam;
        this.nomeDisco = nomeDisco;
        this.nomeRede = nomeRede;
        this.fkMaquina = fkMaquina;
    }
    public infoHardware() {
    }
    public void capturarDadosInfoHardware(){
        nomeCpu = looca.getProcessador().getId();
        nomeRam = looca.getMemoria().ge
    }
}
