package org.stroganov.JsonDBAPI;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class JsonSerializerTest {

    @Test
    void entitySerializer_Return_Valid_JSONString() {
        //GIVEN
        String expected = "{\"Car\":\"BMW\"}";
        Map<String, String> givenMap = new HashMap<>();
        givenMap.put("Car", "BMW");
        //WHEN
        String actual = JsonSerializer.entitySerializer(givenMap);
        //THEN
        Assertions.assertEquals(expected, actual);
    }
}