package pl.coderslab.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/showAllCookies")
public class Cookie4Show extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        Cookie[] cookies = request.getCookies();

        if (cookies == null || cookies.length == 0) {
            response.getWriter().append("Brak ciasteczek");
            return;
        }

        for (Cookie cookie : cookies) {
            response.getWriter().append(cookie.getName())
                    .append(": ")
                    .append(cookie.getValue())
                    .append(" <a href='/removeCookie?cookieName=")
                    .append(cookie.getName())
                    .append("'>")
                    .append("Usuń")
                    .append("</a>")
                    .append("<br>");
        }

    }

}
