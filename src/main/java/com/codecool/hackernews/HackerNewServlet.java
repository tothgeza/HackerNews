package com.codecool.hackernews;

import com.codecool.hackernews.model.News;
import com.codecool.hackernews.utils.GetNews;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "hackerNewsServlet", urlPatterns = {"/"}, loadOnStartup = 1)
public class HackerNewServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<News> inpList;
        GetNews news = GetNews.getInstance();
        news.requestNewsList("news","1");
        inpList = news.getNewsList();

        PrintWriter out = response.getWriter();

        StringBuilder cards = new StringBuilder("            <div id=\"cards\" class=\"row gy-3\">" + "\n");
        for (News x : inpList) {
            String card;
            card = "                <div class=\"col-sm-4\">\n" +
                    "                    <div class=\"card dark bg-dark h-100\">\n" +
                    "                        <div class=\"card-body\">\n" +
                    "                            <a class=\"text-primary nounderline mb-5\" href=\"#" + x.getUrl() +"\">" + x.getTitle() + "</a>\n" +
                    "                            <div class=\"text-white\">" + x.getTimeAgo() + "</div>\n" +
                    "                            <div class=\"text-white\">" + x.getUser() + "</div>\n" +
                    "                        </div>\n" +
                    "                    </div>\n" +
                    "                </div>" + "\n";
            cards.append(card);
        }
        cards.append("            <div>").append("\n");

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
        String footer = "    <footer class=\"footer mt-5 py-1 bg-dark\">\n" +
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
                "                    <a id=\"home\" class=\"nav-link active\" aria-current=\"page\" href=\"\">Hackson news</a>\n" +
                "                </li>\n" +
                "                <li class=\"nav-item\">\n" +
                "                    <a id=\"top-news\" class=\"nav-link\" href=\"#\">Top news</a>\n" +
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
                "        <div class=\"row mb-3\" id=\"navButtons\">\n" +
                "            <div class=\"col-sm-6 text-start\">\n" +
                "                <button id=\"previous\" class=\"btn btn-primary\" type=\"button\" data-prev=\"0\" data-category=\"top\">Previous</button>\n" +
                "            </div>\n" +
                "            <div class=\"col-sm-6 text-end\">\n" +
                "                <button id=\"next\" class=\"btn btn-primary\" type=\"button\" data-next=\"2\" data-category=\"top\">Next</button>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "            " + cards +
                "        </div>\n" +
                "    </main>\n" +
                "\n" + footer + "\n" +
                "</body>" + "\n" +
                "</html>";
        out.println(head + body);
        out.flush();

    }
}
