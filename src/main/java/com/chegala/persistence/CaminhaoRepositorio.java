package com.chegala.persistence;

import java.util.List;
import javax.persistence.EntityManager;
import com.chegala.model.Caminhao;
import javax.persistence.TypedQuery;

public class CaminhaoRepositorio extends BaseRepositorio<Caminhao>{
    
    private static final CaminhaoRepositorio instance = new CaminhaoRepositorio(Caminhao.class);

    private CaminhaoRepositorio(Class<Caminhao> type) {
        super(type);
    }
    
    public static CaminhaoRepositorio getInstance(){
        return instance;
    }

    public Caminhao getCaminhao(String placa) {
        EntityManager em = JPA.getEM();
        TypedQuery<Caminhao> query = em.createQuery("SELECT c "
                + "FROM Caminhao c "
                + "WHERE c.placa = :placa", Caminhao.class);
        query.setParameter("placa", placa);
        return query.getSingleResult();
    }

    public List<Caminhao> getCaminhoesDisponiveis() {
        EntityManager em = JPA.getEM();
        return em.createQuery("SELECT c "
                + "FROM Caminhao c "
                + "WHERE c.disponivel = true", Caminhao.class)
                .getResultList();
    }
    
    public Integer contarCaminhoesDisponiveis(){
        EntityManager em = JPA.getEM();
        return em.createQuery("SELECT count(c) "
                + "FROM Caminhao c "
                + "WHERE c.disponivel = true", Integer.class)
                .getSingleResult();
        
    }
    
    public Integer contarCaminhoes(){
        EntityManager em = JPA.getEM();
        return em.createQuery("SELECT count(c) "
                + "FROM Caminhao c " , Integer.class)
                .getSingleResult();
    }
}
