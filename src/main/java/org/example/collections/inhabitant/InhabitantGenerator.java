package org.example.collections.inhabitant;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.example.database.MongoDBConnection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
//import java.util.Random;

public class InhabitantGenerator {

    CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
    CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

    MongoDatabase database = MongoDBConnection.connect(pojoCodecRegistry);

    //MongoClient mongoClient = MongoClients.create();
    //MongoDatabase database = mongoClient.getDatabase("CityDB");
    MongoCollection<Document> collection = database.getCollection("Inhabitants");

    ArrayList<String> firstNames = new ArrayList<>(Arrays.asList("Pavel", "Grenville", "Edvard", "Irene", "Percival", "Blinny", "Lea", "Mohandas"));

    ArrayList<String> lastNames = new ArrayList<>(Arrays.asList("Melluish", "Duffin", "Walter", "Bodycomb", "Ranshaw", "Gwillim", "Titmuss", "Linny"));
    ArrayList<Integer> dateOfBirths = new ArrayList<>(Arrays.asList(1999, 2010, 2009, 1988, 1959, 1998, 1998, 1990));
    ArrayList<String> genders = new ArrayList<>(Arrays.asList("Male", "Male", "Male", "Female", "Male", "Female", "Female", "Male"));
    ArrayList<Integer> buildingIds = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
    ArrayList<Integer> districtIds = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
    ArrayList<String> educations = new ArrayList<>(Arrays.asList("Dr", "Mrs", "Dr", "Rev", "Dr", "Honorable", "Honorable", "Mr"));
    ArrayList<String> professions = new ArrayList<>(Arrays.asList("Senior Financial Analyst", "Software Consultant", "VP Marketing", "Office Assistant IV", "Systems Administrator III", "Programmer IV", "Recruiting Manager", "Research Assistant IV"));



//    List<Document> inhabitants = new ArrayList<>();
//        for (int i = 0; i < firstNames.length; i++) {
//        inhabitants.add(new Document("inhabitantId", i + 1)
//                .append("personalInfo.firstName", firstNames.get(i))
//                .append("personalInfo.lastName", lastNames.get(i))
//                .append("personalInfo.dateOfBirth", dateOfBirths.get(i))
//                .append("personalInfo.gender", genders.get(i))
//                .append("buildingId", buildingIds.get(i))
//                .append("districtId", districtIds.get(i))
//                .append("education", educations.get(i))
//                .append("profession", professions.get(i)));
//    }
//
//        database.getCollection("Inhabitants").insertMany(inhabitants);
}


