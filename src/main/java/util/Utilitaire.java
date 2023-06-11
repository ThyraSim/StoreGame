package util;

import dao.CompteDao;
import entities.Compte;

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

}