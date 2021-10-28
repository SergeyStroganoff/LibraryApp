package org.stroganov.JsonDBAPI;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    Gson gson = new Gson();

    public <T> List<T> getListEntitiesFromDB(String jsonString, Class<T> tClass) {
        Type itemsListType = TypeToken.getParameterized(ArrayList.class, tClass).getType();
        List<T> entityList = gson.fromJson(jsonString, itemsListType);
        if (entityList == null) {
            entityList = new ArrayList<>();
        }
        return entityList;
    }
}
