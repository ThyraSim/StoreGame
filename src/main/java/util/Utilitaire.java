package util;

import dao.CommandeDao;
import dao.CompteDao;
import entities.Commande;
import entities.Compte;
import entities.Jeu;

import java.util.ArrayList;
import java.util.List;

public class Utilitaire {

    public static List<Compte> findCompteByProfileName(String searchTerm, List<Compte> comptes, Compte compteLog){
        List<Compte> compteResult = new ArrayList<>();
        for (Compte compte : comptes) {
            if (compte.getProfileName().toLowerCase().contains(searchTerm.toLowerCase()) && !compte.getProfileName().equals(compteLog.getProfileName())) {
                compteResult.add(compte);
            }
        }
        return compteResult;
    }
}