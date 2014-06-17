/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chegala.model;

import com.chegala.persistence.MotoristaRepositorio;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Igor
 */
@Entity
public class Motorista implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;
    private String nomeCompleto;
    private String cpf;
    private String registro;
    private boolean disponivel = true;
    
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "motorista")
    private List<Carga> cargas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getPrimeiroNome() {
        String[] split = getNomeCompleto().split(" ");
        if (split.length > 0) {
            return split[0];
        }
        return "";
    }

    public String getUltimoNome() {
        String[] split = getNomeCompleto().split(" ");
        if(split.length > 0){
            return split[split.length - 1];
        }
        return "";
    }
    
    public String getNomeFormal(){
        return getPrimeiroNome() + " " + getUltimoNome();
    }

    public void salvar() {
        MotoristaRepositorio.salvar(this);
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public List<Carga> getCargas() {
        return cargas;
    }

    public void setCargas(List<Carga> cargas) {
        this.cargas = cargas;
    }
}
