package com.gmail.arthurstrokov.serialization.util;

import com.gmail.arthurstrokov.serialization.model.Address;
import com.gmail.arthurstrokov.serialization.model.Book;
import com.gmail.arthurstrokov.serialization.model.Person;
import com.gmail.arthurstrokov.serialization.model.Phone;

import java.util.List;

public class PersonCreateUtil {

    private PersonCreateUtil() {
    }

    public static Person createPerson() {
        Person person = new Person();
        Address address = new Address();
        List<Book> books = List.of(new Book("Java", 0.12f), new Book("Gradle", 0.01f));
        Phone[] phones = new Phone[]{new Phone("home", 80291555376L), new Phone("work", 80172996110L)};

        address.setStreet("spring street");
        address.setPhones(phones);

        person.setName("me");
        person.setAge(21);
        person.setEmail("me@doit.com");
        person.setAddress(address);
        person.setBooks(books);
        return person;
    }
}
