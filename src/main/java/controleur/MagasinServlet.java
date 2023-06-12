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

        //Récupération de l'action de la requête
        String action = request.getParameter("action");

        //Récupération du code connecté
        Compte compte = (Compte) session.getAttribute("loggedInAccount");

        boolean check = false;
        //on génère le catalog de jeu
        List<Jeu> catalog;
//        List<Jeu> owned = new ArrayList<>();
        catalog = JeuDao.findAll();
        Commande panier = new Commande();
        if(compte != null){
            List<Commande> commandes = compte.getCommande();
            for(Commande commande : commandes){
                for (Jeu jeu : commande.getJeux()) {
                    if (catalog.contains(jeu)) {
                        catalog.remove(jeu);
//                        if(check == true){
//                            owned.add(jeu);
//                        }
                    }
                }
            }
            panier = compte.trouvePanier();
            if(panier == null){
                panier = new Commande(compte, true);
                CommandeDao.insert(panier);
            }
        }
        if (action != null && action.equals("DELETE")) {
            int idJeu = Integer.parseInt(request.getParameter("idJeu"));
            panier.removeJeu(idJeu);
        }
        if (action != null && action.equals("ACHETE")) {
            String index = request.getParameter("index");
            System.out.println("chien:");
            System.out.println(index);
            if (compte == null) {
                // User is not logged in, redirect to Log in Servlet
                response.sendRedirect("LoginServlet?index=" + index);
                return;
            }
            //Ajouter au panier

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
        request.setAttribute("catalog", catalog);
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

