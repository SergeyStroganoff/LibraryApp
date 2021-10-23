package org.stroganov.JsonDBAPI;

import com.google.gson.Gson;

public class JsonSerializer {

    public static <T> String entitySerializer(T o) {
        Gson gson = new Gson();
        return gson.toJson(o);
    }

}
