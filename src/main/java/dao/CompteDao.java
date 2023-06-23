package dao;

import entities.Client;
import entities.Compte;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class CompteDao {

    public static void insert(Compte compte) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("connection");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(compte);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public static boolean delete(Integer id) {
        ClientDao.delete(id);
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("connection");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Compte.class, id));
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public static List<Compte> findAll() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("connection");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            Query query = entityManager.createQuery("SELECT i FROM Compte i");
            return query.getResultList();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public static void afficherListeComptes() {
        List<Compte> compteList = findAll();
        for (Compte compte : compteList) {
            System.out.print(compte + " ");
        }
    }

    public static Compte findCompteById(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("connection");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            return entityManager.find(Compte.class, id);
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
