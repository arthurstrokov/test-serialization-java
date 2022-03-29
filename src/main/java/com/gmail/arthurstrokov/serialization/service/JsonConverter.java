package com.gmail.arthurstrokov.serialization.service;

import java.lang.reflect.Field;
import java.util.List;

public class JsonConverter {

    private JsonConverter() {
    }

    public static String convert(Object obj, boolean suppressObjCurlier) throws IllegalArgumentException, IllegalAccessException {
        Class<?> objClass = obj.getClass();
        Field[] fields = objClass.getDeclaredFields();
        StringBuilder json = new StringBuilder(suppressObjCurlier ? "" : "{");
        for (Field field : fields) {
            field.setAccessible(true); // private fields
            String fieldName = field.getName();
            Object fieldValue = field.get(obj);
            if (fieldValue instanceof String) {
                json.append("\"").append(fieldName).append("\":\"").append(fieldValue).append("\",");
            } else if (fieldValue instanceof Integer) {
                json.append("\"").append(fieldName).append("\":").append(fieldValue).append(",");
            } else if (fieldValue instanceof List<?>) {
                json.append("\"").append(fieldName).append("\": [");
                @SuppressWarnings("unchecked")
                List<Object> arrList = (List<Object>) fieldValue;
                for (Object arrListObj : arrList) {
                    String object = convert(arrListObj, false);
                    json.append(object).append(",");
                }
                json = new StringBuilder(removeTrailingComma(String.valueOf(json)));
                json.append("]");
            } else {  // if another object
                String object = convert(fieldValue, false);
                json.append("\"").append(fieldName).append("\":").append(object).append(",");
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
