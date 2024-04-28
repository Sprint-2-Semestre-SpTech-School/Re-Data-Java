package org.example;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processador.Processador;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class infoHardware {
    Looca looca = new Looca();
    private Integer codHardware;
    private String nomeCpu;
    private Long memoriaTotalRam;
    private String nomeDisco;
    private String nomeRede;
    private Integer fkMaquina;
    private Conexao conexao = new Conexao();
    private JdbcTemplate con = conexao.getConexaoBanco();

    public infoHardware(Integer codHardware,
                        String nomeCpu,
                        Long memoriaTotalRam,
                        String nomeDisco,
                        String nomeRede,
                        Integer fkMaquina) {
        this.codHardware = codHardware;
        this.nomeCpu = nomeCpu;
        this.memoriaTotalRam = memoriaTotalRam;
        this.nomeDisco = nomeDisco;
        this.nomeRede = nomeRede;
        this.fkMaquina = fkMaquina;
    }

    public infoHardware(Integer fkMaquina) {
        this.fkMaquina = fkMaquina;
    }

    public infoHardware() {
    }
    public void capturarDadosInfoHardware(){
        nomeCpu = looca.getProcessador().getNome();
        memoriaTotalRam = looca.getMemoria().getTotal() / (1024 * 1024);
        nomeDisco = looca.getGrupoDeDiscos().getDiscos().get(0).getNome();
        nomeRede = looca.getRede().getGrupoDeInterfaces().getInterfaces().get(2).getNomeExibicao();
        con.update("INSERT INTO infoHardware (nomeCpu, memoriaTotalRam, nomeDisco, nomeRede, fkMaquina)" +
                "values (?, ?, ?, ?, ?)", nomeCpu, memoriaTotalRam, nomeDisco, nomeRede, fkMaquina);
    }
    public Integer consultarId(){
        List<Integer> codsHardware;

        String comandoSql = ("SELECT codHardware from infoHardware");
        codsHardware = con.queryForList(comandoSql, Integer.class);
        return codsHardware.get(codsHardware.size() - 1);
    }
}
