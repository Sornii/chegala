/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.chegala.persistence;

import com.chegala.model.Carga;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

/**
 *
 * @author Igor
 */
public class CargaRepositorio {
    public static void salvar(Carga carga){
        EntityManager em = JPA.getEM();
        EntityTransaction t = em.getTransaction();
        t.begin();
        
        em.merge(carga);
        
        t.commit();
    }
    
    public static void excluir(Carga carga) {        
        EntityManager em = JPA.getEM();
        EntityTransaction t = em.getTransaction();
        t.begin();
        
        em.remove(em.find(Carga.class, carga.getId()));
        
        t.commit();
    }
    
    public static Carga getCarga(Integer codigo){
        EntityManager em = JPA.getEM();
        return em.find(Carga.class, codigo);
    }
    
    public static List<Carga> getCargas(){
        EntityManager em = JPA.getEM();
        return em.createQuery("select c from Carga c", Carga.class).getResultList();
    }
    
    public static List<Carga> getCargasEntregando(){
        EntityManager em = JPA.getEM();
        return em.createQuery("select c from Carga c where c.motorista is not null and c.entregue = false", Carga.class).getResultList();
    }
    
    public static List<Carga> getCargasEntregar(){
        EntityManager em = JPA.getEM();
        return em.createQuery("select c from Carga c where c.motorista is null", Carga.class).getResultList();
    }
    
    public static List<Carga> getCargasEntregues(){
        EntityManager em = JPA.getEM();
        TypedQuery<Carga> query = em.createQuery("select c from Carga c where c.entregue = true", Carga.class);
        return query.getResultList();
    }
}
