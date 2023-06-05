package controleur;

import dao.BibliothequeDao;
import dao.CompteDao;
import entities.Bibliotheque;
import entities.Compte;
import entities.Jeu;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import dao.JeuDao;

public class Main {
    public static void main(String[] args){
        //JeuDao.insert(new Jeu("Skyrim", 19.99, "Masterpiece", "Le meilleur jeu de tous les temps"));
        //CompteDao.insert(new Compte("Chat", "Chapeau", "Orignal"));
        //BibliothequeDao.insert(new Bibliotheque(JeuDao.findJeuById(1), CompteDao.findCompteById(2), true));
        //JeuDao.delete(2);
        //CompteDao.delete(1);
        //BibliothequeDao.delete(2);
        //JeuDao.afficherListeJeux();
        //CompteDao.afficherListeComptes();
        //BibliothequeDao.afficherListeBibliotheques();
    }
}