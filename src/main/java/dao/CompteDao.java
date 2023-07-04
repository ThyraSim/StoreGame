package dao;

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

    public static void update(Compte updatedCompte) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("connection");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Compte existingCompte = entityManager.find(Compte.class, updatedCompte.getIdCompte());
            if (existingCompte != null) {
                existingCompte.setUser(updatedCompte.getUser());
                existingCompte.setProfileName(updatedCompte.getProfileName());
                existingCompte.setPassword(updatedCompte.getPassword());

                // Persist the changes to the database
                entityManager.merge(existingCompte);
                entityManager.getTransaction().commit();
            } else {
                // Handle the case where the compte with the given ID is not found
                System.out.println("Compte with ID " + updatedCompte.getIdCompte() + " not found.");
            }
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

    public static Compte findByUsername(String username) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("connection");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            Query query = entityManager.createQuery("SELECT c FROM Compte c WHERE c.user = :username");
            query.setParameter("username", username);
            List<Compte> resultList = query.getResultList();
            if (!resultList.isEmpty()) {
                return resultList.get(0);
            }
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }

        return null;
    }

}
