package com.chegala.MB;

import com.chegala.model.Caminhao;
import com.chegala.persistence.CaminhaoRepositorio;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.*;

@ManagedBean
@ViewScoped
public class CaminhaoMB implements Serializable {

    private final CaminhaoRepositorio caminhaoRepositorio = CaminhaoRepositorio.getInstance();
    
    private Caminhao caminhao = new Caminhao();
    private List<Caminhao> caminhoes = caminhaoRepositorio.getLista();
    
    public CaminhaoMB() {
    }
    
    public void novoCaminhao(){
        caminhao = new Caminhao();
    }
    
    public void listarCaminhoes(){
        caminhoes = caminhaoRepositorio.getLista();
    }
    
    public void cadastrarCaminhao(){
        caminhao.salvar();
        listarCaminhoes();
        novoCaminhao();
    }
    
    public void excluirCaminhao(Caminhao caminhao){
        caminhao.excluir();
        
        listarCaminhoes();
    }
    
    public Caminhao getCaminhao() {
        return caminhao;
    }

    public void setCaminhao(Caminhao caminhao) {
        this.caminhao = caminhao;
    }

    public List<Caminhao> getCaminhoes() {
        return caminhoes;
    }

    public void setCaminhoes(List<Caminhao> caminhoes) {
        this.caminhoes = caminhoes;
    }
}
