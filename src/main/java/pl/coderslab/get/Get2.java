package pl.coderslab.get;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

// http://localhost:8080/get2?param=1&param=2&param=3&a=1&b=2


@WebServlet("/get2")
public class Get2 extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        Map<String, String[]> parameterMap = request.getParameterMap();

        for (String key : parameterMap.keySet()) {
            response.getWriter().append(key).append(": ");
            String[] values = parameterMap.get(key);
            for (String value : values) {
                response.getWriter().append(value).append(", ");
            }
            response.getWriter().append("<br>");
        }
    }
}
