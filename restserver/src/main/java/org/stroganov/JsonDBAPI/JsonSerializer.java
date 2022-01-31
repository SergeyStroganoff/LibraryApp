package org.stroganov.JsonDBAPI;

import com.google.gson.Gson;

public interface JsonSerializer {

    static <T> String entitySerializer(T o) {
        Gson gson = new Gson();
        return gson.toJson(o);
    }

}
