package filtres;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LanguageFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(true);
        String language = req.getParameter("lang");
        String langCookieValue = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("lang")) {
                    langCookieValue = cookie.getValue();
                    // Do something with the cookie value
                    break;
                }
            }
        }

        if (language != null) {
            language = language.toLowerCase();
            session.setAttribute("lang", language);
            // Create and set the language cookie
            Cookie langCookie = new Cookie("lang", language);
            langCookie.setMaxAge(3600); // Cookie expiration time in seconds
            response.addCookie(langCookie);
        } else if (session.getAttribute("lang") == null) {
            // If no language parameter and no language in session, set default language
            session.setAttribute("lang", request.getLocale().getLanguage());
        } else {
            if(langCookieValue != null){
                session.setAttribute("lang", langCookieValue);
            }
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
