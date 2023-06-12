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

@WebServlet(name = "CheckOutServlet", value = "/CheckOutServlet")
public class CheckOutServlet extends HttpServlet {

    public void init() {
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session == null) {
            response.sendRedirect("http://localhost:82/error.html");
        }

        //Récupération de l'action de la requête
        String action = request.getParameter("action");

        //Récupération du code connecté
        Compte compte = (Compte) session.getAttribute("loggedInAccount");

        //Self checkout
        if (action != null && action.equals("SELF")) {
            CommandeDao.changePanier(compte.getIdCompte());
            for(Commande commande : compte.getCommandes()){
                if(commande.isPanier()){
                    commande.setPanier(false);
                }
            }
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
                for(Commande commande : compte.getCommandes()){
                    if(commande.isPanier()){
                        commande.setPanier(false);
                    }
                }
                CommandeDao.changePanierGift(compte.getIdCompte(), Integer.parseInt(giftIdString));
            }
        }
        String url = "MagasinServlet";
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

