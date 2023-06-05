package dao;

import entities.Compte;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class CompteDao {

    public static void insert(Compte compte){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("connection");
        EntityManager entityManager = null;
        try{
            entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();
            entityManager.persist(compte);
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
            //Cherche le id  dans la classe compte (rajoute un merge)
            entityManager.remove(entityManager.find(Compte.class, id));
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();//Permet de supprimer l'insertion s'il y a eu un probleme
            e.printStackTrace();//affiche l'exception
            return false;
        }

    }

    public static List<Compte> findAll() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("connection");
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        //  Query query = entityManager.createNativeQuery("select * from compte");// un Object
        Query query = entityManager.createQuery("select i from Compte i");//le type compte criteria
        return query.getResultList();
    }

    public static void afficherListeComptes() {
        List<Compte> compteList = findAll();
        for(Compte compte: compteList) {
            System.out.print(compte + " ");
        }
    }

    public static Compte findCompteById(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("connection");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Compte compte = entityManager.find(Compte.class, id);

        return compte;
    }
}
