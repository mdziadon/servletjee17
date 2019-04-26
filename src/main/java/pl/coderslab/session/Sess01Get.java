package pl.coderslab.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/sess01Get")
public class Sess01Get extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("counter") == null) {
            response.getWriter().append("Brak atrybutu counter w sesji");
            return;
        }

        int counter = (int) session.getAttribute("counter");
        response.getWriter().append("counter = ").append(String.valueOf(counter));
        session.setAttribute("counter", ++counter);
    }
}
