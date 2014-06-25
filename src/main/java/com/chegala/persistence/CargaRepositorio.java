package com.chegala.persistence;

import com.chegala.model.Carga;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class CargaRepositorio extends BaseRepositorio<Carga> {
    
    private static CargaRepositorio instance = new CargaRepositorio(Carga.class);
    
    private CargaRepositorio(Class<Carga> type) {
        super(type);
    }
    
    public static CargaRepositorio getInstance(){
        return instance;
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
