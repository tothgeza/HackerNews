package com.codecool.hackernews;

import com.codecool.hackernews.model.News;
import com.codecool.hackernews.utils.GetNews;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.List;

@WebServlet(name = "ApiServlet", urlPatterns = {"/api/top", "/api/newest", "api/jobs"}, loadOnStartup = 0)
public class ApiServlet extends javax.servlet.http.HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        String topicName = null;
        if(servletPath.contains("top")){
            topicName = "news";
        } else if(servletPath.contains("newest")){
            topicName = "newest";
        } else if(servletPath.contains("jobs")){
            topicName = "jobs";
        }
        String pageNumber;
        if (request.getParameter("page") == null){
            pageNumber = "1";
        } else {
            pageNumber = request.getParameter("page");
        }

        List<News> inpList;
        GetNews news = GetNews.getInstance();
        news.requestNewsList(topicName, pageNumber);
        inpList = news.getNewsList();
        System.out.println(inpList.toString());

        Type type = new TypeToken<List<News>>(){}.getType();
        String newsList =  new Gson().toJson(inpList, type);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(newsList);
        out.flush();
    }
}
