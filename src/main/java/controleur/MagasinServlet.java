package controleur;

import dao.JeuDao;
import entities.Jeu;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MagasinServlet", value = "/MagasinServlet")
public class MagasinServlet extends HttpServlet {
    private String message;


    public void init() {

//        JeuDao.insert(new Jeu("Mario",79.99,"plateforme","Mario Bros 1"));
//        JeuDao.insert(new Jeu("Mario2",79.99,"plateforme","Mario Bros 2"));
//        JeuDao.insert(new Jeu("Mario3",79.99,"plateforme","Mario Bros 3"));
//        JeuDao.insert(new Jeu("Mario4",79.99,"plateforme","Mario Bros 4"));


    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        if (session == null) {
            response.sendRedirect("http://localhost:82/error.html");
        }

        //on génère le catalog de jeu
        List<Jeu> catalog = new ArrayList<>();
        catalog = JeuDao.findAll();
        request.setAttribute("catalog", catalog);

        List<Jeu> panier =null;

        // AJOUTER JEU AU PANIER

        String action = request.getParameter("action");

        //Ajouter au panier si action = ACHETE,

        if (action.equals("ACHETE")) {
            //Ajouter au panier


            if (panier == null) {
                panier = new ArrayList<>();

            }

            String index = request.getParameter("index");

            Jeu monJeu = JeuDao.findJeuById(Integer.parseInt(index));
            panier.add(monJeu);


            request.setAttribute("ajoutJeu", monJeu);
            session.setAttribute("panier",panier);

        }


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
}

