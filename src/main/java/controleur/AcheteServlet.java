package controleur;

import dao.CommandeDao;
import dao.JeuDao;
import entities.Commande;
import entities.Compte;
import entities.Jeu;
import service.MagasinService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AcheteServlet", value = "/AcheteServlet")
public class AcheteServlet extends HttpServlet {

    public void init() {
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        if (session == null) {
            response.sendRedirect("http://localhost:82/error.html");
        }

        //Récupération du compte de l'utilisateur
        Compte compte = MagasinService.getCompte(session);

        String index = request.getParameter("index");
        if (compte == null) {
            // L'utilisateur n'est pas connecté, envoie au login
            response.sendRedirect("LoginServlet?index=" + index);
            return;
        }

        //Trouve le jeu selon l'index
        Jeu monJeu = JeuDao.findJeuById(Integer.parseInt(index));

        //Récupération, création et remplissage de panier
        managePanier(session, compte, monJeu);

        RequestDispatcher rd = request.getRequestDispatcher("/MagasinServlet");
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
     * Cette fonction s'occupe de trouver le panier s'il existe et le créer s'il n'existe pas
     * Elle va ensuite vérifier que le jeu n'est pas déjà dans le panier
     * Le jeu sera ensuite ajouté au panier
     * @param session
     * @param compte
     * @param monJeu
     */
    private void managePanier(HttpSession session, Compte compte, Jeu monJeu){
        Commande panier = MagasinService.getPanier(session);
        if(panier == null){
            panier = compte.trouvePanier();
            if(panier == null){
                panier = new Commande(compte, true);
                compte.createPanier(panier);
                CommandeDao.insert(panier);
            }
        }
        if(!panier.getJeux().contains(monJeu)){
            panier.addJeu(monJeu);
            compte.updateCommande(panier);
        }
        session.setAttribute("panier", panier);
    }
}
