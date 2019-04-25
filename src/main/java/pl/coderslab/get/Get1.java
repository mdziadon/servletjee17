package pl.coderslab.get;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//http://localhost:8080/get1?start=10&end=20

@WebServlet("/get1")
public class Get1 extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String start = request.getParameter("start");
        String end = request.getParameter("end");

        if (StringUtils.isBlank(start) || StringUtils.isBlank(end)
            || !StringUtils.isNumeric(start) || !StringUtils.isNumeric(end)) {
            response.getWriter().append("Niepoprawne parametry: start, end");
            return;
        }

        int startInt = Integer.parseInt(start);
        int endInt = Integer.parseInt(end);

        for (int i = startInt; i <= endInt; i++) {
            response.getWriter().append(String.valueOf(i)).append(", ");
        }
    }
}
