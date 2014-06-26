package com.chegala.persistence;

import com.chegala.model.Motorista;
import java.util.List;

public class MotoristaRepositorio extends BaseRepositorio<Motorista> {

    private static final MotoristaRepositorio instance = new MotoristaRepositorio(Motorista.class);

    private MotoristaRepositorio(Class<Motorista> type) {
        super(type);
    }

    public static MotoristaRepositorio getInstance() {
        return instance;
    }
    
    public List<Motorista> getMotoristasDisponiveis() {
        return customGetLista("m.disponivel = true ");
    }
    
    public Long contarMotoristasDisponiveis() {
        return customGetCount("m.disponivel = true ");
    }
}
