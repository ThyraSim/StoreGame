package controleur;

import dao.CompteDao;
import entities.Compte;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        List<Compte> compteList = CompteDao.findAll();
        String index = request.getParameter("index");
        request.setAttribute("index", index);
        System.out.println("chien:");
        System.out.println(index);
        for (Compte compte : compteList) {
            if (compte.getUser().equals(username) && compte.getPassword().equals(password)) {
                // L'utilisateur est authentifié
                // Ajoutez votre logique ici pour rediriger ou effectuer d'autres opérations
                HttpSession session = request.getSession();
                session.setAttribute("loggedInAccount", compte);
                if(index != null){
                    response.sendRedirect("MagasinServlet?action=ACHETE&index=" + index);
                    return;
                } else{
                    response.sendRedirect("ProfileServlet?idCompte="+compte.getIdCompte()); // Exemple de redirection vers une page de tableau de bord

                    return;
                }
            }
        }

        // Si les informations d'identification sont incorrectes, affichez un message d'erreur ou redirigez vers une page d'erreur
        response.sendRedirect("login.jsp?index="+index);
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

