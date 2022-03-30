package com.gmail.arthurstrokov.serialization.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String name;
    private Map<String, Integer> map;
    private Integer age;
    private Address address;
    private String email;
    private List<Book> books;
}
