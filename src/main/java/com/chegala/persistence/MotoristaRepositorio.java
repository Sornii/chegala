package com.chegala.persistence;

import com.chegala.model.Motorista;
import java.util.List;
import javax.persistence.EntityManager;

public class MotoristaRepositorio extends BaseRepositorio<Motorista> {

    private static final MotoristaRepositorio instance = new MotoristaRepositorio();

    private MotoristaRepositorio() {
    }

    public static MotoristaRepositorio getInstance() {
        return instance;
    }

    public Motorista getMotorista(Integer codigo) {
        EntityManager em = JPA.getEM();
        return em.find(Motorista.class, codigo);
    }

    public List<Motorista> getMotoristas() {
        EntityManager em = JPA.getEM();
        return em.createQuery("select c from Motorista c").getResultList();
    }
    
    public Integer contarMotoristas() {
        EntityManager em = JPA.getEM();
        return em.createQuery("select count(c) from Motorista c", Integer.class).getSingleResult();
    }

    public List<Motorista> getMotoristasDisponiveis() {
        EntityManager em = JPA.getEM();
        return em.createQuery("SELECT m "
                + "FROM Motorista m "
                + "WHERE m.disponivel = true ", Motorista.class).getResultList();
    }
    
    public Integer contarMotoristasDisponiveis() {
        EntityManager em = JPA.getEM();
        return em.createQuery("SELECT count(m) "
                + "FROM Motorista m "
                + "WHERE m.disponivel = true ", Integer.class).getSingleResult();
    }
}
