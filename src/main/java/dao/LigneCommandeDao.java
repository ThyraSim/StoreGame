package dao;

import entities.Compte;
import entities.LigneCommande;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class LigneCommandeDao {
    public static void insert(LigneCommande ligneCommande){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("connection");
        EntityManager entityManager = null;
        try{
            entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();
            entityManager.persist(ligneCommande);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public static boolean delete(Integer id) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("connection");
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();

            entityManager.remove(entityManager.find(LigneCommande.class, id));
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }

    }

    public static List<LigneCommande> findAll() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("connection");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createQuery("select i from LigneCommande i");
        return query.getResultList();
    }

    public static void afficherListeLigneCommande() {
        List<LigneCommande> ligneCommandeList = findAll();
        for(LigneCommande ligneCommande: ligneCommandeList) {
            System.out.print(ligneCommande + " ");
        }
    }




}
