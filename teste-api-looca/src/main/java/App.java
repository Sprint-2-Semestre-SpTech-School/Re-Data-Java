import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.processos.Processo;
import com.github.britooo.looca.api.group.processos.ProcessoGrupo;
import com.github.britooo.looca.api.group.servicos.ServicoGrupo;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.group.temperatura.Temperatura;

import java.util.List;


public class App {
    public static void main(String[] args) {
        Looca looca = new Looca();
        Sistema sistema = looca.getSistema();
        Memoria memoria = looca.getMemoria();
        Processador processador = looca.getProcessador();
        Temperatura temperatura = looca.getTemperatura();
        DiscoGrupo grupoDeDiscos = looca.getGrupoDeDiscos();
        ServicoGrupo grupoDeServicos = looca.getGrupoDeServicos();
        ProcessoGrupo grupoDeProcessos = looca.getGrupoDeProcessos();

        System.out.println(looca.getProcessador());


//        List<Disco> discos = grupoDeDiscos.getDiscos();
//        List<Processo> processos = grupoDeProcessos.getProcessos();
//
//
//        //Exibindo individualmente cada disco e cada processo. Com for tradicional e for enhanced.
//        for(int i = 0; i < processos.size() - 1; i++){
//            System.out.println(processos.get(i));
//            System.out.println();
//        }
//
//        for(Disco disco : discos){
//            System.out.println(disco);
//        }
//
//        //Infos gerais do sistema
//
//        System.out.println("Quantidade de processos: "+ processos.size());
//        System.out.println("Temperatura do sistema: "+ temperatura.getTemperatura());
//        System.out.println();
//        System.out.println("Uso do processador: "+ processador.getUso());
//        System.out.println("Frequência do processador: "+ processador.getFrequencia());
//        System.out.println("Nome do processador: "+ processador.getNome());
//        System.out.println();
//        System.out.println("Memória disponível: "+ memoria.getDisponivel());
//        System.out.println("Memória em uso: "+ memoria.getEmUso());
//        System.out.println("Memória total: "+ memoria.getTotal());

    }
}
