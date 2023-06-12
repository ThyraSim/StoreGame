package controleur;

import dao.CommandeDao;
import dao.CompteDao;
import dao.JeuDao;
import entities.Commande;
import entities.Compte;
import entities.Jeu;

public class Main {
    public static void main(String[] args){
        JeuDao.insert(new Jeu("Chat", 29.99, "Action", "Le moyen de tout les temps"));
        JeuDao.insert(new Jeu("Jeu de id 2",79.99,"plateforme","Mario Bros 1"));
        JeuDao.insert(new Jeu("Jeu de id 3",79.99,"plateforme","Mario Bros 2"));
        JeuDao.insert(new Jeu("Jeu de id 4",79.99,"plateforme","Mario Bros 3"));
        JeuDao.insert(new Jeu("jeu de id 5",79.99,"plateforme","Mario Bros 4"));
        JeuDao.insert(new Jeu("Jeu de id 6",79.99,"plateforme","Mario Bros 1"));
        JeuDao.insert(new Jeu("Jeu de id 7",79.99,"plateforme","Mario Bros 2"));
        JeuDao.insert(new Jeu("Jeu de id 8",79.99,"plateforme","Mario Bros 3"));
        JeuDao.insert(new Jeu("jeu de id 9",79.99,"plateforme","Mario Bros 4"));
        JeuDao.insert(new Jeu("Jeu de id 10",79.99,"plateforme","Mario Bros 1"));
        JeuDao.insert(new Jeu("Jeu de id 11",79.99,"plateforme","Mario Bros 2"));
        JeuDao.insert(new Jeu("Jeu de id 12",79.99,"plateforme","Mario Bros 3"));
        JeuDao.insert(new Jeu("jeu de id 13",79.99,"plateforme","Mario Bros 4"));
        CompteDao.insert(new Compte("THEFRIEND", "Chapeau", "Orignal"));
        CompteDao.insert(new Compte("theActualFriend", "fromage", "DaFriend"));

//        CommandeDao.insert(new Commande(CompteDao.findCompteById(1), false));
//          Compte compte = CompteDao.findCompteById(1);
//          Commande commande = compte.getCommande().get(0);
//          commande.addJeu(JeuDao.findJeuById(1));

//        JeuDao.delete(2);
//        CompteDao.delete(1);
//        LigneCommandeDao.delete(5);
//        JeuDao.afficherListeJeux();
//        CompteDao.afficherListeComptes();
//        BibliothequeDao.afficherListeBibliotheques();
//        Commande commande = CommandeDao.findCommandeById(1);
//        Jeu jeu = JeuDao.findJeuById(2);
//        commande.addJeu(jeu);
//        commande.removeJeu(1);



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