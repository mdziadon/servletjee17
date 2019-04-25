package pl.coderslab.simple;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servlet14")
public class Servlet14 extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getHeader("User-Agent");

        String browser = "";
        if (user.contains("msie") || user.contains("Msie")) {
            browser = "Internet Explorer";
        } else if (user.contains("chrome") || user.contains("Chrome")) {
            browser = "Chrome";
        } else if (user.contains("safari") || user.contains("Safari")) {
            browser = "Safari";
        } else if (user.contains("opr") || user.contains("opera") || user.contains("Opr") || user.contains("Opera")) {
            browser = "Opera";
        } else if (user.contains("firefox") || user.contains("Firefox")) {
            browser = "Firefox";
        } else {
            browser = "UnKnown, More-Info: " + user;
        }

        response.getWriter().append(browser);
    }

}
