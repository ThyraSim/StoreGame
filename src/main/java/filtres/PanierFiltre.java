package filtres;

import dao.CommandeDao;
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
import java.util.List;

@WebFilter(value = { "/MagasinServlet", "/ProfileServlet" }, filterName = "PanierFiltre", dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class PanierFiltre implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false); // Pass "false" to avoid creating a new session if it doesn't exist

        if (session == null) {
            httpResponse.sendRedirect("http://localhost:82/error.html");
            return;
        }

        List<Jeu> catalog = MagasinService.getCatalog(session);
        Compte compte = getCompte(session);

        //On vérifie si l'il y a un compte et on récupère le panie, le cas échéant on crée un nouveau panier

        if(compte != null){
            Commande panier = compte.trouvePanier();
            if(panier == null){
                panier = new Commande(compte, true);
                compte.createPanier(panier);
                CommandeDao.insert(panier);
            }


           //Si le panier n'est pas vide, on récupère la liste des jeux du panier et on retire du catalog "dynamique" des jeux.
            if(!panier.getJeux().isEmpty()){
                session.setAttribute("listePanier",panier.getJeux());
                for (Jeu jeu : panier.getJeux()) {
                    if (catalog.contains(jeu)) {
                        catalog.remove(jeu);
                    }
                }
            }
            session.setAttribute("panier", panier);
        }
        session.setAttribute("catalog", catalog);

        //Détermine si un produit du panier est possédé par l'utilisateur
        session.setAttribute("noOwned", MagasinService.getNoOwned(session));

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    public Compte getCompte(HttpSession session){
        return (Compte) session.getAttribute("loggedInAccount");
    }


}

