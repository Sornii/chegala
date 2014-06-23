package com.chegala.persistence;

import com.chegala.model.Carga;
import java.util.List;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Singleton
public class CargaRepositorio extends BaseRepositorio<Carga> {
    
    private static CargaRepositorio instance;
    
    private CargaRepositorio(){}
    
    public static CargaRepositorio getInstance(){
        if (instance == null) {
            instance = new CargaRepositorio();
        }
        return instance;
    }
    
    public Carga getCarga(Integer codigo){
        EntityManager em = JPA.getEM();
        return em.find(Carga.class, codigo);
    }
    
    public List<Carga> getCargas(){
        EntityManager em = JPA.getEM();
        return em.createQuery("select c from Carga c", Carga.class).getResultList();
    }
    
    public List<Carga> getCargasEntregando(){
        EntityManager em = JPA.getEM();
        return em.createQuery("select c from Carga c where c.motorista is not null and c.entregue = false", Carga.class).getResultList();
    }
    
    public List<Carga> getCargasEntregar(){
        EntityManager em = JPA.getEM();
        return em.createQuery("select c from Carga c where c.motorista is null", Carga.class).getResultList();
    }
    
    public List<Carga> getCargasEntregues(){
        EntityManager em = JPA.getEM();
        TypedQuery<Carga> query = em.createQuery("select c from Carga c where c.entregue = true", Carga.class);
        return query.getResultList();
    }
}
