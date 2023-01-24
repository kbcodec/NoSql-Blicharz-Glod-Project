package org.example.collections.inhabitant;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.example.database.MongoDBConnection;

import java.util.Random;

public class InhabitantGenerator {
    public static void main(String[] args) {
//        MongoDBConnection mongoDBConnection = new MongoDBConnection();
//        MongoDatabase database = mongoDBConnection.connect();
//        MongoCollection<Document> collection = database.getCollection("Inhabitants");

        String[] firstNames = {"John", "Jane", "Mike", "Emily", "Jessica", "Daniel", "Sarah", "David", "Cameron", "Holly", "Thomasine", "Betta", "Gilberte"};
        String[] lastNames = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Garcia", "White", "Cameron", "Cat", "Black", "Chamberlain"};
        String[] educations = {"Elementary", "High school", "College", "University"};
        String[] professions = {"Teacher", "Doctor", "Engineer", "Lawyer", "Businessperson", "Footballer", "Programmer", "Actor", "Historian", "Musician", "Writer", "Office worker", "Journalist", "Photographer", "Translator", "Hairdresser", "Librarian", "Bricklayer", "Mechanic", "Locksmith", "Baker", "Waiter", "Notary", "Plumber"};
        int[] districts = {1,2,3,4,5};
        int[] buildingIds = {1,2,3,4,5};

        Random rand = new Random();
        for (int i = 1; i <= 100; i++) {
            String firstName = firstNames[rand.nextInt(firstNames.length)];
            String lastName = lastNames[rand.nextInt(lastNames.length)];
            String birthDate = "01/01/1990";
            String gender = rand.nextBoolean() ? "Male" : "Female";
            String education = educations[rand.nextInt(educations.length)];
            String profession = professions[rand.nextInt(professions.length)];
            int districtID = districts[rand.nextInt(districts.length)];
            int buildingID = buildingIds[rand.nextInt(buildingIds.length)];

            Document inhabitant = new Document("inhabitant_id", i)
                    .append("firstName", firstName)
                    .append("lastName", lastName)
                    .append("BirthDate", birthDate)
                    .append("gender", gender)
                    .append("education", education)
                    .append("profession", profession)
                    .append("districtID", districtID)
                    .append("buildingID", buildingID);

//            collection.insertOne(inhabitant);
        }

//        mongoDBConnection.close();
    }
}
