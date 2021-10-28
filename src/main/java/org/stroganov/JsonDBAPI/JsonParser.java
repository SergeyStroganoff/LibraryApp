package org.stroganov.JsonDBAPI;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public interface JsonParser {


    static <T> List<T> getListEntitiesFromDB(String jsonString, Class<T> tClass) {
        Gson gson = new Gson();
        Type itemsListType = TypeToken.getParameterized(ArrayList.class, tClass).getType();
        List<T> entityList = gson.fromJson(jsonString, itemsListType);
        if (entityList == null) {
            entityList = new ArrayList<>();
        }
        return entityList;
    }
}
