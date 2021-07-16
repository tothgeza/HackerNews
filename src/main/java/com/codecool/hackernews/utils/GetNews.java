package com.codecool.hackernews.utils;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class GetNews {
    private static GetNews INSTANCE;
    private String newsList = null;

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

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));

            newsList = reader.readLine();
            reader.close();
            con.disconnect();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public String getNewsList() {
        return newsList;
    }
}