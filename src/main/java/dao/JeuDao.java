package dao;

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
            //Permet d'utiliser les fonctions pour manipuler la bd
            entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();
            //Cherche le id  dans la classe Jeu (rajoute un merge)
            entityManager.remove(entityManager.find(Jeu.class, id));
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();//Permet de supprimer l'insertion s'il y a eu un probleme
            e.printStackTrace();//affiche l'exception
            return false;
        }

    }

    public static List<Jeu> findAll() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("connection");
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        //  Query query = entityManager.createNativeQuery("select * from Jeu");// un Object
        Query query = entityManager.createQuery("select i from Jeu i");//le type Jeu criteria
        return query.getResultList();
    }

    public static void afficherListeJeux() {
        List<Jeu> jeuList = findAll();
        for(Jeu jeu: jeuList) {
            System.out.print(jeu + " ");
        }
    }

    public static Jeu findJeuById(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("connection");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Jeu jeu = entityManager.find(Jeu.class, id);

        return jeu;
    }
}
