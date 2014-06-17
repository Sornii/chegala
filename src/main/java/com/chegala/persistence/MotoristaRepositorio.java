/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.chegala.persistence;

import com.chegala.model.Motorista;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author Igor
 */
public class MotoristaRepositorio {
    public static void salvar(Motorista motorista){
        EntityManager em = JPA.getEM();
        EntityTransaction t = em.getTransaction();
        t.begin();
        
        em.merge(motorista);
        
        t.commit();
    }
    
    public static void excluir(Motorista motorista) {        
        EntityManager em = JPA.getEM();
        EntityTransaction t = em.getTransaction();
        t.begin();
        
        em.remove(em.find(Motorista.class, motorista.getId()));
        
        t.commit();
    }
    
    public static Motorista getMotorista(Integer codigo){
        EntityManager em = JPA.getEM();
        return em.find(Motorista.class, codigo);
    }
    
    public static List<Motorista> getMotoristas(){
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
