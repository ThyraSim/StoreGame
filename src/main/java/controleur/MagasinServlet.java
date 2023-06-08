package controleur;

import dao.CompteDao;
import dao.JeuDao;
import dao.LigneCommandeDao;
import entities.Jeu;
import entities.LigneCommande;

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
            LigneCommandeDao.changePanier(compteId);
        }
        if (action != null && action.equals("GIFT")){
            LigneCommandeDao.changePanierGift(compteId, 2);
        }
        boolean check = false;
        //on génère le catalog de jeu
        List<Jeu> catalog;
        List<Jeu> owned = new ArrayList<>();
        catalog = JeuDao.findAll();

        List<LigneCommande> ligneCommandeList = LigneCommandeDao.findLignesCommandeByCompteId(1);
        for (LigneCommande ligneCommande : ligneCommandeList) {
            Jeu jeu = ligneCommande.getJeu();
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
            int idLigne = Integer.parseInt(request.getParameter("idLigne"));
            LigneCommandeDao.delete(idLigne);
        }
        List<LigneCommande> panier = LigneCommandeDao.findPanierByCompteId(1);
        if (action != null && action.equals("ACHETE")) {
            //Ajouter au panier

            String index = request.getParameter("index");

            Jeu monJeu = JeuDao.findJeuById(Integer.parseInt(index));
            LigneCommande tempCommande = new LigneCommande(monJeu, CompteDao.findCompteById(1), false, true);
            panier.add(tempCommande);


            //request.setAttribute("ajoutJeu", monJeu);
            LigneCommandeDao.insert(tempCommande);
        }
        request.setAttribute("panier",panier);
        if(!panier.isEmpty()){
            for (LigneCommande ligneCommande : panier) {
                Jeu jeu = ligneCommande.getJeu();
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

