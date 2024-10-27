package com.aluracursos.musicapp.modelo;

public enum Genero {
    POP("pop"),
    ROCK("rock"),
    SALSA("salsa"),
    MERENGUE("merengue"),
    REGUETON("regueton"),
    ELECTRONICA("electrónica");

    private String generoEntrada;

    Genero(String generoUsuario) {
        this.generoEntrada = generoUsuario;
    }

    public static Genero generoTeclado(String text) {
        for (Genero genero : Genero.values()) {
            if (genero.generoEntrada.equalsIgnoreCase(text)) {
                return genero;
            }
        }
        throw new IllegalArgumentException("Ningún genero encontrado: " + text);
    }
}