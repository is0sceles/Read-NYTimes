package com.isosceles.nytsearch;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Teletran on 7/27/2016.
 */
public class Article {

    public String getWebUrl() {
        return webUrl;
    }

    public String getThumbNail() {
        return thumbNail;
    }

    public String getHeadLine() {
        return headLine;
    }

    String webUrl;
    String headLine;
    String thumbNail;

    public Article (JSONObject jsonObject){

        try {
            this.webUrl = jsonObject.getString("web_url");
            this.headLine = jsonObject.getJSONObject("headline").getString("main");

            JSONArray multimedia = jsonObject.getJSONArray("multimedia");

            if (multimedia.length() > 0) {
                JSONObject multimediaJson = multimedia.getJSONObject(0);
                 this.thumbNail = "http://www.nytimes.com/" + multimediaJson.getString("url");

            } else {
                this.thumbNail = "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcSh17GiojAnozld66JP8KOCO7zngUGRZyuxt2EDAbg9_ntbMjCO3A";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // factory method
    public static ArrayList<Article> fromJSONArray(JSONArray array) {
        ArrayList<Article> results = new ArrayList<>();

        for (int i=0; i<array.length(); i++) {

            try {
                results.add(new Article(array.getJSONObject(i)));
            } catch (JSONException e) {
              e.printStackTrace();
            }
        }
        return results;
    }

}
