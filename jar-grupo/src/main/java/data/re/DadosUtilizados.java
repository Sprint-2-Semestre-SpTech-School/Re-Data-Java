package data.re;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
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
        Rede rede01 = looca.getRede();
        RedeParametros redeParam01 = looca.getRede().getParametros();
        RedeInterfaceGroup redeGroup = looca.getRede().getGrupoDeInterfaces();
        RedeInterface redeInterface = looca.getRede().getGrupoDeInterfaces().getInterfaces().get(2);

        // ========================================DADOS DE CPU====================================//
        System.out.println("DADOS DE CPU");
        System.out.println(cpu01.getId()); // Id da própria tabela
        System.out.println(cpu01.getFabricante()); // O nome já vem junto com outros
        System.out.println(cpu01.getFrequencia()); // Para indicar se a cpu precisa de alocação de recursos
        System.out.println(cpu01.getNome()); // Usar para pegar o nome e especificar, separar talvez?
        System.out.println(cpu01.getNumeroCpusFisicas()); // Mais cpu's físicas + velocidade Reecaminha 5 fv//
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
        System.out.println(disco01.getDiscos()); // Informações do disco
        System.out.println(disco01.getQuantidadeDeDiscos()); // Importante para separar depois
        System.out.println(disco01.getTamanhoTotal());
        // Não achei os dois métodos de volume úteis

        System.out.println("\n");
        // ========================================= DADOS DE REDE =========================================//
        System.out.println("DADOS DE REDE");
        System.out.println(redeInterface.getEnderecoIpv4()); // Saber qual o ip que os computadores estão
        // Conectados
        System.out.println(redeInterface.getBytesRecebidos()); // Se os computadores estão recebendo bem a rede
        System.out.println(redeInterface.getNomeExibicao()); // Nome da rede que eles estão conectados
        System.out.println(redeInterface.getPacotesEnviados()); // Mais dados para
        System.out.println(redeInterface.getPacotesRecebidos());

    }
}
