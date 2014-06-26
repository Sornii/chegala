package com.chegala.MB;

import com.chegala.model.Caminhao;
import com.chegala.persistence.CaminhaoRepositorio;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

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
