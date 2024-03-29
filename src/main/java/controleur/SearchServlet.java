package controleur;

import entities.Commande;
import entities.Compte;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

import service.MagasinService;
import util.Utilitaire;

@WebServlet(name = "SearchServlet", value = "/SearchServlet")
public class SearchServlet extends HttpServlet {
        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            String searchTerm = request.getParameter("searchInput");
            HttpSession session = request.getSession();
            List<Compte> comptes = MagasinService.getListCompte(session);
            Compte compte = MagasinService.getCompte(session);
            Commande panier = MagasinService.getPanier(session);
            List<Compte> compteResult = Utilitaire.findCompteByProfileName(searchTerm, comptes, compte, panier);
            request.setAttribute("compteResult", compteResult);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/chooseFriend.jsp");
            try {
                rd.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
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