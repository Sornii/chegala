package com.chegala.chart;

import com.chegala.outros.MessageUtil;
import com.chegala.persistence.CaminhaoRepositorio;
import com.chegala.persistence.CargaRepositorio;
import com.chegala.persistence.MotoristaRepositorio;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.ChartSeries;

@ManagedBean
public class ChartIndex {

    private final CaminhaoRepositorio caminhaoRepositorio = CaminhaoRepositorio.getInstance();
    private final MotoristaRepositorio motoristaRepositorio = MotoristaRepositorio.getInstance();
    private final CargaRepositorio cargaRepositorio = CargaRepositorio.getInstance();

    Long caminhoesDisponiveis = caminhaoRepositorio.contarCaminhoesDisponiveis();
    Long motoristasDisponiveis = motoristaRepositorio.contarMotoristasDisponiveis();

    private BarChartModel model;

    @PostConstruct
    public void inicializar() {
        MontarModel();
        MostrarAvisos();
    }

    private void MostrarAvisos() {
        if (caminhoesDisponiveis == 0) {
            MessageUtil.adicionarMensagem(FacesMessage.SEVERITY_WARN, "Você está sem nenhum caminhão disponível.", "caminhoes");
        }
        if (motoristasDisponiveis == 0) {
            MessageUtil.adicionarMensagem(FacesMessage.SEVERITY_WARN, "Nenhum motorista disponível no momento.", "motoristas");
        }

        Long cargasEntregar = cargaRepositorio.contarCargasEntregar();
        if (cargasEntregar > 0 && motoristasDisponiveis < 1) {
            MessageUtil.adicionarMensagem(FacesMessage.SEVERITY_WARN, "Você tem uma carga para entregar e não tem motoristas para dirigir.", "cargas");
        } else if (cargasEntregar > 0) {
            MessageUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO, "Você tem uma carga para entregar!", "cargas");
        }
    }

    private void MontarModel() {
        model = new BarChartModel();

        ChartSeries disponiveis = new BarChartSeries();
        disponiveis.setLabel("Disponíveis");
        disponiveis.set("Caminhoes", caminhoesDisponiveis);
        disponiveis.set("Motoristas", motoristasDisponiveis);

        ChartSeries ocupados = new BarChartSeries();
        ocupados.setLabel("Ocupados");
        ocupados.set("Caminhoes", caminhaoRepositorio.getCount() - caminhoesDisponiveis);
        ocupados.set("Motoristas", motoristaRepositorio.getCount() - motoristasDisponiveis);

        model.addSeries(disponiveis);
        model.addSeries(ocupados);

        model.setTitle("Disponibilidade");
        model.setLegendPosition("ne");
        model.setShadow(false);
        model.setZoom(true);
    }

    public BarChartModel getModel() {
        return model;
    }

    public void setModel(BarChartModel model) {
        this.model = model;
    }
}
