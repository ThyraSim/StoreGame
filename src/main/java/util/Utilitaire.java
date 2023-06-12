package util;

import dao.CommandeDao;
import dao.CompteDao;
import entities.Commande;
import entities.Compte;
import entities.Jeu;

import java.util.ArrayList;
import java.util.List;

public class Utilitaire {

    public static List<Compte> findCompteByProfileName(String searchTerm){
        List<Compte> comptes = CompteDao.findAll();
        List<Compte> compteResult = new ArrayList<>();
        for (Compte compte : comptes) {
            if (compte.getProfileName().toLowerCase().contains(searchTerm.toLowerCase())) {
                compteResult.add(compte);
            }
        }
        return compteResult;
    }

    public static void addJeu(Commande commande, Jeu jeu){
        List<Jeu> jeux = new ArrayList<>();
        jeux.add(jeu);
        commande.setJeux(jeux);
        CommandeDao.update(commande);
    }

}