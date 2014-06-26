package com.chegala.persistence;

public class Parametro {
    
    private final String nome;
    private final Object valor;

    public Parametro(String nome, Object valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public Object getValor() {
        return valor;
    }
    
    public String getNome() {
        return nome;
    }
}
