package com.jwcjlu.redis;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Zoo implements Serializable {
    public String name;
    public String city;
    public List<Animal> animals;
    @JsonCreator
    public Zoo( @JsonProperty("name") String name, @JsonProperty("city") String city) {
        this.name = name;
        this.city = city;
    }
    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }
    @Override
    public String toString() {
        return "Zoo [name=" + name + ", city=" + city + ", animals=" + animals + "]";
    }

    public static void main(String[] args) throws IOException {
        serializable();
        Zoo zoo=deSerializable();
        System.out.println(zoo);

    }
    public static void serializable() throws IOException {
        Zoo zoo = new Zoo("Samba Wild Park", "Paz");
        Lion lion = new Lion("Simba");
        Elephant elephant = new Elephant("Manny");
        List<Animal> animals = new ArrayList<>();
        animals.add(lion);
        animals.add(elephant);
        zoo.setAnimals(animals);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(System.out, zoo);
    }
    public  static Zoo deSerializable() throws IOException {
        String content="{\n" +
                "  \"name\" : \"Samba Wild Park\",\n" +
                "  \"city\" : \"Paz\",\n" +
                "  \"animals\" : [ {\n" +
                "    \"@class\" : \"com.jwcjlu.redis.Lion\",\n" +
                "    \"name\" : \"Simba\",\n" +
                "    \"sound\" : \"Roar\",\n" +
                "    \"type\" : \"carnivorous\",\n" +
                "    \"endangered\" : true\n" +
                "  }, {\n" +
                "    \"@class\" : \"com.jwcjlu.redis.Elephant\",\n" +
                "    \"name\" : \"Manny\",\n" +
                "    \"sound\" : \"trumpet\",\n" +
                "    \"type\" : \"herbivorous\",\n" +
                "    \"endangered\" : false\n" +
                "  } ]\n" +
                "}";
        ObjectMapper mapper = new ObjectMapper();
        Zoo zoo = mapper.readValue(content, Zoo.class);
       return zoo;

    }

}
