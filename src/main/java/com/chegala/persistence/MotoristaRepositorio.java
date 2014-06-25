package com.chegala.persistence;

import com.chegala.model.Motorista;
import java.util.List;
import javax.persistence.EntityManager;

public class MotoristaRepositorio extends BaseRepositorio<Motorista> {

    private static final MotoristaRepositorio instance = new MotoristaRepositorio(Motorista.class);

    private MotoristaRepositorio(Class<Motorista> type) {
        super(type);
    }

    public static MotoristaRepositorio getInstance() {
        return instance;
    }
    
    public List<Motorista> getMotoristasDisponiveis() {
        EntityManager em = JPA.getEM();
        return em.createQuery("SELECT m "
                + "FROM Motorista m "
                + "WHERE m.disponivel = true ", Motorista.class).getResultList();
    }
    
    public Long contarMotoristasDisponiveis() {
        EntityManager em = JPA.getEM();
        return em.createQuery("SELECT count(m) "
                + "FROM Motorista m "
                + "WHERE m.disponivel = true ", Long.class).getSingleResult();
    }
}
