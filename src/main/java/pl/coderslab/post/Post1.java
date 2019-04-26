package pl.coderslab.post;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/post1")
public class Post1 extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
//        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");



        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        response.getWriter().append("Witaj, ").append(firstName).append(" ").append(lastName);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/index_1.html");
    }

}
