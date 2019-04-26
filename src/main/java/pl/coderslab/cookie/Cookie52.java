package pl.coderslab.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/cookie52")
public class Cookie52 extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();

        boolean found = false;
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("visited")) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    found = true;
                }
            }
        }

        if (found) {
            response.getWriter().append("Witamy na stronie Cookie52");
        } else {
            String message = "Nie odwiedziles jeszcze tej strony ";
            response.sendRedirect("/cookie51?msg=" + URLEncoder.encode(message, "UTF-8"));
        }

    }
}
