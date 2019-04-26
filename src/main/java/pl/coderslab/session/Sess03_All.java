package pl.coderslab.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("/showAllSession")
public class Sess03_All extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Pobranie mapy z sesji
        Map<String, String> map = (Map<String, String>) session.getAttribute("map");
        // Jeśli nie ma w sesji to wyswietlam komunikat i wychodze z metody
        if (map == null) {
            response.getWriter().append("Brak danych w sesji");
            return;
        }

        response.getWriter().append("<table border='1'>");
        for (String key : map.keySet()) {
            String value = map.get(key);
            response.getWriter().append("<tr>")
                    .append("<td>").append(key).append("</td>")
                    .append("<td>").append(value).append("</td>")
                    .append("</tr>");
        }
        response.getWriter().append("</table>");
    }
}
