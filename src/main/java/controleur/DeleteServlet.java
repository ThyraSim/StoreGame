package controleur;

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
import java.net.http.HttpRequest;

@WebServlet(name = "DeleteServlet", value = "/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    private static String url;
    public void init() {
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        if (session == null) {
            response.sendRedirect("http://localhost:82/error.html");
        }
        url = "panier.jsp";

        //Retire le jeu du panier
        retirerJeu(session, request);
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

    /**
     * Retire le jeu du panier et rafraîchi l'attribut panier
     * @param session
     * @param request
     */
    private void retirerJeu(HttpSession session, HttpServletRequest request){
        Compte compte = MagasinService.getCompte(session);
        Commande panier = MagasinService.getPanier(session);

        int idJeu = Integer.parseInt(request.getParameter("idJeu"));
        panier.removeJeu(idJeu);
        compte.updateCommande(panier);

        session.setAttribute("panier", panier);
        if(panier.getJeux().isEmpty() || request.getParameter("source").equals("nav")){
            url = "MagasinServlet";
        }
    }
}
