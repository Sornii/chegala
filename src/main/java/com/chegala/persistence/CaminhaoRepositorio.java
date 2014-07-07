package com.chegala.persistence;

import com.chegala.model.Caminhao;
import java.util.ArrayList;
import java.util.List;

public class CaminhaoRepositorio extends BaseRepositorio<Caminhao> {

    private static final CaminhaoRepositorio instance = new CaminhaoRepositorio(Caminhao.class);

    private CaminhaoRepositorio(Class<Caminhao> type) {
        super(type);
    }

    public static CaminhaoRepositorio getInstance() {
        return instance;
    }

    public Caminhao getCaminhao(String placa) {
        List<Parametro> parametros = new ArrayList<Parametro>();
        parametros.add(new Parametro("placa", placa));

        return customGetUnico("c.placa = :placa", parametros);
    }

    public List<Caminhao> getCaminhoesDisponiveis() {
        return customGetLista("c.disponivel = true");
    }

    public Long contarCaminhoesDisponiveis() {
        return customGetCount("c.disponivel = true");
    }
}
