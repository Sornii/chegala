/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.chegala.model;

import com.chegala.persistence.CaminhaoRepositorio;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
public class Caminhao implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;
    private String placa;
    private Double pesoMax;
    private Double volumeMax;
    private boolean disponivel = true;
    
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "caminhao")
    List<Carga> cargas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Double getPesoMax() {
        return pesoMax;
    }

    public void setPesoMax(Double pesoMax) {
        this.pesoMax = pesoMax;
    }

    public Double getVolumeMax() {
        return volumeMax;
    }

    public void setVolumeMax(Double volumeMax) {
        this.volumeMax = volumeMax;
    }
    
    public void salvar(){
        CaminhaoRepositorio.salvar(this);
    }
    
    public void excluir(){
        CaminhaoRepositorio.excluir(this);
    }
    
    public boolean isDisponivel() {
        return CaminhaoRepositorio.isCaminhaoDisponivel(this.id);
    }

    public List<Carga> getCargas() {
        return cargas;
    }

    public void setCargas(List<Carga> cargas) {
        this.cargas = cargas;
    }
    
    public boolean getDisponivel(){
        return disponivel;
    }
    
    public void setDisponivel(boolean disponivel){
        this.disponivel = disponivel;
    }
}
