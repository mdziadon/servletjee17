package pl.coderslab.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteCookie")
public class Cookie1Del extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();

        if (cookies == null || cookies.length == 0) {
            response.getWriter().append("Brak ciasteczek");
            return;
        }

        for (Cookie cookie : cookies) {
            if ("User".equals(cookie.getName())) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                response.getWriter().append("Ciasteczko User skasowane");
                return;
            }
        }

        response.getWriter().append("Brak ciasteczka User");
    }
}
