package pl.coderslab.post;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/post3")
public class Post3 extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        String num_a = request.getParameter("num_a");
        String num_b = request.getParameter("num_b");
        String num_c = request.getParameter("num_c");

        if(StringUtils.isBlank(num_a) || StringUtils.isBlank(num_b) || StringUtils.isBlank(num_c)) {
            response.getWriter().append("<h2>MUSISZ PODAC A, B i C</h2>");
            return;
        }

        try {
            response.getWriter().append("<h2>Obliczanie miejsc zerowych dla f(x) = ")
                    .append(num_a).append("X^2 * ")
                    .append(num_b).append("X + ")
                    .append(num_c).append("</h2>");

            double a = Double.valueOf(num_a);
            double b = Double.valueOf(num_b);
            double c = Double.valueOf(num_c);
            double delta = Math.pow(b, 2) - (4 * (a * c)); //sprawdzam deltę b^2-4ac

            response.getWriter().append("<h4>Twoja delta to:").append(String.valueOf(delta)).append("</h4>");

            if (delta >= 0) { //dla delty większej od zera szukam pierwiastków
                double x1 = (-b - Math.sqrt(delta)) / (2 * a);
                double x2 = (-b + Math.sqrt(delta)) / (2 * a);
                if (x1 == x2) {//jeśli dwa pierwiastki są równe to wyświetlam odpowiednią informację dla użytkownika
                    response.getWriter().append("<h3>Znaleziono 1 pierwiastek: ").append(String.valueOf(x1)).append("</h3>");
                } else {
                    response.getWriter().append("<hr/><h3>Znalezione pierwiastki to:<br/>x1=")
                            .append(String.valueOf(x1)).append("<br/>x2=")
                            .append(String.valueOf(x2)).append("</h3><hr/>");//drukuję informajce o dwóch pierwiastkach
                }
            } else {
                response.getWriter().append("<h3>Brak pierwiastków</h3>");//jeśli delta jest >= 0 drukuję informację o braku pierwiastków
            }
        } catch (NumberFormatException e) {
            response.getWriter().append("<h3>Liczby podane jako paramerty nie mogły zostać przetwrzone. Spróbuj ponownie</h3>");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("index_3.html");
    }

}
