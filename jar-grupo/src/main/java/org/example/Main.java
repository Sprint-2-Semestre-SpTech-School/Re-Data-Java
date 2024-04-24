package org.example;

public class Main {
    public static void main(String[] args) {
        Empresa empresa01 = new Empresa();
        empresa01.adicionarEmpresa();
        empresa01.adicionarLocalizacaoEmpresa();
        System.out.println(empresa01.obterIdsEmpresa());
    }
}
