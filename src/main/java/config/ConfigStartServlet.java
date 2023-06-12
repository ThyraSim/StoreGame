package config;


import dao.*;
import entities.*;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;


public class ConfigStartServlet extends HttpServlet {

    public void init() throws ServletException {

        if (JeuDao.findAll().isEmpty() && CommandeDao.findAll().isEmpty() && ClientDao.findAll().isEmpty() && CompteDao.findAll().isEmpty()) {

            Client client1 = new Client("Tremblay1", "bob", "11 rue la marche", "123@chat");
            Client client2 = new Client("Tremblay1", "Simon", "11 rue le foie", "321@chien");

            CompteDao.insert(new Compte("THEFRIEND", "Chapeau", "Orignal"));
            CompteDao.insert(new Compte("admin", "admin", "admin"));

            client1.setCompte(CompteDao.findCompteById(1));
            client1.setCompte(CompteDao.findCompteById(2));

            ClientDao.insert(client1);
            ClientDao.insert(client2);



            JeuDao.insert(new Jeu("Mario", 79.99, "plateforme", "Mario Bros 1"));
            JeuDao.insert(new Jeu("Zelda", 79.99, "Aventure", "Zelda Breath of the wild"));
            JeuDao.insert(new Jeu("Mario3", 79.99, "plateforme", "Mario Bros 3"));
            JeuDao.insert(new Jeu("Mario4", 79.99, "plateforme", "Mario Bros 4"));


            JeuDao.insert(new Jeu("Chat", 29.99, "Action", "Le moyen de tout les temps"));





            Commande commande1 = new Commande(CompteDao.findCompteById(1), false);
            commande1.addJeu(JeuDao.findJeuById(1));
            commande1.addJeu(JeuDao.findJeuById(2));
            commande1.addJeu(JeuDao.findJeuById(3));

            Commande commande2 = new Commande(CompteDao.findCompteById(1), true);
            CommandeDao.insert(commande2);
            commande2.addJeu(JeuDao.findJeuById(4));

            Commande commande3 = new Commande(CompteDao.findCompteById(2), false);
            CommandeDao.insert(commande3);
            commande3.addJeu(JeuDao.findJeuById(1));
            commande3.addJeu(JeuDao.findJeuById(2));
            commande3.addJeu(JeuDao.findJeuById(3));

            Commande commande4 = new Commande(CompteDao.findCompteById(2), true);
            CommandeDao.insert(commande4);
            commande4.addJeu(JeuDao.findJeuById(4));






        }
    }
}








