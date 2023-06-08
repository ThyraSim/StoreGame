package controleur;

import dao.CommandeDao;
import dao.CompteDao;
import dao.JeuDao;
import dao.LigneCommandeDao;
import entities.Commande;
import entities.Compte;
import entities.Jeu;
import entities.LigneCommande;

import java.util.List;

public class Main {
    public static void main(String[] args){
//        JeuDao.insert(new Jeu("Chat", 29.99, "Action", "Le moyen de tout les temps"));
//        CompteDao.insert(new Compte("THEFRIEND", "Chapeau", "Orignal"));
//        CommandeDao.insert(new Commande(CompteDao.findCompteById(1), false));
//        LigneCommandeDao.insert(new LigneCommande(JeuDao.findJeuById(1), CommandeDao.findCommandeById(1), false));
//        JeuDao.delete(2);
//        CompteDao.delete(1);
        LigneCommandeDao.delete(5);
//        JeuDao.afficherListeJeux();
//        CompteDao.afficherListeComptes();
//        BibliothequeDao.afficherListeBibliotheques();

        List<Commande> commandes = CommandeDao.findCommandeByCompteId(1);
        for(Commande commande:commandes){
            LigneCommande ligne = commande.getLignes().get(0);
            Jeu jeu = ligne.getJeu();
            System.out.println(jeu.getNomJeu());
        }

    }
}