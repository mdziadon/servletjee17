package pl.coderslab.session;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/sess04")
public class Sess04 extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Pobranie produktów z sesji, jeśli ich nie ma to utworzenie nowej listy produktów
        List<Product> products = (List<Product>) request.getSession().getAttribute("basket");
        if (products == null) {
            products = new ArrayList<>();
        }

        //Pobranie parametrów z request (po submit formularza)
        String priceStr = request.getParameter("price");
        String nameStr = request.getParameter("name");
        String qtyStr = request.getParameter("qty");

        if (StringUtils.isBlank(priceStr) || StringUtils.isBlank(nameStr) || StringUtils.isBlank(qtyStr)
                || !StringUtils.isNumeric(priceStr) || !StringUtils.isNumeric(qtyStr)) {
            response.getWriter().append("Nieprawidlowe parametry");
            return;
        }

        //Jeśli parametry są ok to utworzenie obiektu produktu, dodanie go do listy a listę do sesji
        int qty = Integer.valueOf(qtyStr);
        double price = Double.valueOf(priceStr);
        Product p = new Product(nameStr, qty, price);
        products.add(p);
        //Dodanie produktów do sesji
        request.getSession().setAttribute("basket", products);

        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter writer = response.getWriter();
        writer.append("<form method='post'>" +
                "<input type='number' step='0.01' placeholder='Podaj cene' name='price'/>" +
                "<input type='number' step='1' placeholder='Podaj ilosc' name='qty'/>" +
                "<input type='text' placeholder='Podaj nazwe' name='name'/>" +
                "<input type='submit' value='Wyslij'/>" +
                "</form>");

        //Pobranie produktów z sesji, jeśli ich nie ma to utworzenie nowej listy produktów
        List<Product> products = (ArrayList<Product>) request.getSession().getAttribute("basket");
        if (products == null) {
            products = new ArrayList<>();
        }

        //Wyświetlenie produktów
        displayBasket(products, response);
    }

    private void displayBasket(List<Product> products, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=utf-8");

        double sum = 0.0;
        PrintWriter writer = response.getWriter();
        for (Product product : products) {
            writer.append("Produkt: ").append(product.getName())
                  .append(" ilosc: ").append(String.valueOf(product.getQty()))
                  .append(" cena: ").append(String.valueOf(product.getPrice())).append(" PLN<br>");
            sum += product.getPrice() * product.getQty();
        }
        writer.append("Suma: ").append(String.valueOf(sum)).append(" PLN");
    }

}

