package pl.coderslab.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@WebServlet("/sess05")
public class Sess05 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String captchaStr = request.getParameter("captcha");
        response.setContentType("text/html; charset=utf-8");

        try{
            int captcha = Integer.valueOf(captchaStr);
            int checkCaptcha = (int) request.getSession().getAttribute("captcha");
            if(captcha==checkCaptcha){
                response.getWriter().append("Gratuluje. Zdałeś, 3 siadaj");
            }else{
                response.getWriter().append("Nieprawidłowa captcha");
            }
            response.getWriter().append("<a href='/sess05'>Powrót</a>");

        }catch (NumberFormatException e){

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Random random = new Random();
        int a = random.nextInt(100);
        int b = random.nextInt(100);
        int sum = a+b;
        request.getSession().setAttribute("captcha",sum);
        response.setContentType("text/html; charset=utf-8");
        response.getWriter().append(
                "<form method='post' action=''>" +
                    "<input type='text' name='imie' placeholder='imie'/><br>" +
                    "<input type='text' name='nazwisko' placeholder='nazwisko'/><br>" +
                    "<input type='email' name='email' placeholder='email'/><br>" +
                    "Podaj wynik dodawania liczby "+a+" + "+b+": <br>" +
                    "<input type='number' name='captcha'/><br>" +
                    "<input type='submit' value='Sprawdz'/>" +
                "</form>"
        );
    }
}