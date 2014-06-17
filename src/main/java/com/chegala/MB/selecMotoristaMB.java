/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chegala.MB;

import com.chegala.model.Motorista;
import com.chegala.persistence.MotoristaRepositorio;
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
public class selecMotoristaMB implements Serializable {

    private List<Motorista> motoristas;

    public List<Motorista> getMotoristas() {
        if (motoristas == null) {
            motoristas = MotoristaRepositorio.getMotoristasDisponiveis();
        }
        return motoristas;
    }

    public void selectMotorista(Motorista motorista) {
        RequestContext.getCurrentInstance().closeDialog(motorista);
    }
}
