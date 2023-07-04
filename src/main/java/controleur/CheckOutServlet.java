package controleur;

import dao.CommandeDao;
import dao.CompteDao;
import dao.JeuDao;
import entities.Commande;
import entities.Compte;
import entities.Jeu;
import service.MagasinService;

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
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        if (session == null) {
            response.sendRedirect("http://localhost:82/error.html");
        }

        Commande panier = MagasinService.getPanier(session);

        //Récupération de l'action de la requête
        String action = request.getParameter("action");

        //Récupération du code connecté
        Compte compte = MagasinService.getCompte(session);

        //Self checkout
        if (action != null && action.equals("SELF")) {
             selfCheckOut(compte);
        }
        //Gift
        if (action != null && action.equals("GIFT")){
            String giftIdString = request.getParameter("giftId");
            if(giftIdString == null){ //Bouton Acheter pour un ami de la page magasin.jsp
                String url = "WEB-INF/chooseFriend.jsp";
                RequestDispatcher rd = request.getRequestDispatcher(url);
                try {
                    rd.forward(request, response);
                } catch (ServletException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                return;
            }
            else{ //Bouton Choisir de la page chooseFriend.jsp
                giftCheckout(compte, panier, giftIdString, session);
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

    /**
     * Change le boolean panier du panier en false dans la bd, il ne sera donc plus un panier, il deviendra owned.
     * @param compte
     */
    private void selfCheckOut(Compte compte){
        //Change dans l'objet (application)
        setPanier(compte);

        //Change dans la db
        CommandeDao.changePanier(compte.getIdCompte());
    }

    /**
     * Change le boolean panier du panier en false dans la bd, il ne sera donc plus un panier, il deviendra owned.
     * Change aussi l'id du compte pour qu'il corresponde à celui de l'ami choisi
     * @param compte
     * @param panier
     * @param giftIdString
     */
    private void giftCheckout(Compte compte, Commande panier, String giftIdString, HttpSession session){
        //Set panier dans l'objet (application)
        setPanier(compte);

        //Set panier et commande dans la db
        CommandeDao.changePanierGift(compte.getIdCompte(), Integer.parseInt(giftIdString));

        List<Compte> newListCompte = MagasinService.getListCompte(session);
        for(Compte compteTemp:newListCompte){
            if(compteTemp.getIdCompte() == Integer.parseInt(giftIdString)){
                newListCompte.remove(compteTemp);
                newListCompte.add(CompteDao.findCompteById(Integer.parseInt(giftIdString)));
                session.setAttribute("ListeComptes", newListCompte);
                break;
            }
        }
        compte.removeCommande(panier);
    }

    /**
     * Change le boolean dans l'objet seulement et non la bd
     * @param compte
     */
    private void setPanier(Compte compte){
        for(Commande commande : compte.getCommandes()){
            if(commande.isPanier()){
                commande.setPanier(false);
            }
        }
    }
}

