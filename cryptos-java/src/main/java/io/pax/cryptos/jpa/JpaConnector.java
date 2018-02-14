package io.pax.cryptos.jpa;

import io.pax.cryptos.domain.jpa.JpaUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by AELION on 13/02/2018.
 */
public class JpaConnector {

   static EntityManagerFactory factory;

    void connect(){
        if(factory == null){
            factory=  Persistence.createEntityManagerFactory("cryptos");
        }
    }

    public EntityManager createEntityManager(){

        // if already connected, do nothing
        this.connect();
        return factory.createEntityManager();
    }

    public void close(){
        factory.close();
    }

    public static void main(String[] args) {

        JpaConnector connector = new JpaConnector();
        EntityManager em =connector.createEntityManager();

        JpaUser jean = new JpaUser();
        jean.setName("Jean");

        JpaUser jack = new JpaUser();
        jack.setName("Jack");

        JpaUser jackie = new JpaUser();
        jackie.setName("Jackie");

        JpaUser jules = new JpaUser();
        jules.setName("Jules");

        JpaUser jasper = new JpaUser();
        jasper.setName("Jasper");

        // Open the box
        em.getTransaction().begin();
        em.persist(jean);
        em.persist(jack);
        em.persist(jackie);
        em.persist(jules);
        em.persist(jasper);

        em.getTransaction().commit();
        em.close();
        connector.close();

    }



}
