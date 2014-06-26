package com.chegala.persistence;

import com.chegala.model.ModeloBase;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class BaseRepositorio<T extends ModeloBase> {
        
    private final Class<T> type;
    
    private final String selectListar = "SELECT l FROM %s l";
    private final String selectCount = "SELECT COUNT(l) FROM %s l";
    
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
    
    public T getUnico(Integer id){
        EntityManager em = JPA.getEM();
        return em.find(type, id);
    }
    
    public T customGetUnico(String customWhere, List<Parametro> parametros){
        EntityManager em = JPA.getEM();
        TypedQuery<T> typedQuery = em.createQuery(getSelect(selectListar + " WHERE "), type);
        
        for(Parametro parametro : parametros){
            typedQuery.setParameter(parametro.getNome(), parametro.getValor());
        }
        return null;
    }
    
    public List<T> getLista(){
        EntityManager em = JPA.getEM();
        return em.createQuery(getSelect(selectListar), type)
                .getResultList();
    }
    
    public List<T> customGetLista(String customWhere) {
        EntityManager em = JPA.getEM();
        return em.createQuery(getSelect(selectCount + " WHERE " + customWhere), type)
                .getResultList();
    }
    
    public Long getCount(){
        EntityManager em = JPA.getEM();
        return em.createQuery(getSelect(selectCount), Long.class)
                .getSingleResult();
    }
    
    public Long customGetCount(String customWhere) {
        EntityManager em = JPA.getEM();
        return em.createQuery(getSelect(selectCount + " WHERE " + customWhere), Long.class)
                .getSingleResult();
    }
    
    private String getSelect(String select){
        return String.format(select, type.getSimpleName());
    }
}
