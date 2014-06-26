package com.chegala.MB;

import com.chegala.model.Motorista;
import com.chegala.persistence.MotoristaRepositorio;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class selecMotoristaMB implements Serializable {
    
    private final MotoristaRepositorio motoristaRepositorio = MotoristaRepositorio.getInstance();

    private List<Motorista> motoristas;

    @PostConstruct
    public void inicializar() {
        motoristas = motoristaRepositorio.getMotoristasDisponiveis();
    }
    
    public List<Motorista> getMotoristas() {
        return motoristas;
    }

    public void selectMotorista(Motorista motorista) {
        RequestContext.getCurrentInstance().closeDialog(motorista);
    }
}
