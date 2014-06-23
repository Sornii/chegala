/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chegala.chart;

import com.chegala.persistence.CaminhaoRepositorio;
import com.chegala.persistence.MotoristaRepositorio;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author Igor
 */
@ManagedBean
public class ChartIndex {
    
    @Inject
    private CaminhaoRepositorio caminhaoRepositorio;
    
    @Inject
    private MotoristaRepositorio motoristaRepositorio;

    private BarChartModel model;

    @PostConstruct
    public void init() {
        model = new BarChartModel();
        
        Integer caminhoesDisponiveis = caminhaoRepositorio.contarCaminhoesDisponiveis();

        ChartSeries disponiveis = new BarChartSeries();
        disponiveis.setLabel("Disponíveis");
        disponiveis.set("Caminhoes", caminhoesDisponiveis);
        disponiveis.set("Motoristas", MotoristaRepositorio.getMotoristasDisponiveis().size());

        ChartSeries ocupados = new BarChartSeries();
        ocupados.setLabel("Ocupados");
        ocupados.set("Caminhoes", caminhaoRepositorio.contarCaminhoes() - caminhoesDisponiveis);
        ocupados.set("Motoristas", MotoristaRepositorio.getMotoristas().size() - MotoristaRepositorio.getMotoristasDisponiveis().size());

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
