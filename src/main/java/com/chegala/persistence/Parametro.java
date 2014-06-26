/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.chegala.persistence;

/**
 *
 * @author Igor
 */
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
