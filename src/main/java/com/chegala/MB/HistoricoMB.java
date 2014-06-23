/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.chegala.MB;

import com.chegala.model.Carga;
import com.chegala.persistence.CargaRepositorio;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class HistoricoMB implements Serializable {
    
    private final CargaRepositorio cargaRepositorio = CargaRepositorio.getInstance();
    
    private List<Carga> cargasEntregues;
    
    @PostConstruct
    public void inicializar() {
        cargasEntregues = cargaRepositorio.getCargasEntregues();
    }
    public List<Carga> getCargasEntregues() {
        return cargasEntregues;
    }

    public void setCargasEntregues(List<Carga> cargasEntregues) {
        this.cargasEntregues = cargasEntregues;
    }
}
