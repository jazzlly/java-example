package org.example.util;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtils {
    public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static String toJson(Object o) {
        return gson.toJson(o);
    }
}