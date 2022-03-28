package com.gmail.arthurstrokov.serialization.service;

import java.lang.reflect.Field;
import java.util.List;

public class JsonConverter {

    private JsonConverter() {
    }

    public static String objectConverter(Object obj, boolean suppressObjCurlier) throws IllegalArgumentException, IllegalAccessException {
        Class<?> objClass = obj.getClass();
        Field[] fields = objClass.getDeclaredFields();
        StringBuilder json = new StringBuilder(suppressObjCurlier ? "" : "{");
        for (Field field : fields) {
            field.setAccessible(true); // private fields
            String name = field.getName();
            Object value = field.get(obj);
            if (value instanceof String) {
                json.append("\"").append(name).append("\":\"").append(value).append("\",");
            } else if (value instanceof List<?>) {
                json.append("\"").append(name).append("\": [");
                @SuppressWarnings("unchecked")
                List<Object> arrList = (List<Object>) value;
                for (Object arrListObj : arrList) {
                    String object = objectConverter(arrListObj, false);
                    json.append(object).append(",");
                }
                json = new StringBuilder(removeTrailingComma(String.valueOf(json)));
                json.append("]");
            } else {
                String object = objectConverter(value, false);
                json.append("\"").append(name).append("\":").append(object).append(",");
            }
        }
        json = new StringBuilder(removeTrailingComma(String.valueOf(json)));
        return json + (suppressObjCurlier ? "" : "}");
    }

    private static String removeTrailingComma(String json) {
        json = json.endsWith(",") ? json.substring(0, json.length() - 1) : json;
        return json;
    }
}
