package org.example;

import com.github.britooo.looca.api.core.Looca;
import org.springframework.jdbc.core.JdbcTemplate;

public class Cpu {
    Looca looca = new Looca();
   private String idCpu;
   private Long frequencia;
   private Integer numeroCpuFisica;
   private Integer numeroCpuLogica;
   private Integer numeroPacote;
   private Double usoDeCpu;
   private Integer fkCodHardware;
   private Integer fkMaquina;
   private Conexao conexao = new Conexao();
   private JdbcTemplate con = conexao.getConexaoBanco();

    public Cpu(String idCpu,
               Long frequencia,
               Integer numeroCpuFisica,
               Integer numeroCpuLogica, Integer numeroPacote,
               Double usoCpu,
               Integer fkCodHardware,
               Integer fkMaquina) {
        this.idCpu = idCpu;
        this.frequencia = frequencia;
        this.numeroCpuFisica = numeroCpuFisica;
        this.numeroCpuLogica = numeroCpuLogica;
        this.numeroPacote = numeroPacote;
        this.usoDeCpu = usoCpu;
        this.fkCodHardware = fkCodHardware;
        this.fkMaquina = fkMaquina;
    }

    public Cpu(Integer fkCodHardware, Integer fkMaquina) {
        this.fkCodHardware = fkCodHardware;
        this.fkMaquina = fkMaquina;
    }

    public Cpu() {
    }
    public void capturarDadosCpu(){
        idCpu = looca.getProcessador().getId();
        frequencia = looca.getProcessador().getFrequencia();
        numeroCpuFisica = looca.getProcessador().getNumeroCpusFisicas();
        numeroCpuLogica = looca.getProcessador().getNumeroCpusLogicas();
        numeroPacote = looca.getProcessador().getNumeroPacotesFisicos();
        usoDeCpu = looca.getProcessador().getUso();
        con.update("")
    }
}
