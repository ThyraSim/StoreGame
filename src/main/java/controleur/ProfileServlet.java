package controleur;

import dao.CompteDao;
import entities.Commande;
import entities.Compte;
import entities.Jeu;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProfileServlet", value = "/ProfileServlet")
public class ProfileServlet extends HttpServlet {

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int idCompte = Integer.parseInt(request.getParameter("idCompte"));

        // Charger le compte depuis la BD
        Compte compte = CompteDao.findCompteById(idCompte);

        // Charger les commandes du compte
        List<Commande> commandes = compte.getCommandes();
        System.out.println("chat");
        System.out.println(compte);
        request.setAttribute("compte", compte);

        request.setAttribute("commandes", commandes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
        dispatcher.forward(request, response);
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

    public void destroy() {
    }
}