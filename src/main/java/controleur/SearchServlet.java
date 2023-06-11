package controleur;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.CompteDao;
import entities.Compte;
import com.fasterxml.jackson.core.type.TypeReference;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import util.Utilitaire;

@WebServlet(name = "SearchServlet", value = "/SearchServlet")
public class SearchServlet extends HttpServlet {
        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            String searchTerm = request.getParameter("searchInput");

            List<Compte> compteResult = Utilitaire.findCompteByProfileName(searchTerm);
            request.setAttribute("compteResult", compteResult);
            RequestDispatcher rd = request.getRequestDispatcher("chooseFriend.jsp");
            try {
                rd.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
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