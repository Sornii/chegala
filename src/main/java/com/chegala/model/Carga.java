package com.chegala.model;

import com.chegala.persistence.CargaRepositorio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Carga implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;
    
    private String destino;
    @ManyToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Caminhao caminhao;
    
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "carga")
    private List<Item> itens;
    
    @ManyToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Motorista motorista;
    
    private boolean entregue;
    
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date dataCriacao;
    
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date dataSaida;
    
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date dataChegada;

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Date getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(Date dataChegada) {
        this.dataChegada = dataChegada;
    }
    
    public Double getPesoTotal(){
        Double pesoTotal = 0.0D;
        
        for(Item item : getItens()){
            pesoTotal += item.getPeso();
        }
        
        return pesoTotal;
    }
    
    public Double getVolumeTotal(){
        Double volumeTotal = 0.0D;
        
        for(Item item : getItens()){
            volumeTotal += item.getVolume();
        }
        
        return volumeTotal;
    }
    
    public boolean cabeItem(Item item){
        return getCaminhao().getPesoMax() > item.getPeso() + getPesoTotal()
                || getCaminhao().getVolumeMax() > item.getVolume() + getVolumeTotal();
    }
    
    public boolean isCheio(){
        return getVolumeTotal() > getCaminhao().getVolumeMax()
                || getPesoTotal() > getCaminhao().getPesoMax();
    }
    
    public boolean addItem(Item item){
        if(cabeItem(item)){
            getItens().add(item);
            item.setCarga(this);
            return true;
        }
        return false;
    }
    
    public void excluirItem(Item item){
        itens.remove(item);
    }
    
    public void salvar(){
        CargaRepositorio.salvar(this);
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Caminhao getCaminhao() {
        return caminhao;
    }

    public void setCaminhao(Caminhao caminhao) {
        this.caminhao = caminhao;
    }

    public List<Item> getItens() {
        if(itens == null){
            itens = new ArrayList<Item>();
        }
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public boolean isEntregue() {
        return entregue;
    }

    public void setEntregue(boolean entregue) {
        this.entregue = entregue;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }
}
