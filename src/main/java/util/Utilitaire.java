package util;

import entities.Commande;
import entities.Compte;
import entities.Jeu;

import java.util.ArrayList;
import java.util.List;

public class Utilitaire {

    public static List<Compte> findCompteByProfileName(String searchTerm, List<Compte> comptes, Compte compteLog, Commande panier){
        List<Compte> compteResult = new ArrayList<>();
        if(searchTerm == null){
            searchTerm = "";
        }
        for (Compte compte : comptes) {
            if (compte.getProfileName().toLowerCase().contains(searchTerm.toLowerCase()) && !compte.getProfileName().equals(compteLog.getProfileName())) {
                boolean check = false;
                compteResult.add(compte);
                List<Commande> commandesOwned = compte.getCommandes();
                for(Commande commande: commandesOwned){
                    for(Jeu jeu: commande.getJeux()){
                        if(panier.getJeux().contains(jeu)){
                            compteResult.remove(compte);
                            check = true;
                            break;
                        }
                    }
                    if(check){
                        break;
                    }
                }
            }
        }
        return compteResult;
    }
}