package dao;

import entities.Commande;
import entities.LigneCommande;
import jakarta.persistence.*;

import java.util.List;

public class CommandeDao {
    public static void insert(Commande commande){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("connection");
        EntityManager entityManager = null;
        try{
            entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();
            entityManager.persist(commande);
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

            entityManager.remove(entityManager.find(Commande.class, id));
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }

    }

        public static List<Commande> findAll() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("connection");
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        Query query = entityManager.createQuery("select i from Commande i");

        return query.getResultList();
    }

    public static void afficherListeCommandes() {
        List<Commande> CommandeList = findAll();
        for(Commande commande: CommandeList) {
            System.out.print(commande + " ");
        }
    }

    public static Commande findCommandeById(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("connection");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Commande commande = entityManager.find(Commande.class, id);

        return commande;
    }

//    public static List<Commande> findCommandeByCompteId(int compteId){
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("connection");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//
//        String jpql = "SELECT b FROM Commande b " +
//                "JOIN b.compte c " +
//                "WHERE c.id = :compteId AND b.panier = false";
//
//        TypedQuery<Commande> query = entityManager.createQuery(jpql, Commande.class);
//        query.setParameter("compteId", compteId);
//
//        List<Commande> listeCommande = query.getResultList();
//
//        entityManager.close();
//        entityManagerFactory.close();
//
//        return listeCommande;
//    }

//    public static Commande findPanierByCompteId(int compteId) {
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("connection");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//
//        String jpql = "SELECT b FROM Commande b " +
//                "JOIN b.compte c " +
//                "WHERE c.id = :compteId AND b.panier = true";
//
//        TypedQuery<Commande> query = entityManager.createQuery(jpql, Commande.class);
//        query.setParameter("compteId", compteId);
//
//        Commande commande = query.getSingleResult();
//
//        entityManager.close();
//        entityManagerFactory.close();
//
//        return commande;
//    }
}
