package org.example.logging;

public class AppLog {
    public static void main(String[] args) {
        GeradorLog.log("Iniciando a aplicação...", TagNiveisLog.INFO);
        GeradorLog.log("Teste", TagNiveisLog.WARN);
    }
}