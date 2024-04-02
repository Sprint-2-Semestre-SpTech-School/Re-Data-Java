package codigoLogin;

import java.util.Scanner;

public class Login {
    public static void main(String[] args) {

        Metodos validarLogin = new Metodos("fernanda.caramico","SPtechPI");

        validarLogin.validacaoLogin();

        System.out.println(validarLogin);

    }
}
