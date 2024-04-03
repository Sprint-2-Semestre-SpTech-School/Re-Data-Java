package codigoLogin;

import java.util.Scanner;

public class Metodos {

    private String nomeUsuario;
    private String senha;

    private Scanner inputLine = new Scanner(System.in);

    public Metodos(String nomeUsuario, String senha) {
        this.nomeUsuario = "fernanda.caramico";
        this.senha = "SPtechPI";
    }

    public Metodos() {
    }

    public void validacaoLogin() {

        System.out.println("Quer acessar sua página de usuário e acompanhar o monitoramento?");

        System.out.println("Insira aqui seu nome de usuário:");
        String usuarioInserido = inputLine.nextLine();

        while (!(usuarioInserido.equals(getNomeUsuario()))) {
            System.out.println("\nNome de usuário incorreto. Tente novamente ");

            System.out.println("Insira aqui seu nome de usuário:");
            usuarioInserido = inputLine.nextLine();
        }

        System.out.println("Insira aqui sua senha:");
        String senhaInserida = inputLine.nextLine();

        while (!(senhaInserida.equals(getSenha()))) {
            System.out.println("\nSenha incorreta. Tente novamente");

            System.out.println("Insira aqui sua senha:");
            senhaInserida = inputLine.nextLine();
        }
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Scanner getInputLine() {
        return inputLine;
    }

    public void setInputLine(Scanner inputLine) {
        this.inputLine = inputLine;
    }

    @Override
    public String toString() {
        return """
                Obrigada, %s, por fazer login no nosso sistema.
                Você será direcionado à sua página de login.""".formatted(getNomeUsuario());
    }
}
