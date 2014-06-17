package com.chegala.persistence;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import com.chegala.model.Caminhao;
import com.chegala.model.Carga;
import javax.persistence.TypedQuery;

public class CaminhaoRepositorio {

    public static void salvar(Caminhao caminhao) {
        EntityManager em = JPA.getEM();
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.merge(caminhao);
        t.commit();
    }

    public static void excluir(Caminhao caminhao) {
        EntityManager em = JPA.getEM();
        EntityTransaction t = em.getTransaction();
        t.begin();

        em.remove(em.find(Caminhao.class, caminhao.getId()));

        t.commit();
    }

    public static Caminhao getCaminhao(Integer codigo) {
        EntityManager em = JPA.getEM();
        return em.find(Caminhao.class, codigo);
    }

    public static Caminhao getCaminhao(String placa) {
        EntityManager em = JPA.getEM();
        TypedQuery<Caminhao> query = em.createQuery("select c from Caminhao c where c.placa = :placa", Caminhao.class);
        query.setParameter("placa", placa);
        return query.getSingleResult();
    }

    public static List<Caminhao> getCaminhoes() {
        EntityManager em = JPA.getEM();
        return em.createQuery("select c from Caminhao c", Caminhao.class).getResultList();
    }

    public static List<Caminhao> getCaminhoesDisponiveis() {
        EntityManager em = JPA.getEM();
        return em.createQuery("SELECT c "
                + "FROM Caminhao c "
                + "WHERE c.disponivel = true", Caminhao.class).getResultList();
    }

    public static boolean isCaminhaoDisponivel(Integer codigo) {
        EntityManager em = JPA.getEM();
        TypedQuery<Caminhao> query = 
                em.createQuery("SELECT c "
                + "FROM Caminhao c "
                + "WHERE c.id = :codigo "
                + "AND c.disponivel = false", Caminhao.class);
        query.setParameter("codigo", codigo);
        return query.getResultList().isEmpty();
    }
}
