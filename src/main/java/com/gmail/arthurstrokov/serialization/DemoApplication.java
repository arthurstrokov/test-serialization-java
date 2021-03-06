package com.gmail.arthurstrokov.serialization;

import com.gmail.arthurstrokov.serialization.model.Book;
import com.gmail.arthurstrokov.serialization.model.Person;
import com.gmail.arthurstrokov.serialization.service.JsonConverter;
import com.gmail.arthurstrokov.serialization.util.PersonCreateUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.FileWriter;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            Person person = PersonCreateUtil.createPerson();

            String convert = JsonConverter.convert(person, false);
            System.out.println(convert);

            FileWriter fileWriter = new FileWriter("test-convert.json");
            fileWriter.write(convert);
            fileWriter.close();

            Book book = new Book("null", null);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            System.out.println(gson.toJson(book));
            System.out.println(JsonConverter.convert(book, false));
        };
    }
}
