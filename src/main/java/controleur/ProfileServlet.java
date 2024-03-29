package controleur;

import entities.Client;
import entities.Compte;
import service.MagasinService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


@WebServlet(name = "ProfileServlet", value = "/ProfileServlet")
public class ProfileServlet extends HttpServlet {

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Compte compte = MagasinService.getCompte(session);
        if(compte == null){
            response.sendRedirect("LoginServlet");
            return;
        }

        Client client = MagasinService.getClient(compte);
        session.setAttribute("client", client);


        request.setAttribute("compte", compte);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/profile.jsp");
        dispatcher.forward(request, response);
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