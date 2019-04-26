package pl.coderslab.post;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/post2")
public class Post2 extends HttpServlet {

    private static final String[] badWords = {"cholera", "kurde", "motylanoga"};

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");

        String agree = request.getParameter("agree");
        String description = request.getParameter("description");

        if (!"on".equals(agree)) {
            for (String badWord : badWords) {
                if (description.contains(badWord)) {
                    String stars = badWord.replaceAll(".", "*");
                    description = StringUtils.replace(description, badWord, stars);
                }
            }

        }
        response.getWriter().append(description);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/index_2.html");
    }
}
