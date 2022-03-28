package com.gmail.arthurstrokov.serialization.util;

import com.gmail.arthurstrokov.serialization.model.Address;
import com.gmail.arthurstrokov.serialization.model.Book;
import com.gmail.arthurstrokov.serialization.model.Person;

import java.util.Arrays;
import java.util.List;

public class PersonUtil {

    private PersonUtil() {
    }

    public static Person createPerson() {
        Person person = new Person();
        Address address = new Address();
        List<Book> books = Arrays.asList(new Book("Java"), new Book("Gradle"));

        address.setStreet("spring street");
        person.setName("me");
        person.setEmail("me@doit.com");
        person.setAddress(address);
        person.setBooks(books);
        return person;
    }
}
