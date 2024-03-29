package controleur;


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

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String logout = request.getParameter("logout");

        if (logout != null && logout.equals("true")) {
            session.invalidate();
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }

        List<Compte> compteList = MagasinService.getListCompte(session);
        Compte compteLogged = MagasinService.getCompte(session);
        if(compteLogged != null){
            response.sendRedirect("ProfileServlet?idCompte="+compteLogged.getIdCompte());
            return;
        }


        String indexPourjsp = request.getParameter("index");
        Integer index = null;

        for (Compte compte : compteList) {
            if (compte.getUser().equals(username) && compte.getPassword().equals(password)) {
                // L'utilisateur est authentifié
                // Ajoutez votre logique ici pour rediriger ou effectuer d'autres opérations
                session.setAttribute("loggedInAccount", compte);
                try{
                    index = Integer.parseInt(request.getParameter("index"));
                    request.setAttribute("index", index);
                    response.sendRedirect("AcheteServlet?index=" + index);
                }catch (NumberFormatException e){
                    response.sendRedirect("ProfileServlet?idCompte="+compte.getIdCompte());
                }
                return;
            }
        }

        // Si les informations d'identification sont incorrectes, affichez un message d'erreur ou redirigez vers une page d'erreur
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    public void destroy() {
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
}

