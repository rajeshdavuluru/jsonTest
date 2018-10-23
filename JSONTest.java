package com.att;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class JSONTest {

    public static void main(String[] args) {
        try {

            if (args.length <= 0)
                return;

            URL url = new URL(args[0]);
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();

            JsonParser jsonParser = new JsonParser();
            JsonElement jsonElement = jsonParser.parse(new InputStreamReader((InputStream) urlConnection.getContent()));
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            JsonArray numbersArray;
            int individualSum = 0, total = 0;

            for (JsonElement jsonObject : jsonArray) {
                numbersArray = jsonObject.getAsJsonObject().get("numbers").getAsJsonArray();
                individualSum = 0;
                for (JsonElement val : numbersArray) {
                    individualSum += val.getAsInt();
                }
                System.out.println("individualSum:--" + individualSum);
                total += individualSum;
            }
            System.out.println("Total:--" + total);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
