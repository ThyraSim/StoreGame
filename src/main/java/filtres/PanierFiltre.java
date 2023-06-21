package filtres;

import dao.CommandeDao;
import entities.Commande;
import entities.Compte;
import entities.Jeu;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebFilter(value = "/MagasinServlet", filterName = "PanierFiltre", dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
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

        List<Jeu> catalog = (List<Jeu>) session.getAttribute("catalog");
        Compte compte = (Compte) session.getAttribute("loggedInAccount");
        Commande panier;

        if(compte != null){
            panier = compte.trouvePanier();
            if(panier == null){
                panier = new Commande(compte, true);
                compte.createPanier(panier);
                CommandeDao.insert(panier);
            }

            if(!panier.getJeux().isEmpty()){
                request.setAttribute("listeJeux",panier.getJeux());
                for (Jeu jeu : panier.getJeux()) {
                    if (catalog.contains(jeu)) {
                        catalog.remove(jeu);
                    }
                }
            }
            session.setAttribute("panier", panier);
        }
        session.setAttribute("catalog", catalog);
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
    // PanierFiltre implementation
}

