package controleur;

import dao.CompteDao;
import dao.JeuDao;
import dao.LigneCommandeDao;
import entities.Compte;
import entities.Jeu;
import entities.LigneCommande;

public class Main {
    public static void main(String[] args){
        JeuDao.insert(new Jeu("Chat", 29.99, "Action", "Le moyen de tout les temps"));
        CompteDao.insert(new Compte("THEFRIEND", "Chapeau", "Orignal"));
        LigneCommandeDao.insert(new LigneCommande(JeuDao.findJeuById(1), CompteDao.findCompteById(1), true, false));
//        JeuDao.delete(2);
//        CompteDao.delete(1);
//        BibliothequeDao.delete(2);
//        JeuDao.afficherListeJeux();
//        CompteDao.afficherListeComptes();
//        BibliothequeDao.afficherListeBibliotheques();
    }
}