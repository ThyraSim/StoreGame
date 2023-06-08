package config;


import dao.*;
import entities.*;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;


public class ConfigStartServlet extends HttpServlet {

    public void init() throws ServletException {
        JeuDao.insert(new Jeu("Mario", 79.99, "plateforme", "Mario Bros 1"));
        JeuDao.insert(new Jeu("Mario2", 79.99, "plateforme", "Mario Bros 2"));
        JeuDao.insert(new Jeu("Mario3", 79.99, "plateforme", "Mario Bros 3"));
        JeuDao.insert(new Jeu("Mario4", 79.99, "plateforme", "Mario Bros 4"));


        JeuDao.insert(new Jeu("Chat", 29.99, "Action", "Le moyen de tout les temps"));

        CompteDao.insert(new Compte("THEFRIEND", "Chapeau", "Orignal"));

        ClientDao.insert( new Client("Tremblay1", "bob", "11 rue la marche", "123@chat"));
        ClientDao.insert( new Client("Tremblay1", "bob", "11 rue la marche", "123@chat"));

        CommandeDao.insert(new Commande(CompteDao.findCompteById(1), false));
        CommandeDao.insert(new Commande(CompteDao.findCompteById(1), true));
        LigneCommandeDao.insert(new LigneCommande(JeuDao.findJeuById(2), CommandeDao.findCommandeById(2), false));
        LigneCommandeDao.insert(new LigneCommande(JeuDao.findJeuById(3), CommandeDao.findCommandeById(2), false));

        LigneCommandeDao.insert(new LigneCommande(JeuDao.findJeuById(1), CommandeDao.findCommandeById(1), false));


    }
}








