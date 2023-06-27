package dao;

import entities.Client;
import entities.Compte;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class ClientDao {

    public static void insert(Client client) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("connection");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            Compte managedCompte = entityManager.merge(client.getCompte());
            client.setCompte(managedCompte);

            entityManager.persist(client);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public static void update(Client updatedClient) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("connection");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            Client existingClient = entityManager.find(Client.class, updatedClient.getIdClient());
            if (existingClient != null) {
                // Update the fields of the existingClient object with the new values
                existingClient.setNom(updatedClient.getNom());
                existingClient.setPrenom(updatedClient.getPrenom());
                existingClient.setAdressePhysique(updatedClient.getAdressePhysique());
                existingClient.setAdresseCourriel(updatedClient.getAdresseCourriel());
                // Update other fields as needed

                // Persist the changes to the database
                entityManager.merge(existingClient);
                entityManager.getTransaction().commit();
            } else {
                // Handle the case where the client with the given ID is not found
                System.out.println("Client with ID " + updatedClient.getIdClient() + " not found.");
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
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("connection");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Client.class, id));
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

    public static List<Client> findAll() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("connection");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            Query query = entityManager.createQuery("SELECT c FROM Client c");
            return query.getResultList();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public static Client findClientById(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("connection");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            return entityManager.find(Client.class, id);
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public static Client findClientByCompteId(int compteId) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("connection");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            return entityManager.find(Client.class, compteId);
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public static Client findClientByCompte(Compte compte) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("connection");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            Query query = entityManager.createQuery("SELECT c FROM Client c WHERE c.compte = :compte");
            query.setParameter("compte", compte);
            return (Client) query.getSingleResult();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
