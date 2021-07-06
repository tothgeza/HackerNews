package com.codecool.hackernews.utils;

import com.codecool.hackernews.model.News;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.List;

public class GetNews {
    private static GetNews INSTANCE;
    private List<News> newsList = null;

    public GetNews(){}

    public static GetNews getInstance(){
        if(INSTANCE == null){
            INSTANCE = new GetNews();
        }
        return INSTANCE;
    }
    public void requestNewsList(String topic, String pageNumber){
        try{
            String strUrl = "https://api.hnpwa.com/v0/" + topic + "/" + pageNumber + ".json";
            URL url = new URL(strUrl);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            int responseCode = con.getResponseCode();
//            System.out.println("\nSending 'GET' request to URL : " + url);
//            System.out.println("Response Code : " + responseCode);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));

            String line = reader.readLine();
            reader.close();

            Type type = new TypeToken<List<News>>(){}.getType();
            newsList =  new Gson().fromJson(line, type);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<News> getNewsList() {
        return newsList;
    }
}