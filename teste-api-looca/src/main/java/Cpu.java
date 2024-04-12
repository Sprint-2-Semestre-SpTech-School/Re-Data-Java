import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processador.Processador;

public class Cpu {
    Looca looca = new Looca();
    Processador processador = looca.getProcessador();
    private String fabricante;

    public Cpu() {
        obterFabricante();
    }

    public String obterFabricante(){
        Processador processador = looca.getProcessador();
        processador.getFabricante();
        fabricante = processador.getFabricante();
        return fabricante;
    }

    public static void main(String[] args) {
        Cpu cpu01 = new Cpu();
        String fabricante = cpu01.obterFabricante();
        System.out.println(fabricante);
    }
}
