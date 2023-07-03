package controleur;

import entities.*;
import service.MagasinService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String lang = (String) session.getAttribute("lang");
        request.setAttribute("lang", lang);


        //Récupération du catalog (Les jeux à afficher)
        List<Jeu> catalog = MagasinService.getCatalog(session);

        MagasinService.getGenres(session);

        //On détermine le prix maximun pour les fournchettes de prix pour le filtre
        Double maxPrice = getMaxPrix(catalog);

        request.setAttribute("maxPrice", maxPrice);
        String url = "WEB-INF/magasin.jsp";
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


    public static Commande getPanier(HttpSession session) {
        Commande panier = (Commande) session.getAttribute("panier");
        if (panier == null) {
            // Si le panier n'existe pas, créez une nouvelle instance
            panier = new Commande();
            session.setAttribute("panier", panier);
        }
        return panier;
    }

    private Map<Jeu, String> convertPrices(List<Jeu> catalog, String selectedCurrency) {
        Map<Jeu, String> convertedPrices = new HashMap<>();
        // Vérifier si la devise sélectionnée est vide ou nulle
        if (selectedCurrency == null || selectedCurrency.isEmpty()) {
            // Utiliser la devise par défaut (USD)
            selectedCurrency = "CAD";
        }
        // Définir le taux de conversion fixe en fonction de la devise sélectionnée
        double conversionRate = getConversionRate(selectedCurrency);

        for (Jeu jeu : catalog) {
            double convertedPrice = jeu.getPrix() * conversionRate;
            String formattedPrice = String.format("%.2f", convertedPrice);
            convertedPrices.put(jeu, formattedPrice);
        }

        return convertedPrices;
    }

    private double getConversionRate(String currency) {
        double conversionRate;
        if (currency.equals("CAD")) {
            conversionRate = 1.0; // Aucune conversion nécessaire pour CAD
        } else if (currency.equals("USD")) {
            conversionRate = 0.75; // Conversion de CAD à USD (taux de change fixe)
        } else if (currency.equals("EUR")) {
            conversionRate = 0.68; // Conversion de CAD à EUR (taux de change fixe)
        } else {
            throw new IllegalArgumentException("Devise non prise en charge: " + currency);
        }
        return conversionRate;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String currency = request.getParameter("currency");
        HttpSession session = request.getSession();
        session.setAttribute("selectedCurrency", currency);

        // Mettre à jour les prix convertis dans le panier
        Commande panier = MagasinService.getPanier(session);
        if (panier != null) {
            Map<Jeu, String> convertedPrices = convertPrices(panier.getJeux(), currency);
            session.setAttribute("convertedPrices", convertedPrices);
        }
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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