package com.gmail.arthurstrokov.serialization.util;

import com.gmail.arthurstrokov.serialization.model.Address;
import com.gmail.arthurstrokov.serialization.model.Book;
import com.gmail.arthurstrokov.serialization.model.Person;
import com.gmail.arthurstrokov.serialization.model.Phone;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonCreateUtil {

    private PersonCreateUtil() {
    }

    public static Person createPerson() {
        Person person = new Person();
        Address address = new Address();
        List<Book> books = List.of(new Book("Java", 0.12f), new Book("Gradle", 0.01f));
        Phone[] phones = new Phone[]{new Phone("home", 80291555376L), new Phone("work", 80172996110L)};

        Map<String, Integer> map = new HashMap<>();
        map.put("Key", 1);
        map.put("Value", 2);
        person.setMap(map);

        address.setStreet("spring street");
        address.setPhones(phones);

        Map<Short, Double> shortDoubleMap = new HashMap<>();
        shortDoubleMap.put((short) 12, 0.255d);
        address.setShortDoubleMap(shortDoubleMap);
        address.setCharacter('a');

        person.setAge(21);
        person.setName("me");
        person.setAddress(address);
        person.setEmail("me@doit.com");
        person.setBooks(books);
        person.setActive(true);
        return person;
    }
}
