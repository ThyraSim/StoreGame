package controleur;

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

        List<Compte> compteList = findAll();

        for (Compte compte : compteList) {
            if (compte.getUser().equals(username) && compte.getPassword().equals(password)) {
                // L'utilisateur est authentifié
                // Ajoutez votre logique ici pour rediriger ou effectuer d'autres opérations
                response.sendRedirect("profile.jsp"); // Exemple de redirection vers une page de tableau de bord
                return;
            }
        }

        // Si les informations d'identification sont incorrectes, affichez un message d'erreur ou redirigez vers une page d'erreur
        response.sendRedirect("login.jsp");
    }

    private List<Compte> findAll() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("connection");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createQuery("SELECT c FROM Compte c");
        return query.getResultList();
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

