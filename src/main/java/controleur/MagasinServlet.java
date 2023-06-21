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

        //Récupération de tous les comptes
        List<Compte> comptes = (List<Compte>) session.getAttribute("ListeComptes");

        //Pour la première ouverture
        if(comptes == null){
            comptes = CompteDao.findAll();
            session.setAttribute("ListeComptes", comptes);
        }

        //On récupère la liste des genre
        List<Genre> genreList = (List<Genre>) session.getAttribute("genreList");
        if (genreList == null) {
            //génère la liste des genre à la première ouverture
            genreList =  GenreDao.findAll();
        }
        session.setAttribute("genreList",genreList);
        List<Jeu> catalog = (List<Jeu>) session.getAttribute("catalog");


        List<Jeu> owned = (List<Jeu>) session.getAttribute("owned");
        Commande panier = (Commande) session.getAttribute("panier");

        // on détermine le prix maximun pour les fournchettes de prix pour le filtre
        Double maxPrice = 0.0;
        for (Jeu jeu : catalog) {
            if (jeu.getPrix() > maxPrice) {
                maxPrice = jeu.getPrix();
            }
        }

        boolean noOwned = true;
        if(owned != null){
            for(Jeu jeu:owned){
                if(panier.getJeux().contains(jeu)){
                    noOwned = false;
                }
            }
        }


        request.setAttribute("noOwned", noOwned);
        request.setAttribute("maxPrice", maxPrice);
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

