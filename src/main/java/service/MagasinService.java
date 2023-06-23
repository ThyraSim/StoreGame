package service;

import dao.ClientDao;
import dao.CompteDao;
import dao.GenreDao;
import dao.JeuDao;
import entities.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class MagasinService {
    /**
     * Retourne le catalog s'il existe.
     * Demande la création sinon
     * @param session
     * @return
     */
    public static List<Jeu> getCatalog(HttpSession session){
        if(session.getAttribute("catalog") != null) {
            return (List<Jeu>) session.getAttribute("catalog");
        }else {
            return createCatalog(session);
        }
    }

    /**
     * Créer l'attribut catalog s'il n'existe pas. Renvoie catalog ensuite
     * @param session
     * @return
     */
    private static List<Jeu> createCatalog(HttpSession session){
        List<Jeu> catalog = new ArrayList<>(getCompleteList(session));
        session.setAttribute("catalog", catalog);
        return catalog;
    }

    /**
     * Retourne la liste de tous les jeux s'il existe dans la session. Demande la création de la liste sinon
     * @param session
     * @return
     */
    public static List<Jeu> getCompleteList(HttpSession session){
        if(session.getAttribute("ListeJeux") != null) {
            return new ArrayList<>((List<Jeu>) session.getAttribute("ListeJeux"));
        }else {
            return createCompleteList(session);
        }
    }

    /**
     * Créer la liste de tous les jeux
     * @param session
     * @return
     */
    private static List<Jeu> createCompleteList(HttpSession session){
        List<Jeu> jeuList = JeuDao.findAll();
        session.setAttribute("ListeJeux", jeuList);
        return jeuList;
    }

    /**
     * Renvoie le compte auquel l'utilisateur est connecté
     * @param session
     * @return
     */
    public static Compte getCompte(HttpSession session){
        return (Compte) session.getAttribute("loggedInAccount");
    }

    /**
     * Renvoie la liste de tous les comptes s'il existe. Demande sa création sinon
     * @param session
     * @return
     */
    public static List<Compte> getListCompte(HttpSession session){
        if(session.getAttribute("ListeComptes") != null) {
            return (List<Compte>) session.getAttribute("ListeComptes");
        } else{
            return createListCompte(session);
        }
    }

    /**
     * Créer la liste de tous les comptes
     * @param session
     * @return
     */
    private static List<Compte> createListCompte(HttpSession session) {
        List<Compte> comptes = CompteDao.findAll();
        session.setAttribute("ListeComptes", comptes);
        return comptes;
    }

    /**
     * Renvoie la liste de tous les genres si elle existe. Demande la création sinon
     * @param session
     * @return
     */
    public static List<Genre> getGenres(HttpSession session){
        if(session.getAttribute("genreList") != null) {
            return (List<Genre>) session.getAttribute("genreList");
        } else{
            return createGenres(session);
        }
    }

    /**
     * Créer la liste de tous les genres
     * @param session
     * @return
     */
    private static List<Genre> createGenres(HttpSession session){
        List<Genre> genres = GenreDao.findAll();
        session.setAttribute("genreList", genres);
        return genres;
    }

    /**
     * Récupère la liste des jeux possédés
     * @param session
     * @return
     */
    public static List<Jeu> getOwned(HttpSession session){
        return (List<Jeu>) session.getAttribute("owned");
    }

    /**
     * Renvoie le panier
     * @param session
     * @return
     */
    public static Commande getPanier(HttpSession session){
        return (Commande) session.getAttribute("panier");
    }

    public static Client getClient(Compte compte){
        return ClientDao.findClientById(compte.getIdCompte());
    }
}
