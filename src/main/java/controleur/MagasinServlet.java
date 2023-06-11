package controleur;

import dao.CommandeDao;
import dao.CompteDao;
import dao.JeuDao;
import entities.Commande;
import entities.Compte;
import entities.Jeu;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MagasinServlet", value = "/MagasinServlet")
public class MagasinServlet extends HttpServlet {
    private String message;

    public void init() {
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session == null) {
            response.sendRedirect("http://localhost:82/error.html");
        }
        String action = request.getParameter("action");
        Compte compte = CompteDao.findCompteById(1);
        request.setAttribute("compteId", compte.getIdCompte());

        //Self checkout
        if (action != null && action.equals("SELF")) {
            CommandeDao.changePanier(compte.getIdCompte());
        }

        //Gift
        if (action != null && action.equals("GIFT")){
            String giftIdString = request.getParameter("giftId");
            if(giftIdString == null){ //Bouton Acheter pour un ami de la page magasin.jsp
                String url = "chooseFriend.jsp";
                RequestDispatcher rd = request.getRequestDispatcher(url);
                try {
                    rd.forward(request, response);
                } catch (ServletException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
            else{ //Bouton Choisir de la page chooseFriend.jsp
                CommandeDao.changePanierGift(compte.getIdCompte(), Integer.parseInt(giftIdString));
            }
        }

        boolean check = false;
        //on génère le catalog de jeu
        List<Jeu> catalog;
        List<Jeu> owned = new ArrayList<>();
        catalog = JeuDao.findAll();

        List<Commande> commandes = compte.getCommande();
        for(Commande commande : commandes){
            for (Jeu jeu : commande.getJeux()) {
                if (catalog.contains(jeu)) {
                    catalog.remove(jeu);
                    if(check == true){
                        owned.add(jeu);
                    }
                }
            }
        }

        request.setAttribute("catalog", catalog);
        request.setAttribute("owned", owned);

        // AJOUTER JEU AU PANIER

        //Ajouter au panier si action = ACHETE,

        Commande panier = compte.trouvePanier();
        if(panier == null){
            panier = new Commande(compte, true);
            CommandeDao.insert(panier);

        }
        if (action != null && action.equals("DELETE")) {
            int idJeu = Integer.parseInt(request.getParameter("idJeu"));
            panier.removeJeu(idJeu);
        }
        if (action != null && action.equals("ACHETE")) {
            //Ajouter au panier
            String index = request.getParameter("index");

            Jeu monJeu = JeuDao.findJeuById(Integer.parseInt(index));
            panier.addJeu(monJeu);
        }
        if(!panier.getJeux().isEmpty()){
            request.setAttribute("listeJeux",panier.getJeux());
            for (Jeu jeu : panier.getJeux()) {
                if (catalog.contains(jeu)) {
                    catalog.remove(jeu);
                }
            }
        }
        Boolean noOwned = true;
        request.setAttribute("noOwned", noOwned);
        String url = "magasin.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(url);
        try {
            rd.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void destroy() {
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
}

