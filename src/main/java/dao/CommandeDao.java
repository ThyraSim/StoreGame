package dao;

import entities.Commande;
import entities.Compte;
import entities.Jeu;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

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

    public static void changePanier(int compteId) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("connection");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaUpdate<Commande> updateQuery = criteriaBuilder.createCriteriaUpdate(Commande.class);
            Root<Commande> root = updateQuery.from(Commande.class);

            updateQuery.set(root.get("panier"), false);
            updateQuery.where(criteriaBuilder.equal(root.get("compte").get("id"), compteId));

            int updatedCount = entityManager.createQuery(updateQuery).executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public static void changePanierGift(int compteId, int giftId) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("connection");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaUpdate<Commande> updateQuery = criteriaBuilder.createCriteriaUpdate(Commande.class);
            Root<Commande> root = updateQuery.from(Commande.class);

            updateQuery.set(root.get("panier"), false);
            updateQuery.set(root.get("compte"), entityManager.getReference(Compte.class, giftId));
            updateQuery.where(
                    criteriaBuilder.and(
                            criteriaBuilder.equal(root.get("panier"), true),
                            criteriaBuilder.equal(root.get("compte").get("id"), compteId)
                    )
            );

            int updatedCount = entityManager.createQuery(updateQuery).executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    @Transactional
    public static boolean update(Commande updatedCommande) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("connection");
        EntityManager entityManager = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            Commande existingCommande = entityManager.find(Commande.class, updatedCommande.getIdCommande());

            if (existingCommande != null) {
                existingCommande.getJeux().clear(); // Clear the existing Jeux collection

                // Add the Jeu objects from the updatedCommande to the existingCommande
                for (Jeu jeu : updatedCommande.getJeux()) {
                    existingCommande.getJeux().add(jeu);
                }

                entityManager.merge(existingCommande); // Merge the updated entity

                entityManager.getTransaction().commit();
                return true;
            } else {
                return false; // Commande not found
            }
        } catch (Exception e) {
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
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
