package com.chegala.persistence;

import com.chegala.model.ModeloBase;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class BaseRepositorio<T extends ModeloBase> {
        
    private final Class<T> type;
    
    private final String selectListar = "SELECT l FROM %s l";

    public BaseRepositorio(Class<T> type) {
        this.type = type;
    }
    
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
    
    public T getUnico(int id){
        EntityManager em = JPA.getEM();
        return em.find(type, id);
    }
    
    public List<T> getLista(){
        EntityManager em = JPA.getEM();
        return em.createQuery(getSelect(selectListar), type)
                .getResultList();
    }
    
    public String getSelect(String select){
        return String.format(select, type.getSimpleName());
    }
}
