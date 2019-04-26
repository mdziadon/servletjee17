package pl.coderslab.post;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@WebServlet("/post4")
public class Post4 extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] numbers = request.getParameterValues("numbers");
        List<Integer> list = new ArrayList<>();

        for (String number : numbers) {
            if (StringUtils.isNumeric(number)) {
                int numberInt = Integer.parseInt(number);
                list.add(numberInt);
            }
        }

        Collections.sort(list);
        response.getWriter().append(list.toString());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");

        Random random = new Random();
        int count = random.nextInt(6) + 5;

        StringBuilder sb = new StringBuilder();
        sb.append("<form action='/post4' method='post'>");
        for (int i = 0; i < count; i++) {
            sb.append("<input type='text' name='numbers'>").append("<br>");
        }
        sb.append("<br><br>");
        sb.append("<input type='submit' value='Zapisz'>");
        sb.append("</form>");

        response.getWriter().append(sb.toString());
    }
}
