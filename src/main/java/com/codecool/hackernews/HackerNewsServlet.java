package com.codecool.hackernews;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "hackerNewsServlet", urlPatterns = {"/"}, loadOnStartup = 1)
public class HackerNewsServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        String head = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "\n" +
                "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\"\n" +
                "          integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\">\n" +
                "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js\"\n" +
                "            integrity=\"sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM\"\n" +
                "            crossorigin=\"anonymous\"></script>\n" +
                "    <script src='/static/js/main.js' defer></script>\n" +
                "    <link rel=\"stylesheet\" href=\"static/css/site.css\">\n" +
                "\n" +
                "    <title>Hacker news</title>\n";
        String footer = "    <footer class=\"footer mt-auto py-1 bg-dark\">\n" +
                "        <div class=\"container-sm\">\n" +
                "            <div class=\"row\">\n" +
                "                <div class=\"col-sm-8 text-end\">\n" +
                "                    <span class=\"text-muted\">T&oacute;th G&eacute;za</span>\n" +
                "                </div>\n" +
                "                <div class=\"col-sm-4 text-end\">\n" +
                "                    <span class=\"text-muted\">toth.geza.0425@gmail.com</span>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </footer>";
        String body = "<body class=\"d-flex flex-column min-vh-100\">\n" +
                "    <nav class=\"navbar navbar-expand navbar-dark bg-dark mb-5\">\n" +
                "        <div class=\"container justify-content-center\">\n" +
                "            <ul class=\"navbar-nav\">\n" +
                "                <li class=\"nav-item\">\n" +
                "                    <label id=\"home\" class=\"navbar-brand text-warning\" aria-current=\"page\" href=\"\">Hackson news</label>\n" +
                "                </li>\n" +
                "                <li class=\"nav-item\">\n" +
                "                    <a id=\"top-news\" class=\"nav-link active\" href=\"#\">Top news</a>\n" +
                "                </li>\n" +
                "                <li class=\"nav-item\">\n" +
                "                    <a id=\"newest\" class=\"nav-link\" href=\"#\">Newest</a>\n" +
                "                </li>\n" +
                "                <li class=\"nav-item\">\n" +
                "                    <a id=\"jobs\" class=\"nav-link\" href=\"#\">Jobs</a>\n" +
                "                </li>\n" +
                "            </ul>\n" +
                "        </div>\n" +
                "    </nav>\n" +
                "    <main>\n" +
                "        <div id=\"main\" class=\"container-sm\">\n" +
                "        <div class=\"row mb-5 justify-content-center \" id=\"navButtons\">\n" +
                "            <div class=\"d-grid col-2\">\n" +
                "                <button id=\"previous\" class=\"btn btn-outline-primary btn-sm\" type=\"button\" data-prev=\"0\" data-category=\"top\">Previous</button>\n" +
                "            </div>\n" +
                "            <div class=\"d-grid col-2\">\n" +
                "                <button id=\"next\" class=\"btn btn-outline-primary btn-sm\" type=\"button\" data-next=\"2\" data-category=\"top\">Next</button>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div id=\"cards\" class=\"row gy-3 mb-5\">" + "</div>" + "\n" +
                "        </div>\n" +
                "    </main>\n" +
                "\n" + footer + "\n" +
                "</body>" + "\n" +
                "</html>";
        out.println(head + body);
        out.flush();
    }
}
