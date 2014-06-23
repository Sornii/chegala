/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chegala.persistence;

import com.chegala.model.Motorista;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Igor
 */
public class MotoristaRepositorio extends BaseRepositorio<Motorista> {

    private static final MotoristaRepositorio instance = new MotoristaRepositorio();

    private MotoristaRepositorio() {
    }

    public static MotoristaRepositorio getInstance() {
        return instance;
    }

    public static Motorista getMotorista(Integer codigo) {
        EntityManager em = JPA.getEM();
        return em.find(Motorista.class, codigo);
    }

    public static List<Motorista> getMotoristas() {
        EntityManager em = JPA.getEM();
        return em.createQuery("select c from Motorista c").getResultList();
    }

    public static List<Motorista> getMotoristasDisponiveis() {
        EntityManager em = JPA.getEM();
        return em.createQuery("SELECT m "
                + "FROM Motorista m "
                + "WHERE m.disponivel = true ", Motorista.class).getResultList();
    }
}
