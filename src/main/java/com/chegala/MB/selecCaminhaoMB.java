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

    private List<Caminhao> caminhoes;

    public List<Caminhao> getCaminhoes() {
        if (caminhoes == null) {
            caminhoes = CaminhaoRepositorio.getCaminhoesDisponiveis();
        }
        return caminhoes;
    }

    public void selecCaminhao(Caminhao caminhao) {
        RequestContext.getCurrentInstance().closeDialog(caminhao);
    }
}
