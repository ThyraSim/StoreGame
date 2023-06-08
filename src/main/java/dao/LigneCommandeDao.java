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

    public static List<LigneCommande> findLignesCommandeByCompteId(int compteId) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("connection");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String jpql = "SELECT b FROM LigneCommande b " +
                "JOIN b.compte c " +
                "WHERE c.id = :compteId AND b.panier = false";

        TypedQuery<LigneCommande> query = entityManager.createQuery(jpql, LigneCommande.class);
        query.setParameter("compteId", compteId);

        List<LigneCommande> ligneCommandeList = query.getResultList();

        entityManager.close();
        entityManagerFactory.close();

        return ligneCommandeList;
    }

    public static List<LigneCommande> findPanierByCompteId(int compteId) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("connection");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String jpql = "SELECT b FROM LigneCommande b " +
                "JOIN b.compte c " +
                "WHERE c.id = :compteId AND b.panier = true";

        TypedQuery<LigneCommande> query = entityManager.createQuery(jpql, LigneCommande.class);
        query.setParameter("compteId", compteId);

        List<LigneCommande> ligneCommandeList = query.getResultList();

        entityManager.close();
        entityManagerFactory.close();

        return ligneCommandeList;
    }

    public static void afficherListeLigneCommande() {
        List<LigneCommande> ligneCommandeList = findAll();
        for(LigneCommande ligneCommande: ligneCommandeList) {
            System.out.print(ligneCommande + " ");
        }
    }

    public static void changePanier(int compteId) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("connection");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaUpdate<LigneCommande> updateQuery = criteriaBuilder.createCriteriaUpdate(LigneCommande.class);
            Root<LigneCommande> root = updateQuery.from(LigneCommande.class);

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
            CriteriaUpdate<LigneCommande> updateQuery = criteriaBuilder.createCriteriaUpdate(LigneCommande.class);
            Root<LigneCommande> root = updateQuery.from(LigneCommande.class);

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
}
