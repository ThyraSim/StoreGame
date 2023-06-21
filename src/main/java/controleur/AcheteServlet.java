package controleur;

import dao.CommandeDao;
import dao.JeuDao;
import entities.Commande;
import entities.Compte;
import entities.Jeu;

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
        HttpSession session = request.getSession();
        if (session == null) {
            response.sendRedirect("http://localhost:82/error.html");
        }

        Compte compte = (Compte) session.getAttribute("loggedInAccount");
        Commande panier = (Commande) session.getAttribute("panier");

        String index = request.getParameter("index");
        if (compte == null) {
            // User is not logged in, redirect to Log in Servlet
            response.sendRedirect("LoginServlet?index=" + index);
            return;
        }
        //Ajouter au panier
        Jeu monJeu = JeuDao.findJeuById(Integer.parseInt(index));
        if(panier == null){
            panier = new Commande();
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
}
