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

@WebServlet(name = "ModifierCompteServlet", value = "/ModifierCompteServlet")
public class ModifierCompteServlet extends HttpServlet {

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        if (session == null) {
            response.sendRedirect("http://localhost:82/error.html");
        }

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");


       //récupère les paramètre du formulaire
        //Compte
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String profileName = request.getParameter("profileName");

        //Client
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String physique = request.getParameter("physique");
        String email = request.getParameter("email");


        Compte newCompte = new Compte(username, password, profileName);
        Compte oldCompte = MagasinService.getCompte(session);

        //On récupère l'ancien id pour le mettre au nouveau compte
        newCompte.setIdCompte(oldCompte.getIdCompte());
        //on va transféfer l'historique de commande au nouveau compte
        newCompte.setCommande(oldCompte.getCommandes());

        //on fais la mise-à-jour de la base de données
        CompteDao.update(newCompte);

        // On créer un nouveau client et on récupère le id de l'ancien
        Client newClient = new Client(nom, prenom, physique, email, newCompte);
        Client oldClient = MagasinService.getClient(newCompte);
        newClient.setIdClient(oldClient.getIdClient());
        //on fais la mise-à-jour de la base de données
        ClientDao.update(newClient);

        // on récupère la liste de comptes ( utiliser dans loginServlet  pour validation)
        List<Compte> comptes = MagasinService.getListCompte(session);

        // on retire l'ancien compte de la liste
        for(Compte compte : comptes){
            if(compte.getIdCompte() == oldCompte.getIdCompte()){
                comptes.remove(compte);
                break;
            }
        }

        //on ajoute le nouveau compte à la liste
        comptes.add(newCompte);

        //on met à jour les attribut de session
        session.setAttribute("ListeComptes", comptes);
        session.setAttribute("client", newClient);
        session.setAttribute("loggedInAccount", newCompte);

        if(newCompte != null) {
            // Si la création a réussi, vous pouvez rediriger l'utilisateur vers la page de connexion ou vers une autre page.
            response.sendRedirect("ProfileServlet?idCompte="+newCompte.getIdCompte());
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
