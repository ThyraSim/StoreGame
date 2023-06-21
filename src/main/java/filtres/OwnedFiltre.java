package filtres;

import dao.CommandeDao;
import dao.CompteDao;
import dao.JeuDao;
import entities.Commande;
import entities.Compte;
import entities.Jeu;
import org.xml.sax.XMLFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(value = "/MagasinServlet", filterName = "OwnedFiltre", dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class OwnedFiltre implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(); // Pass "false" to avoid creating a new session if it doesn't exist

        if (session == null) {
            httpResponse.sendRedirect("http://localhost:82/error.html");
            return;
        }

        // Retrieving the connected user code
        Compte compte = (Compte) session.getAttribute("loggedInAccount");
        List<Jeu> catalog = null;

        if (session.getAttribute("ListeJeux") != null) {
            catalog = new ArrayList<>((List<Jeu>) session.getAttribute("ListeJeux"));
        }

        if (catalog == null) {
            catalog = JeuDao.findAll();
            session.setAttribute("ListeJeux", catalog);
        }

        // Retrieving the game catalog
        if(compte != null){
            List<Jeu> owned = new ArrayList<>();
            List<Commande> commandes = compte.getCommandes();
            for(Commande commande : commandes){
                if(!commande.isPanier()){
                    for (Jeu jeu : commande.getJeux()) {
                        if (catalog.contains(jeu)) {
                            catalog.remove(jeu);
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
