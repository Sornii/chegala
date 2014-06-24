package com.chegala.MB;

import com.chegala.model.Motorista;
import com.chegala.persistence.MotoristaRepositorio;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class MotoristaMB implements Serializable {
    
    private final MotoristaRepositorio motoristaRepositorio = MotoristaRepositorio.getInstance();

    private Motorista motorista;
    public List<Motorista> motoristas;

    public void cadastrarMotorista() {
        motorista.salvar();
        novoMotorista();
        atualizarMotorista();
    }

    public void novoMotorista() {
        motorista = new Motorista();
    }

    public void atualizarMotorista() {
        motoristas = motoristaRepositorio.getMotoristas();
    }

    public List<Motorista> getMotoristas() {
        if (motoristas == null) {
            motoristas = motoristaRepositorio.getMotoristas();
        }
        return motoristas;
    }

    public Motorista getMotorista() {
        if (motorista == null) {
            novoMotorista();
        }
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }
}
