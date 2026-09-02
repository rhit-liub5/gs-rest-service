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

    private void saveAll(List<Greeting> greetings) {

        try {

            mapper.writeValue(
                    new File(filePath),
                    greetings
            );

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    public Greeting add(Greeting greeting) {

        try {

            List<Greeting> greetings = findAll();

            greetings.add(greeting);

            mapper.writeValue(
                    new File(filePath),
                    greetings
            );

        } catch (Exception e) {

            e.printStackTrace();

        }

        return greeting;
    }

    public Greeting update(Greeting greeting) {

        List<Greeting> greetings = findAll();

        for (int i = 0; i < greetings.size(); i++) {

            if (greetings.get(i).id() == greeting.id()) {

                greetings.set(i, greeting);

                saveAll(greetings);

                return greeting;

            }

        }

        return null;

    }

    public boolean delete(long id) {

        List<Greeting> greetings = findAll();

        boolean removed
                = greetings.removeIf(
                        greeting -> greeting.id() == id
                );

        if (removed) {

            saveAll(greetings);

        }

        return removed;

    }

    public static void main(String[] args) {

        JsonDatabase db = new JsonDatabase();

        System.out.println(db.findAll());

    }

}
