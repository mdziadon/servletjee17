package pl.coderslab.cookie;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cookie51")
public class Cookie51 extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("utf-8");

        Cookie[] cookies = request.getCookies();

        boolean found = false;
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("visited")) {
                    found = true;
                }
            }
        }

        if (found) {
            response.getWriter().append("Twoje ciasteczko jest juz utworzone");
        } else {
            Cookie cookie = new Cookie("visited", "true");
            response.addCookie(cookie);

            String msg = request.getParameter("msg");
            if (StringUtils.isNotBlank(msg)) {
                response.getWriter().append(msg);
            }

        }

        response.getWriter().append("<a href='/cookie52'>Przejdz do kolejnej strony</a>");
    }
}
