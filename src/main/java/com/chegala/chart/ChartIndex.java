package com.chegala.chart;

import com.chegala.persistence.CaminhaoRepositorio;
import com.chegala.persistence.MotoristaRepositorio;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.ChartSeries;

@ManagedBean
public class ChartIndex {

    private final CaminhaoRepositorio caminhaoRepositorio = CaminhaoRepositorio.getInstance();
    private final MotoristaRepositorio motoristaRepositorio = MotoristaRepositorio.getInstance();

    private final Long caminhoesDisponiveis = caminhaoRepositorio.contarCaminhoesDisponiveis();
    private final Long motoristasDisponiveis = motoristaRepositorio.contarMotoristasDisponiveis();

    private BarChartModel model;

    @PostConstruct
    public void inicializar() {
        MontarModel();
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
