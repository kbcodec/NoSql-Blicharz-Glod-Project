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

    static public void Generator() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        MongoDatabase database = MongoDBConnection.connect(pojoCodecRegistry);

        //MongoClient mongoClient = MongoClients.create();
        //MongoDatabase database = mongoClient.getDatabase("CityDB");
        MongoCollection<Document> collection = database.getCollection("Inhabitants");

        ArrayList<String> firstNames = new ArrayList<>(Arrays.asList("Abigail", "Nicholas", "Hannah", "Austin", "Isabella", "Jacob", "Lauren", "Mason", "Ashley", "Robert", "Madison", "David", "Taylor", "John", "Alyssa", "Adam", "Jessica", "William", "Sarah", "Christopher", "Taylor", "Madison", "Brianna", "Michael", "Alyssa", "Matthew", "Hannah", "Andrew", "Lauren", "Joseph", "Avery", "William", "Abigail", "Christopher", "Isabella", "Daniel", "Emily", "Anthony", "Madison", "David", "Ashley", "Joshua", "Sarah", "Brian", "Jessica", "James", "Taylor", "Ryan", "Brianna", "Adam", "Alyssa", "John", "Hannah", "Robert", "Lauren", "Nathan", "Avery", "Jonathan", "Abigail", "Justin", "Isabella", "Adam", "Emily", "William", "Madison", "Robert", "Avery", "John", "Hannah", "James", "Lauren", "Michael", "Abigail", "Matthew", "Isabella", "David", "Taylor", "Daniel", "Ashley", "Andrew", "Sarah", "Anthony", "Jessica", "Jonathan", "Brianna", "Justin", "Alyssa", "Joshua", "Emily", "Brian", "Madison", "Ryan", "Avery", "Nathan", "Hannah", "Christopher", "Lauren", "Nicholas", "Abigail", "Jacob"));

        ArrayList<String> lastNames = new ArrayList<>(Arrays.asList("Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor", "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin", "Thompson", "Garcia", "Martinez", "Robinson", "Clark", "Rodriguez", "Lewis", "Lee", "Walker", "Hall", "Allen", "Young", "Hernandez", "King", "Wright", "Lopez", "Hill", "Scott", "Green", "Adams", "Baker", "Gonzalez", "Nelson", "Carter", "Mitchell", "Perez", "Roberts", "Turner", "Phillips", "Campbell", "Parker", "Evans", "Edwards", "Stewart", "Flores", "Morris", "Nguyen", "Murphy", "Rivera", "Cook", "Rogers", "Morgan", "Peterson", "Cooper", "Reed", "Bailey", "Bell", "Gomez", "Kelly", "Howard", "Ward", "Cox", "Diaz", "Richardson", "Wood", "Watson", "Brooks", "Bennett", "Gray", "James", "Rey", "Cruz", "Hughes", "Price", "Myers", "Long", "Foster", "Sanders", "Ross", "Morales", "Powell", "Sullivan", "Russell", "Ortiz", "Jenkins", "Gutierrez", "Perry", "Butler", "Barnes", "Fisher", "Smith", "White", "Smith", "Ben"));
        ArrayList<Integer> dateOfBirths = new ArrayList<>(Arrays.asList(1956, 1964, 1971, 1952, 1978, 2003, 1955, 1962, 1968, 1981, 1994, 1954, 1985, 1958, 2001, 1977, 1972, 1999, 1975, 1967, 1987, 1953, 1983, 1991, 1957, 1982, 1993, 1965, 1989, 1951, 1989, 2003, 1955, 1969, 1995, 1998, 1972, 1958, 1992, 1978, 2004, 1961, 2002, 1954, 1970, 1990, 1957, 1995, 1994, 1962, 1984, 1953, 1989, 1978, 2003, 2002, 1977, 1962, 1999, 1975, 1987, 1951, 1980, 1989, 1957, 2004, 1982, 1994, 1962, 1998, 1988, 1956, 1990, 2002, 1980, 2004, 1957, 1988, 1991, 1966, 1999, 1954, 1994, 1958, 2000, 1997, 1956, 1995, 1962, 2001, 1971, 1953, 2005, 1977, 1972, 1960, 1964, 1968, 1972, 1976, 1980, 1984, 1988, 1992, 1996, 2000, 2004, 1975, 1985));
        ArrayList<String> genders = new ArrayList<>(Arrays.asList("Female", "Male", "Female", "Male", "Female", "Male", "Female", "Male", "Female", "Male", "Female", "Male", "Female", "Male", "Female", "Male", "Female", "Male", "Female", "Male", "Female", "Male", "Female", "Male", "Female", "Male", "Female", "Male", "Female", "Male", "Female", "Male", "Female", "Male", "Female", "Male", "Female", "Male", "Female", "Male", "Female", "Male", "Female", "Male", "Female", "Male", "Female", "Male", "Female", "Male", "Female", "Male", "Female", "Male", "Female", "Male", "Female", "Male", "Female", "Male", "Female", "Male", "Female", "Male", "Female", "Male", "Female", "Male", "Female", "Male", "Female", "Male", "Female", "Male", "Female", "Male", "Female", "Male", "Female", "Male", "Female", "Male", "Female", "Male", "Female", "Male", "Female", "Male", "Female","Male", "Female", "Male","Male", "Female","Male", "Female", "Male", "Male", "Female","Male"));
        ArrayList<Integer> buildingIds = new ArrayList<>(Arrays.asList(24, 20, 15, 2, 28, 21, 29, 9, 18, 8, 27, 6,1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 15, 17, 19, 21, 23, 25, 27, 29, 1, 3, 5, 7, 9, 11, 13, 15, 5, 12, 14, 7, 26, 11, 25, 23, 10, 3, 17, 16, 4, 19, 1, 22, 30, 13, 6, 28, 29, 15, 12, 24, 22, 18, 21, 20, 10, 8, 17, 5, 4, 3, 27, 26, 25, 23, 2, 1, 19, 14, 13, 11, 9, 7, 30, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1));
        ArrayList<Integer> districtIds = new ArrayList<>(Arrays.asList(2, 8, 7, 3, 4, 1, 6, 5, 10, 9, 7, 1, 2, 6, 4, 10, 8, 9, 6, 1, 4, 7, 3, 8, 5, 2, 9, 10, 8, 1, 3, 4, 5, 7, 6, 2, 3, 9, 4, 10, 5, 8, 6, 7, 1, 2, 8, 10, 4, 7, 9, 5, 6, 3, 1, 4, 5, 7, 6, 2, 3, 9, 10, 8, 3, 10, 6, 2, 8, 7, 9, 5, 4, 1, 10, 8, 5, 7, 9, 2, 3, 1, 4, 6, 5, 9, 4, 2, 8, 7, 6, 1, 3, 10, 3, 5, 6, 1, 2, 10));
        ArrayList<String> educations = new ArrayList<>(Arrays.asList("Mrs", "Dr", "Mrs", "Mr", "Mrs", "Rev", "Mrs", "BSc", "Mrs", "Mr", "Msc", "Mr", "Dr", "BSc", "Mr", "Mrs", "BSc", "Mrs", "Rev", "BSc", "Mr", "Dr", "Mrs", "Mr", "Mrs", "Mr", "Msc", "BSc", "Mrs", "Mr", "Mrs", "BSc", "Msc", "Dr", "Mrs", "Mr", "Mrs", "Mr", "Mrs", "Mr", "Mrs", "Mr", "Mrs", "Mr", "Mrs", "Mr", "Mrs", "Mr", "Dr", "Mr", "Mrs", "Mr", "Dr", "Dr", "Mrs", "Dr", "Mrs", "Rev", "Mrs", "Msc", "BSc", "Mr", "Msc", "Msc", "Dr", "Mr", "Mrs", "Dr", "BSc", "Dr", "Mrs", "Mr", "Mrs", "Mr", "Dr", "Mr", "Msc", "Mr", "BSc", "Msc", "Msc","Dr", "BSc", "Dr", "Dr", "BSc", "Dr", "BSc", "Msc", "Msc", "BSc", "Dr", "Msc", "Msc", "BSc", "BSc", "BSc", "Msc", "BSc", "Msc"));
        ArrayList<String> professions = new ArrayList<>(Arrays.asList("Teacher", "Teacher","Nurse","Lawyer","Accountant","Engineer","Software developer","Doctor","Construction worker","Police officer","Firefighter","Chef","Artist","Designer","Electrician","Carpenter","Plumber","Mechanic","Clerk","Receptionist","Security guard","Cleaner","Janitor","Housekeeper","IT specialist","Web developer","Data analyst","Marketing manager","Business analyst","Human resources manager","Public relations specialist","Consultant","Financial analyst", "Nurse", "Lawyer", "Accountant", "Engineer", "Software developer", "Doctor", "Construction worker", "Police officer", "Firefighter", "Chef", "Artist", "Designer", "Electrician", "Carpenter", "Plumber", "Mechanic", "Clerk", "Receptionist", "Security guard", "Cleaner", "Janitor", "Housekeeper", "IT specialist", "Web developer", "Data analyst", "Marketing manager", "Business analyst", "Human resources manager", "Public relations specialist", "Consultant", "Financial analyst", "Librarian", "Photographer", "Musician", "Journalist", "Travel agent", "Physical therapist", "Dentist", "Veterinarian", "Pharmacist", "Optometrist", "Chiropractor", "Therapist", "Psychologist", "Social worker", "Counselor", "Statistician", "Actuary", "Biologist", "Chemist", "Geologist", "Astronomer", "Meteorologist", "Zoologist", "Ecologist", "Anthropologist", "Archaeologist", "Geographer", "Historian", "Linguist", "Philosopher", "Political scientist", "Sociologist", "Economist", "Mathematician", "Astrophysicist", "Physicist", "Computer scientist", "Robotics engineer", "Aerospace engineer", "Civil engineer", "Environmental engineer", "Marine engineer", "Nuclear engineer", "Petroleum engineer"));



        List<Document> inhabitants = new ArrayList<>();
        for (int i = 0; i < firstNames.size(); i++) {
            inhabitants.add(new Document("inhabitantId", i + 1)
                    .append("personalInfo", new Document("firstName", firstNames.get(i))
                            .append("lastName", lastNames.get(i))
                            .append("dateOfBirth", dateOfBirths.get(i))
                            .append("gender", genders.get(i)))
                    .append("buildingId", buildingIds.get(i))
                    .append("districtId", districtIds.get(i))
                    .append("education", educations.get(i))
                    .append("profession", professions.get(i)));
        }

        database.getCollection("Inhabitants").insertMany(inhabitants);
    }
}

