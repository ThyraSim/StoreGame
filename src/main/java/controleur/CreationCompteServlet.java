package controleur;

import dao.CompteDao;
import entities.Commande;
import entities.Compte;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CreationCompteServlet", value = "/CreationCompteServlet")
public class CreationCompteServlet extends HttpServlet {

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String profileName = request.getParameter("profileName");

        // Valider les entrées (à implémenter)

        Compte compte = new Compte(username, password, profileName);
        CompteDao.insert(compte);

        if(compte != null) {
            // Si la création a réussi, vous pouvez rediriger l'utilisateur vers la page de connexion ou vers une autre page.
            response.sendRedirect("ProfileServlet?idCompte="+compte.getIdCompte());
        } else {
            // Si la création du compte échoue, vous pouvez renvoyer l'utilisateur vers la page de création du compte et afficher un message d'erreur.
            request.setAttribute("errorMessage", "Erreur lors de la création du compte. Veuillez réessayer.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
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
