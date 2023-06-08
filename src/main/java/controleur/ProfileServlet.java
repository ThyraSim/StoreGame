package controleur;

import entities.Compte;
import entities.LigneCommande;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ProfileServlet", value = "/ProfileServlet")
public class ProfileServlet extends HttpServlet {

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
//
//        // Récupérer les informations de l'utilisateur depuis une source de données
//        String userName = "John Doe";
//        String favoriteCategory = "Jeux vidéo";
//        String[] favoriteGames = {"Jeu 1", "Jeu 2", "Jeu 3", "Jeu 4"};
//
//        // Afficher le profil de l'utilisateur
//        PrintWriter out = response.getWriter();
//        out.println("<html><body>");
//        out.println("<h1>Profil utilisateur</h1>");
//        out.println("<div class=\"profile\">");
//        out.println("<div class=\"profile-image\"></div>");
//        out.println("<h2>" + userName + "</h2>");
//        out.println("</div>");
//        out.println("<h3>Catégorie favorite :</h3>");
//        out.println("<ul>");
//        for (String game : favoriteGames) {
//            out.println("<li>" + game + "</li>");
//        }
//        out.println("</ul>");
//        out.println("</body></html>");

        Compte compte = new Compte();
        compte.setProfileName(request.getParameter("profileName"));
        LigneCommande ligneBibliotheque = new LigneCommande();
        //bibliotheque.setCompte(request.getParameter("user"));

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