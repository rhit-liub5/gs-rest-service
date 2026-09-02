package com.example.restservice;

import java.io.File;
import java.util.List;

import tools.jackson.databind.ObjectMapper;

public class JsonDatabase {

    private final String filePath
            = "src/main/resources/database.json";

    private final ObjectMapper mapper
            = new ObjectMapper();

    public List<Greeting> findAll() {

        try {

            return mapper.readValue(
                    new File(filePath),
                    mapper.getTypeFactory()
                            .constructCollectionType(
                                    List.class,
                                    Greeting.class
                            )
            );

        } catch (Exception e) {

            e.printStackTrace();

            return List.of();

        }

    }

    public static void main(String[] args) {

        JsonDatabase db = new JsonDatabase();

        System.out.println(db.findAll());

    }

}
