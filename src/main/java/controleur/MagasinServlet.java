package controleur;

import dao.BibliothequeDao;
import dao.CompteDao;
import dao.JeuDao;
import entities.Bibliotheque;
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
    private JeuDao jeuDao;

    public void init() {


    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Jeu> catalog;
        catalog = jeuDao.findAll();
        request.setAttribute("catalog", catalog);
        Compte compte = CompteDao.findCompteById(2);
        Boolean check = true;

        List<Jeu> panier = null;
        List<Bibliotheque> bibliotheques = BibliothequeDao.findBibliothequesByCompteId(2);
        for (Bibliotheque bibliotheque : bibliotheques) {
            Jeu jeu = bibliotheque.getJeu();
            if (catalog.contains(jeu)) {
                catalog.remove(jeu);
            }
        }

        // AJOUTER JEU AU PANIER

        String action = request.getParameter("action");

        if (action.equals("ACHETE")) {
            //Ajouter au panier


            if (panier == null) {
                panier = new ArrayList<>();

            }

            String index = request.getParameter("index");

            Jeu monJeu = jeuDao.findJeuById(Integer.parseInt(index));
            panier.add(monJeu);


            request.setAttribute("ajoutJeu", monJeu);
            request.setAttribute("panier",panier);
        }

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
}
