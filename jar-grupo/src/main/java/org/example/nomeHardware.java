package org.example;

public enum nomeHardware {
    CPU ("Cpu"),
    RAM ("Ram"),
    DISCO ("Disco"),
    VOLUME ("Volume)"),
    REDE ("Rede");

    private final String nome;

    nomeHardware(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Nome: " + nome;
    }
}
