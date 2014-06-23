/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chegala.MB;

import com.chegala.model.Caminhao;
import com.chegala.persistence.CaminhaoRepositorio;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Igor
 */
@ManagedBean
@ViewScoped
public class selecCaminhaoMB implements Serializable {

    private final CaminhaoRepositorio caminhaoRepositorio = CaminhaoRepositorio.getInstance();
    
    private List<Caminhao> caminhoes;
    
    @PostConstruct
    public void inicializar(){
        caminhoes = caminhaoRepositorio.getCaminhoesDisponiveis();
    }

    public List<Caminhao> getCaminhoes() {
        return caminhoes;
    }

    public void selecCaminhao(Caminhao caminhao) {
        RequestContext.getCurrentInstance().closeDialog(caminhao);
    }
}
