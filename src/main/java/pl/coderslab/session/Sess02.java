package pl.coderslab.session;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/sess02")
public class Sess02 extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("utf-8");

        // Pobranie parametru wysłanego metodą post z formularza
        String grade = request.getParameter("grade");

        // Pobranie listy ocen z sesji
        HttpSession session = request.getSession();
        List<Integer> gradeList = (List<Integer>) session.getAttribute("gradeList");
        // Jeśli nie było w sesji to tworzę nową listę
        if (gradeList == null) {
            gradeList = new ArrayList<>();
        }

        // Sprawdzenie poprawności podanej wartości
        if (isCorrectGrade(grade)) {
            // Dodanie oceny do listy
            gradeList.add(Integer.parseInt(grade));
            // Dodanie listy z ocenami do sesji
            session.setAttribute("gradeList", gradeList);
        } else {
            response.getWriter().append("Nieprawidłowa ocena");
        }

        // Wywołuje doGet aby wyswietlic formularz (bez duplikacji kodu)
        doGet(request, response);

        // Wyświetlenie ocen i średniej
        displayGrades(response, gradeList);
    }

    private void displayGrades(HttpServletResponse response, List<Integer> gradeList) throws IOException {
        response.getWriter().append("Oceny: ").append("<br>");
        double sum = 0;
        for (int grade : gradeList) {
            response.getWriter().append(String.valueOf(grade)).append("<br>");
            sum += grade;
        }
        response.getWriter().append("<br>")
                .append("Średnia ocen: ").append(String.valueOf(sum / gradeList.size()));
    }

    private boolean isCorrectGrade(String grade) {
        return StringUtils.isNotBlank(grade) && StringUtils.isNumeric(grade)
                && Integer.parseInt(grade) >= 1 && Integer.parseInt(grade) <= 6;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("utf-8");

        response.getWriter().append("<form method='post'>")
                .append("<label>Ocena:")
                .append("<input type='number' name='grade'>")
                .append("</label>")
                .append("<input type='submit' value='Zapisz'>")
                .append("</form>");
    }
}
