package dao;

import entities.Bibliotheque;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class BibliothequeDao {

    public static void insert(Bibliotheque bibliotheque){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("connection");
        EntityManager entityManager = null;
        try{
            entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();
            entityManager.persist(bibliotheque);
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
            //Cherche le id  dans la classe bibliotheque (rajoute un merge)
            entityManager.remove(entityManager.find(Bibliotheque.class, id));
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();//Permet de supprimer l'insertion s'il y a eu un probleme
            e.printStackTrace();//affiche l'exception
            return false;
        }

    }

    public static List<Bibliotheque> findAll() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("connection");
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        //  Query query = entityManager.createNativeQuery("select * from bibliotheque");// un Object
        Query query = entityManager.createQuery("select i from Bibliotheque i");//le type bibliotheque criteria
        return query.getResultList();
    }

    public static void afficherListeBibliotheque() {
        List<Bibliotheque> bibliothequeList = findAll();
        for(Bibliotheque bibliotheque: bibliothequeList) {
            System.out.print(bibliotheque + " ");
        }
    }
}
