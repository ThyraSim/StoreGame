package filtres;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LanguageFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession(true);
        String language = req.getParameter("lang");

        if (language != null) {
            session.setAttribute("lang", language);
        } else if (session.getAttribute("lang") == null) {
            session.setAttribute("lang", request.getLocale().getLanguage());
        }

        chain.doFilter(req, res);
    }

    public void init(FilterConfig config) throws ServletException {
        // Initialization code
    }

    public void destroy() {
        // Destruction code
    }
}