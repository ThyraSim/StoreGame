package controleur;

import dao.CommandeDao;
import dao.CompteDao;
import dao.GenreDao;
import dao.JeuDao;
import entities.*;


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

        //Récupération de tous les comptes
        List<Compte> comptes = (List<Compte>) session.getAttribute("ListeComptes");

        //Pour la première ouverture
        if(comptes == null){
            comptes = CompteDao.findAll();
            session.setAttribute("ListeComptes", comptes);


        }



        //Récupération du code connecté
        Compte compte = (Compte) session.getAttribute("loggedInAccount");

        boolean check = false;
        //on récupère le catalog de jeu
        List<Jeu> catalog = (List<Jeu>) session.getAttribute("ListeJeux");
        if(catalog == null){ //Génère le catalog à la première ouverture
            catalog = JeuDao.findAll();
        }

        //on récupère la liste des genre
        List<Genre> genreList = (List<Genre>) session.getAttribute("genreList");
        if (genreList == null) {
            //génère la liste des genre à la première ouverture
            genreList =  GenreDao.findAll();
        }
        session.setAttribute("genreList",genreList);


        // on détermine le prix maximun pour les fournchettes de prix pour le filtre

        Double maxPrice = (Double) session.getAttribute("maxPrice");
        if (maxPrice == null) {
            maxPrice = 0.0;
            for (Jeu jeu : catalog) {
                if (jeu.getPrix() > maxPrice) {
                    maxPrice = jeu.getPrix();
                }
            }
            session.setAttribute("maxPrice", maxPrice);
        }
        request.setAttribute("maxPrice", maxPrice);



        List<Jeu> owned = new ArrayList<>();
        Commande panier = new Commande();
        if(compte != null){
            List<Commande> commandes = compte.getCommandes();
            for(Commande commande : commandes){
                if(!commande.isPanier()){
                    for (Jeu jeu : commande.getJeux()) {
                        if (catalog.contains(jeu)) {
                            catalog.remove(jeu);
                            owned.add(jeu);
                        }
                    }
                }
            }
            panier = compte.trouvePanier();
            if(panier == null){
                panier = new Commande(compte, true);
                compte.createPanier(panier);
                CommandeDao.insert(panier);
            }
        }
        if (action != null && action.equals("DELETE")) {
            int idJeu = Integer.parseInt(request.getParameter("idJeu"));
            panier.removeJeu(idJeu);
            compte.updateCommande(panier);
        }
        if (action != null && action.equals("ACHETE")) {
            String index = request.getParameter("index");
            if (compte == null) {
                // User is not logged in, redirect to Log in Servlet
                response.sendRedirect("LoginServlet?index=" + index);
                return;
            }
            //Ajouter au panier

            Jeu monJeu = JeuDao.findJeuById(Integer.parseInt(index));
            if(!panier.getJeux().contains(monJeu)){
                panier.addJeu(monJeu);
                compte.updateCommande(panier);
            }
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

        boolean noOwned = true;
        for(Jeu jeu:owned){
            if(panier.getJeux().contains(jeu)){
                noOwned = false;
            }
        }

        request.setAttribute("noOwned", noOwned);
        System.out.println("Chat");
        System.out.println(panier);
        session.setAttribute("panier", panier);
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

