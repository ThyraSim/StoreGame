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
        CompteDao.insert(new Compte("theActualFriend", "fromage", "DaFriend"));
//        CommandeDao.insert(new Commande(CompteDao.findCompteById(1), false));
//        LigneCommandeDao.insert(new LigneCommande(JeuDao.findJeuById(1), CommandeDao.findCommandeById(1)));
//        JeuDao.delete(2);
//        CompteDao.delete(1);
//        LigneCommandeDao.delete(5);
//        JeuDao.afficherListeJeux();
//        CompteDao.afficherListeComptes();
//        BibliothequeDao.afficherListeBibliotheques();

//        List<Commande> commandes = CommandeDao.findCommandeByCompteId(1);
//        for(Commande commande:commandes){
//            LigneCommande ligne = commande.getLignes().get(0);
//            Jeu jeu = ligne.getJeu();
//            System.out.println(jeu.getNomJeu());

        //TESTER FOCNTION DAO CLIENT

        //        Client c1 = new Client("Tremblay1","bob","11 rue la marche", "123@chat" );
//        Client c2 = new Client("Tremblay2","bob","11 rue la marche", "123@chat" );
//        Client c3 = new Client("Tremblay3","bob","11 rue la marche", "123@chat" );
//        ClientDao.insert(c1);
//        ClientDao.insert(c2);
//        ClientDao.insert(c3);
//        System.out.println("recherche id 1" +ClientDao.findClientById(1));
//
//        for (Client c:
//                ClientDao.findAll()) {
//            System.out.println(c);
//
//        }
//
//        ClientDao.delete(1);
//        }

    }
}