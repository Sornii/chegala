package com.chegala.persistence;

import com.chegala.model.ModeloBase;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class BaseRepositorio<T extends ModeloBase> {
        
    public void salvar(T value){
        EntityManager em = JPA.getEM();
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.merge(value);
        t.commit();
    }
    
    public void excluir(T value){
        EntityManager em = JPA.getEM();
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.remove(em.find(value.getClass(), value.getId()));
        t.commit();
    }
    
}
