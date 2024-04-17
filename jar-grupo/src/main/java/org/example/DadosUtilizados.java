package org.example;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.discos.Volume;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.rede.Rede;
import com.github.britooo.looca.api.group.rede.RedeInterface;
import com.github.britooo.looca.api.group.rede.RedeInterfaceGroup;
import com.github.britooo.looca.api.group.rede.RedeParametros;
import com.github.britooo.looca.api.group.sistema.Sistema;
import oshi.hardware.NetworkIF;

public class DadosUtilizados {
    public static void main(String[] args) {

        Looca looca = new Looca();
        Processador cpu01 = looca.getProcessador();
        Memoria memoria01 = looca.getMemoria();
        DiscoGrupo disco01 = looca.getGrupoDeDiscos();
        Volume volume01 = looca.getGrupoDeDiscos().getVolumes().get(0);
        Rede rede01 = looca.getRede();
        RedeParametros redeParam01 = looca.getRede().getParametros();
        RedeInterfaceGroup redeGroup = looca.getRede().getGrupoDeInterfaces();
        RedeInterface redeInterface = looca.getRede().getGrupoDeInterfaces().getInterfaces().get(2);

        // ========================================DADOS DE CPU====================================//
        System.out.println("DADOS DE CPU");
        System.out.println(cpu01.getId()); // Id da própria tabela
        // System.out.println(cpu01.getFabricante()); O nome já vem junto com outros
        System.out.println(cpu01.getFrequencia()); // Para indicar se a cpu precisa de alocação de recursos
        System.out.println(cpu01.getNome()); // Usar para pegar o nome e especificar, separar talvez?
        System.out.println(cpu01.getNumeroCpusFisicas()); // Mais cpu's físicas + velocidade Reecaminha 5 //
        System.out.println(cpu01.getNumeroCpusLogicas());
        System.out.println(cpu01.getNumeroPacotesFisicos());
        System.out.println(cpu01.getUso()); // Uso de cpu, precisa arrumar
        System.out.println("\n");

        // ======================================== Dados DE MEMÓRIA =====================================//
        System.out.println("DADOS DE MEMÓRIA");
        System.out.println(memoria01.getDisponivel());
        System.out.println(memoria01.getEmUso());
        System.out.println(memoria01.getTotal());
        System.out.println(memoria01.getClass());
        // O que iremos fazer com isso? Colocar eles em % em relação um ao outro se baseando na aula de KPI.
        // Ex: (Dado meio vaidade) Sua empresa ainda tem x de pool de ram para aplicar em geral.
        // Ex: X% da sua ram total está em uso.

        System.out.println("\n");
        // ========================================= DADOS DE DISCO ========================================//
        System.out.println("DADOS DE DISCO");
        System.out.println("Get disco" + disco01.getDiscos()); // Informações do disco
        System.out.println("Qtd de discos" + disco01.getQuantidadeDeDiscos()); // Importante para separar depois
        System.out.println("Tamanho total" + disco01.getTamanhoTotal() / 1000000000);
        System.out.println("\n");

        System.out.println("Informações do volume do disco");
        System.out.println(volume01.getPontoDeMontagem());
        System.out.println(volume01.getTipo());
        System.out.println(volume01.getDisponivel());
        System.out.println(volume01.getTotal());
        System.out.println("Quantidade de volume" + disco01.getQuantidadeDeVolumes());
        // Não achei os dois métodos de volume úteis

        System.out.println("\n");
        // ========================================= DADOS DE REDE =========================================//
        System.out.println("DADOS DE REDE ");
        System.out.println("Endereço ipv4 " + redeInterface.getEnderecoIpv4()); // Saber qual o ip que os computadores estão
        // Conectados
        System.out.println("Endereço ipv6 " + redeInterface.getEnderecoIpv6()); // Duvida, precisa do ipv4 mesmo?
        System.out.println("Endereço MAC " + redeInterface.getEnderecoMac()); // Endereço de ID da rede
        System.out.println("Bytes recebidos " + redeInterface.getBytesRecebidos()); // Se os computadores estão recebendo bem a rede
        System.out.println("Nome exibicação " + redeInterface.getNomeExibicao()); // Nome da rede que eles estão conectados
        System.out.println("Pacotes enviados " + redeInterface.getPacotesEnviados()); // Mais dados de pacote
        System.out.println("Pacotes recebidos " + redeInterface.getPacotesRecebidos()); // Mais dados de pacote
        System.out.println("Rede DNS " + redeParam01.getServidoresDns());
        System.out.println("Nome do host " + redeParam01.getHostName());
        // System.out.println(redeInterface);
        // ======================================= Coisas que não sei =====================================//
        System.out.println("\n");
        System.out.println("Dados que eu não sei");
        System.out.println(looca.getDispositivosUsbGrupo().getDispositivosUsbConectados()); // Só vê unidade de disco
        System.out.println("Tempo de atividade " + looca.getSistema().getTempoDeAtividade()); // Temp atv
        System.out.println("Temperatura maquina" + looca.getTemperatura());
        System.out.println("Inicializado " + looca.getSistema().getInicializado());
    }
}
