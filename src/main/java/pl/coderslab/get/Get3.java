package pl.coderslab.get;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/get3")
public class Get3 extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        int widthInt = 5;
        int heightInt = 10;

        String width = request.getParameter("width");
        String height = request.getParameter("height");

        if (isCorrectParemeters(width, height)) {
            widthInt = Integer.parseInt(width);
            heightInt = Integer.parseInt(height);
        }

        PrintWriter writer = response.getWriter();
        writer.append("<table border=1>");

        for (int i = 1; i <= heightInt; i++) {
            writer.append("<tr>");
            for (int j = 1; j <= widthInt; j++) {
                writer.append("<td>")
                        .append(String.valueOf(i))
                        .append(" * ")
                        .append(String.valueOf(j))
                        .append(" = ")
                        .append(String.valueOf(i*j))
                        .append("</td>");
            }
            writer.append("</tr>");
        }
        writer.append("</table>");

    }

    private boolean isCorrectParemeters(String width, String height) {
        return StringUtils.isNotBlank(width) && StringUtils.isNotBlank(height)
                && StringUtils.isNumeric(width) && StringUtils.isNumeric(height);
    }

}
