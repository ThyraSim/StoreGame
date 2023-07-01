package controleur;

import entities.*;
import service.MagasinService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MagasinServlet", value = "/MagasinServlet")
public class MagasinServlet extends HttpServlet {
    private String message;

    public void init() {
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        if (session == null) {
            response.sendRedirect("http://localhost:82/error.html");
        }


        //On récupère la liste des genre
        List<Genre> genreList = MagasinService.getGenres(session);

        //Récupération du catalog (Les jeux à afficher)
        List<Jeu> catalog = MagasinService.getCatalog(session);

        String selectedCurrency = (String) session.getAttribute("selectedCurrency");
        catalog = convertPrices(catalog, selectedCurrency);

        //On détermine le prix maximun pour les fournchettes de prix pour le filtre
        Double maxPrice = getMaxPrix(catalog);

        //Détermine si un produit du panier est possédé par l'utilisateur
        boolean noOwned = getNoOwned(session);

        session.setAttribute("noOwned", noOwned);
        request.setAttribute("maxPrice", maxPrice);
        String url = "magasin.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(url);
        try {
            rd.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void destroy() {
    }
    private List<Jeu> convertPrices(List<Jeu> catalog, String selectedCurrency) {
        // This is where you would convert the prices of each game in the catalog based on the selected currency.
        // For the sake of this example, we'll assume that the conversion rate from USD to EUR is 0.85.
        if (selectedCurrency == null) {
            selectedCurrency = "USD";
        }
        double conversionRate = selectedCurrency.equals("EUR") ? 0.85 : 1.0;

        for (Jeu jeu : catalog) {
            double originalPrice = jeu.getPrix();
            double convertedPrice = originalPrice * conversionRate;
            jeu.setPrix(convertedPrice);

            // Print original and converted prices
            System.out.println("Original price: " + originalPrice + ", Converted price: " + convertedPrice);
        }

        return catalog;
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


    /**
     * Vérifie si un produit du panier est aussi présent dans la liste des jeux possédés de l'utilisateur
     * Cette donnée servira à désactiver le bouton de self checkout
     * @param session
     * @return noOwned
     */
    private boolean getNoOwned(HttpSession session){
        List<Jeu> owned = MagasinService.getOwned(session);
        Commande panier = MagasinService.getPanier(session);
        if(owned != null){
            for(Jeu jeu:owned){
                if(panier.getJeux().contains(jeu)){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Retourne le prix du jeu étant le plus dispendieux
     * Cette donnée servira au filtre par prix dans magasin.jsp
     * @param catalog
     * @return maxPrice
     */
    private double getMaxPrix(List<Jeu> catalog){
        Double maxPrice = 0.0;
        for (Jeu jeu : catalog) {
            if (jeu.getPrix() > maxPrice) {
                maxPrice = jeu.getPrix();
            }
        }
        return maxPrice;
    }
}

