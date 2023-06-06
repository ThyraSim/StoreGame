package controleur;

import dao.BibliothequeDao;
import dao.CompteDao;
import dao.JeuDao;
import entities.Bibliotheque;
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
//        JeuDao.insert(new Jeu("Mario",79.99,"plateforme","Mario Bros 1"));
//        JeuDao.insert(new Jeu("Mario2",79.99,"plateforme","Mario Bros 2"));
//        JeuDao.insert(new Jeu("Mario3",79.99,"plateforme","Mario Bros 3"));
//        JeuDao.insert(new Jeu("Mario4",79.99,"plateforme","Mario Bros 4"));
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session == null) {
            response.sendRedirect("http://localhost:82/error.html");
        }
        String action = request.getParameter("action");
        int compteId = 1;
        request.setAttribute("compteId", compteId);
        if (action != null && action.equals("SELF")) {
            BibliothequeDao.changePanier(compteId);
        }
        if (action != null && action.equals("GIFT")){
            BibliothequeDao.changePanierGift(compteId, 2);
        }
        boolean check = false;
        //on génère le catalog de jeu
        List<Jeu> catalog;
        List<Jeu> owned = new ArrayList<>();
        catalog = JeuDao.findAll();

        List<Bibliotheque> bibliotheques = BibliothequeDao.findBibliothequesByCompteId(1);
        for (Bibliotheque bibliotheque : bibliotheques) {
            Jeu jeu = bibliotheque.getJeu();
            if (catalog.contains(jeu)) {
                catalog.remove(jeu);
                if(check == true){
                    owned.add(jeu);
                }
            }
        }

        request.setAttribute("catalog", catalog);
        request.setAttribute("owned", owned);

        // AJOUTER JEU AU PANIER



        //Ajouter au panier si action = ACHETE,
        if (action != null && action.equals("DELETE")){
            int idBiblio = Integer.parseInt(request.getParameter("idBiblio"));
            BibliothequeDao.delete(idBiblio);
        }
        List<Bibliotheque> panier = BibliothequeDao.findPanierByCompteId(1);
        if (action != null && action.equals("ACHETE")) {
            //Ajouter au panier

            String index = request.getParameter("index");

            Jeu monJeu = JeuDao.findJeuById(Integer.parseInt(index));
            Bibliotheque tempBiblio = new Bibliotheque(monJeu, CompteDao.findCompteById(1), false, true);
            panier.add(tempBiblio);


            //request.setAttribute("ajoutJeu", monJeu);
            BibliothequeDao.insert(tempBiblio);
        }
        request.setAttribute("panier",panier);
        if(!panier.isEmpty()){
            for (Bibliotheque bibliotheque : panier) {
                Jeu jeu = bibliotheque.getJeu();
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

