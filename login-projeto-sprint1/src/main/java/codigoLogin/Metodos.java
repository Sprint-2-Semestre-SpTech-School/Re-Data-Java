package codigoLogin;

import java.util.Scanner;

public class Metodos {

    String nomeUsuario;
    String senha;

    Scanner inputLine = new Scanner(System.in);

    void validacaoLogin() {

        System.out.println("Quer acessar sua página de usuário e acompanhar o monitoramento?");

        System.out.println("Insira aqui seu nome de usuário:");
        nomeUsuario = inputLine.nextLine();

        while (!(nomeUsuario.equals("fernanda.caramico"))) {
            System.out.println("\nNome de usuário incorreto. Tente novamente ");

            System.out.println("Insira aqui seu nome de usuário:");
            nomeUsuario = inputLine.nextLine();
        }

        System.out.println("Insira aqui sua senha:");
        senha = inputLine.nextLine();

        while (!(senha.equals("SPtechPI"))) {
            System.out.println("\nSenha incorreta. Tente novamente");

            System.out.println("Insira aqui sua senha:");
            senha = inputLine.nextLine();
        }
        System.out.println("\nVocê será direcionado à sua página de usuário. Obrigada!");
    }
}
