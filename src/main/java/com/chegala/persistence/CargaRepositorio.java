package com.chegala.persistence;

import com.chegala.model.Carga;
import java.util.List;

public class CargaRepositorio extends BaseRepositorio<Carga> {

    private static final CargaRepositorio instance = new CargaRepositorio(Carga.class);

    private CargaRepositorio(Class<Carga> type) {
        super(type);
    }

    public static CargaRepositorio getInstance() {
        return instance;
    }

    public List<Carga> getCargasEntregando() {
        return customGetLista("c.motorista is not null and c.entregue = false");
    }

    public List<Carga> getCargasEntregar() {
        return customGetLista("c.motorista is null");
    }

    public List<Carga> getCargasEntregues() {
        return customGetLista("c.entregue = true");
    }

    public Long contarCargasEntregar() {
        return customGetCount("c.motorista is null");
    }

    public Long contarCargaCriadaPorData(String data) {
        return customGetCount("c.dataSaida = ");
    }
}
