package controleur;

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

@WebServlet(name = "DeleteServlet", value = "/DeleteServlet")
public class DeleteServlet extends HttpServlet {

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

        int idJeu = Integer.parseInt(request.getParameter("idJeu"));
        panier.removeJeu(idJeu);
        compte.updateCommande(panier);

        session.setAttribute("panier", panier);

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
