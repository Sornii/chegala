package com.chegala.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPA {

    private static EntityManagerFactory emf;

    public static EntityManager getEM() {

        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("chegalaPU");
        }

        return emf.createEntityManager();
    }
}
