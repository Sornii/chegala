package com.chegala.MB;

import com.chegala.model.Caminhao;
import com.chegala.model.Carga;
import com.chegala.model.Item;
import com.chegala.model.Motorista;
import com.chegala.persistence.CaminhaoRepositorio;
import com.chegala.persistence.CargaRepositorio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.chart.MeterGaugeChartModel;

@ManagedBean
@ViewScoped
public class CargaMB implements Serializable {

    private List<Caminhao> caminhoes;

    private List<Carga> cargasEntregar = CargaRepositorio.getCargasEntregar();
    private List<Carga> cargasEntregando = CargaRepositorio.getCargasEntregando();

    private Carga carga;
    private Item item;

    private MeterGaugeChartModel pesoGauge;
    private MeterGaugeChartModel volumeGauge;
    
    private List<Item> visualizarItens;

    @PostConstruct
    public void inicializar() {
        novaCarga();
        novoItem();
        atualizarGauges();
        atualizarCaminhoes();
        atualizarCargasEntregar();
        atualizarCargasEntregando();
    }

    private void atualizarGauges() {
        atualizarPesoGauge();
        atualizarVolumeGauge();
    }

    /*
    
    private Map<String, Object> getModelOptions() {
    Map<String, Object> options = new HashMap<String, Object>();
    options.put("modal", true);
    return options;
    }

    public void selecionaCaminhao() {
    RequestContext.getCurrentInstance().openDialog("selecionarCaminhao", getModelOptions(), null);
    }

    public void onSelecionaCaminhao(SelectEvent event) {
    Caminhao caminhao = (Caminhao) event.getObject();
    carga.setCaminhao(caminhao);
    atualizarGauges();
    }
    
    */
    
    private Carga cargaSelecionadaUltima;
    
    public void chegouLa(Carga carga){
        carga.getMotorista().setDisponivel(true);
        carga.getCaminhao().setDisponivel(true);
        carga.setEntregue(true);
        carga.salvar();
        atualizarCaminhoes();
        atualizarCargasEntregando();
        atualizarCargasEntregar();
    }
    
    public void selecionarMotoristaCarga(Carga carga){
        cargaSelecionadaUltima = carga;
        RequestContext.getCurrentInstance().openDialog("selecionarMotorista");
    }
    
    public void onSelecionarMotoristaCarga(SelectEvent event){
        Motorista motorista = (Motorista) event.getObject();
        motorista.setDisponivel(false);
        cargaSelecionadaUltima.setMotorista(motorista);
        cargaSelecionadaUltima.salvar();
        atualizarCargasEntregando();
        atualizarCargasEntregar();
    }
    
    private void atualizarCargasEntregar(){
        cargasEntregar = CargaRepositorio.getCargasEntregar();
    }
    
    private void atualizarCargasEntregando(){
        cargasEntregando = CargaRepositorio.getCargasEntregando();
    }
    
    private void atualizarCaminhoes(){
        caminhoes = CaminhaoRepositorio.getCaminhoesDisponiveis();
    }
    
    public void selecionarCaminhao(Caminhao caminhao) {
        carga.setCaminhao(caminhao);
        atualizarGauges();
    }

    public List<Caminhao> getCaminhoes() {
        return caminhoes;
    }

    public void setCaminhoes(List<Caminhao> caminhoes) {
        this.caminhoes = caminhoes;
    }

    public void cadastrarCarga() {
        carga.getCaminhao().setDisponivel(false);
        carga.salvar();
        inicializar();
    }

    public List<Carga> getCargasEntregar() {
        return cargasEntregar;
    }

    public void setCargasEntregar(List<Carga> cargasEntregar) {
        this.cargasEntregar = cargasEntregar;
    }

    public List<Carga> getCargasEntregando() {
        return cargasEntregando;
    }

    public void setCargasEntregando(List<Carga> cargasEntregando) {
        this.cargasEntregando = cargasEntregando;
    }

    public Carga getCarga() {
        return carga;
    }

    public void setCarga(Carga carga) {
        this.carga = carga;
    }

    public void novaCarga() {
        carga = new Carga();
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void inserirItem() {
        if (item != null && carga.addItem(item)) {
            novoItem();
            atualizarGauges();
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Esse item não cabe na carga, retire um para inseri-lo.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void excluirItem(Item item) {
        carga.excluirItem(item);
        atualizarGauges();
    }

    public void novoItem() {
        item = new Item();
    }

    public MeterGaugeChartModel getPesoGauge() {
        return pesoGauge;
    }

    public void setPesoGauge(MeterGaugeChartModel pesoGauge) {
        this.pesoGauge = pesoGauge;
    }

    public MeterGaugeChartModel getVolumeGauge() {
        return volumeGauge;
    }

    public void setVolumeGauge(MeterGaugeChartModel volumeGauge) {
        this.volumeGauge = volumeGauge;
    }

    public List<Item> getVisualizarItens() {
        return visualizarItens;
    }

    public void setVisualizarItens(List<Item> visualizarItens) {
        this.visualizarItens = visualizarItens;
    }

    private void atualizarPesoGauge() {
        if (carga.getCaminhao() == null) {
            pesoGauge = new MeterGaugeChartModel();
            return;
        }

        List<Number> intervalos = new ArrayList<Number>();
        Double pesoMax = carga.getCaminhao().getPesoMax();

        intervalos.add(pesoMax * 0.125);
        intervalos.add(pesoMax * 0.25);
        intervalos.add(pesoMax * 0.5);
        intervalos.add(pesoMax);

        pesoGauge = new MeterGaugeChartModel(carga.getPesoTotal(), intervalos);
        pesoGauge.setTitle("Peso ocupado");
        pesoGauge.setGaugeLabel("kg");
    }

    private void atualizarVolumeGauge() {
        if (carga.getCaminhao() == null) {
            volumeGauge = new MeterGaugeChartModel();
            return;
        }

        List<Number> intervalos = new ArrayList<Number>();
        Double pesoMax = carga.getCaminhao().getVolumeMax();

        intervalos.add(pesoMax * 0.125);
        intervalos.add(pesoMax * 0.25);
        intervalos.add(pesoMax * 0.5);
        intervalos.add(pesoMax);

        volumeGauge = new MeterGaugeChartModel(carga.getVolumeTotal(), intervalos);
        volumeGauge.setTitle("Volume ocupado");
        volumeGauge.setGaugeLabel("m2");
    }
}
