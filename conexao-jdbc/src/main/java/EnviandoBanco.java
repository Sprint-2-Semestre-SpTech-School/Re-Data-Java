import org.springframework.jdbc.core.JdbcTemplate;

public class EnviandoBanco {
    public static void main(String[] args) {
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoBanco();
        Cpu cpu01 = new Cpu();
        String fabricante = cpu01.obterFabricante();
        Integer CNPJ = 00000000000000;

        con.update("INSERT INTO Empresa (nomeEmpresa, CNPJ) values (?, ?)", fabricante, CNPJ);

    }
}
