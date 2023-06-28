package dao;

import entities.Commande;
import entities.Compte;
import entities.Jeu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class JeuDao {

    public static void insert(Jeu jeu){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("connection");
        EntityManager entityManager = null;
        try{
            entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();
            entityManager.persist(jeu);
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

            entityManager.remove(entityManager.find(Jeu.class, id));
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }

    }

    public static List<Jeu> findAll() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("connection");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createQuery("select i from Jeu i");
        return query.getResultList();
    }

    public static void afficherListeJeux() {
        List<Jeu> jeuList = findAll();
        for(Jeu jeu: jeuList) {
            System.out.print(jeu + " ");
        }
    }

    public static void insertWithImage(Jeu jeu, String imagePath) {
        // Insérer le jeu dans la base de données
        insert(jeu);

        // Récupérer l'ID du jeu nouvellement inséré
        int jeuId = jeu.getIdJeu();

        // Effectuer les opérations nécessaires pour associer l'image au jeu (par exemple, enregistrer le chemin d'image dans la base de données ou copier l'image vers un répertoire spécifique)

        // Ici, vous pouvez ajouter votre logique pour associer le chemin d'image au jeu
    }

    public static Jeu findJeuById(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("connection");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Jeu jeu = entityManager.find(Jeu.class, id);

        return jeu;
    }

//    public static boolean update(Jeu updatedJeu) {
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("connection");
//        EntityManager entityManager = null;
//
//        try {
//            entityManager = entityManagerFactory.createEntityManager();
//            entityManager.getTransaction().begin();
//
//            Jeu existingJeu = entityManager.find(Jeu.class, updatedJeu.getIdJeu());
//
//            if (existingJeu != null) {
//                // Update the properties
//                existingJeu.setCommandes(updatedJeu.getCommandes());
//                // ... update other properties as needed
//
//                entityManager.getTransaction().commit();
//                return true;
//            } else {
//                return false; // Commande not found
//            }
//        } catch (Exception e) {
//            if (entityManager != null && entityManager.getTransaction().isActive()) {
//                entityManager.getTransaction().rollback();
//            }
//            e.printStackTrace();
//            return false;
//        } finally {
//            if (entityManager != null) {
//                entityManager.close();
//            }
//        }
//    }
}
