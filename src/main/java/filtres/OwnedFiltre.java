package filtres;

import dao.JeuDao;
import entities.Commande;
import entities.Compte;
import entities.Jeu;
import service.MagasinService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(value = { "/MagasinServlet", "/ProfileServlet" }, filterName = "OwnedFiltre", dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class OwnedFiltre implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        //Création de response et request de type http pour des manipulations plus faciles
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        //Récupération de la session, création au besoin
        HttpSession session = httpRequest.getSession();

        if (session == null) {
            httpResponse.sendRedirect("http://localhost:8080/Init");
            return;
        }

        //Récupère le compte connecté (s'il y a lieu
        Compte compte = MagasinService.getCompte(session);

        //Création de la liste de jeux
        List<Jeu> catalog = MagasinService.getCompleteList(session);


        //Parcourt L'historique des commandes du compte, en excluant le panier, va retirer les jeux du catalog
        // et ajouter à la listedes jeu Owned
        String action = request.getParameter("action");



        if (compte != null) {
            List<Jeu> owned = new ArrayList<>();
            List<Commande> commandes = compte.getCommandes();
            for (Commande commande : commandes) {
                if (!commande.isPanier()) {  // exclu le panier
                    for (Jeu jeu : commande.getJeux()) {
                        if (catalog.contains(jeu)) {
                            owned.add(jeu);
                        }
                    }
                }
            }
            session.setAttribute("owned", owned);
        }
        session.setAttribute("catalog", catalog);

        // Call the next filter or the servlet in the chain
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
