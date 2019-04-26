package pl.coderslab.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/removeCookie")
public class Cookie4Del extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        String cookieName = request.getParameter("cookieName");

        Cookie[] cookies = request.getCookies();

        if (cookies == null || cookies.length == 0) {
            response.getWriter().append("Brak ciasteczek");
            return;
        }

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                response.getWriter().append("Ciasteczko ")
                        .append(cookieName)
                        .append(" zostalo usuniete");
            }
        }
    }
}
