package controleur;

import dao.CompteDao;
import entities.Commande;
import entities.Compte;
import entities.Jeu;
import service.MagasinService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "ProfileServlet", value = "/ProfileServlet")
public class ProfileServlet extends HttpServlet {

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        Compte compte = MagasinService.getCompte(session);
        if(compte == null){
            response.sendRedirect("LoginServlet");
            return;
        }

        // Charger les commandes du compte
        List<Commande> commandes = new ArrayList<>(compte.getCommandes());
        List<Commande> commandesToRemove = new ArrayList<>();
        for (Commande commande : commandes) {
            if (commande.isPanier()) {
                commandesToRemove.add(commande);
            }
        }

        commandes.removeAll(commandesToRemove);
        request.setAttribute("commandes", commandes);


        request.setAttribute("compte", compte);
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