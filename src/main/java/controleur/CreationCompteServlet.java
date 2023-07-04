package controleur;

import dao.ClientDao;
import dao.CompteDao;
import entities.Client;
import entities.Compte;
import service.MagasinService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CreationCompteServlet", value = "/CreationCompteServlet")
public class CreationCompteServlet extends HttpServlet {

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        if (session == null) {
            response.sendRedirect("http://localhost:82/error.html");
        }

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String profileName = request.getParameter("profileName");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String physique = request.getParameter("physique");
        String email = request.getParameter("email");

        // Vérifier si le compte existe déjà
        if (CompteDao.findByUsername(username) != null) {
            // Compte déjà existant, renvoyer l'utilisateur vers la page de création du compte avec un message d'erreur
            request.setAttribute("errorMessage", "Ce nom d'utilisateur est déjà utilisé. Veuillez choisir un autre nom.");
            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
            return; // Arrêter l'exécution du code pour éviter l'insertion du compte en double
        }

        // Valider les entrées (à implémenter)

        Compte compte = new Compte(username, password, profileName);
        CompteDao.insert(compte);

        Client client = new Client(nom, prenom, physique, email, compte);
        ClientDao.insert(client);

        List<Compte> comptes = MagasinService.getListCompte(session);
        comptes.add(compte);

        session.setAttribute("ListeComptes", comptes);

        if(compte != null) {
            // Si la création a réussi, vous pouvez rediriger l'utilisateur vers la page de connexion ou vers une autre page.
            response.sendRedirect("ProfileServlet?idCompte="+compte.getIdCompte());
        } else {
            // Si la création du compte échoue, vous pouvez renvoyer l'utilisateur vers la page de création du compte et afficher un message d'erreur.
            request.setAttribute("errorMessage", "Erreur lors de la création du compte. Veuillez réessayer.");
            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
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
