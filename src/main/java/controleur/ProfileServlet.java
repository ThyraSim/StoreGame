package controleur;

import dao.CompteDao;
import entities.Commande;
import entities.Compte;
import entities.Jeu;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProfileServlet", value = "/ProfileServlet")
public class ProfileServlet extends HttpServlet {

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//
//        Compte compte = new Compte();
//        compte.setProfileName(request.getParameter("profileName"));
//        //bibliotheque.setCompte(request.getParameter("user"));
//
//        //récupère le compte id
////       int compteiD = request.getParameter()
//        /* en attendant que la récup id soit fonctionnel*/
//        int compteiD = 1;
//
//        compte = CompteDao.findCompteById(compteiD);
//
//        // on récupère la liste de toutes les commandes passé
//        List<Commande> listCommandePasse = compte.getCommande();
//
//        //déclare la list qui va contenir les jeu des commandes passé
//        List<Jeu> listeJeuOwned = new ArrayList<>();
//
//        // on parcourt les listes pour récupérer les jeux
//
//        for (Commande commande : listCommandePasse
//        ) {
//            for (Jeu jeu : commande.getJeux()
//            ) {
//                listeJeuOwned.add(jeu);
//            }
//        }
//
//        // donne attribut listeJeuOwned
//        request.setAttribute("listeJeuOwned", listeJeuOwned);
//
//
//        //routage
//        String url = "profile.jsp";
//        RequestDispatcher rd = request.getRequestDispatcher(url);
//        try {
//            rd.forward(request, response);
//        } catch (ServletException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
        int idCompte = Integer.parseInt(request.getParameter("idCompte"));

        // Charger le compte depuis la BD
        Compte compte = CompteDao.findCompteById(idCompte);

        // Charger les commandes du compte
        List<Commande> commandes = compte.getCommande();
        System.out.println("chat");
        System.out.println(compte);
        request.setAttribute("compte", compte);

        request.setAttribute("commandes", commandes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    public void destroy() {
    }
}