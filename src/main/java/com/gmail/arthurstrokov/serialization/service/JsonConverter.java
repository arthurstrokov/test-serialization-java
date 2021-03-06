package com.gmail.arthurstrokov.serialization.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class JsonConverter {

    private JsonConverter() {
    }

    public static String convert(Object obj, boolean suppressObjCurlier) throws IllegalArgumentException, IllegalAccessException {
        Class<?> objClass = obj.getClass();
        Field[] fields = objClass.getDeclaredFields();
        StringBuilder jsonResult = new StringBuilder(suppressObjCurlier ? "" : "{");
        for (Field field : fields) {
            field.setAccessible(true); // private fields
            String fieldName = field.getName();
            Object fieldValue = field.get(obj);

            if (fieldValue == null) {
                continue;
            }
            if (fieldValue instanceof String || fieldValue instanceof Character) {
                jsonResult.append("\"").append(fieldName).append("\":\"").append(fieldValue).append("\",");

            } else if (fieldValue instanceof Number || fieldValue instanceof Boolean) {
                jsonResult.append("\"").append(fieldName).append("\":").append(fieldValue).append(",");

            } else if (fieldValue instanceof List<?>) {
                jsonResult.append(listToJson(field.get(obj), field.getName()));

            } else if (fieldValue instanceof Map<?, ?>) {
                jsonResult.append(mapToJson(field.get(obj), field.getName()));

            } else if (fieldValue.getClass().isArray()) {  // Arrays
                jsonResult.append(arrayToJson(field.get(obj), field.getName()));

            } else {  // if another object
                String object = convert(fieldValue, false);
                jsonResult.append("\"").append(fieldName).append("\":").append(object).append(",");
            }

            field.setAccessible(false); // private fields
            jsonResult.append(",");
            jsonResult = new StringBuilder(removeTrailingComma(String.valueOf(jsonResult)));
        }
        jsonResult = new StringBuilder(removeTrailingComma(String.valueOf(jsonResult)));
        return jsonResult + (suppressObjCurlier ? "" : "}");
    }

    private static String mapToJson(Object obj, String fieldName) {
        StringBuilder jsonResult = new StringBuilder();
        jsonResult.append("\"").append(fieldName).append("\": {");
        Map<?, ?> map = (Map<?, ?>) obj;
        for (Map.Entry<?, ?> objectObjectEntry : map.entrySet()) {
            Object value = objectObjectEntry.getValue();
            StringBuilder resultValue = new StringBuilder();
            if (value instanceof String) {  // TODO
                resultValue.append("\"").append(value).append("\"");
            } else {
                resultValue.append(value);
            }
            jsonResult.append("\"").append(objectObjectEntry.getKey()).append("\"").append(":").append(resultValue).append(",");
        }
        jsonResult = new StringBuilder(removeTrailingComma(String.valueOf(jsonResult)));
        jsonResult.append("},");
        return jsonResult.toString();
    }

    private static String listToJson(Object obj, String fieldName) throws IllegalAccessException {
        StringBuilder jsonResult = new StringBuilder();
        jsonResult.append("\"").append(fieldName).append("\": [");
        List<?> arrList = (List<?>) obj;
        for (Object arrListObj : arrList) {
            String object = convert(arrListObj, false);
            jsonResult.append(object).append(",");
        }
        jsonResult = new StringBuilder(removeTrailingComma(String.valueOf(jsonResult)));
        jsonResult.append("],");
        return jsonResult.toString();
    }

    private static String arrayToJson(Object obj, String fieldName) throws IllegalAccessException {
        StringBuilder jsonResult = new StringBuilder();
        jsonResult.append("\"").append(fieldName).append("\": [");
        Object[] objects = (Object[]) obj;
        for (Object arrListObj : objects) {
            String object = convert(arrListObj, false);
            jsonResult.append(object).append(",");
        }
        jsonResult = new StringBuilder(removeTrailingComma(String.valueOf(jsonResult)));
        jsonResult.append("],");
        return jsonResult.toString();
    }

    private static String removeTrailingComma(String jsonResult) {
        jsonResult = jsonResult.endsWith(",") ? jsonResult.substring(0, jsonResult.length() - 1) : jsonResult;
        return jsonResult;
    }
}
