package org.example.collections.building;

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

public class BuildingGenerator {

    static public void Generator() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        MongoDatabase database = MongoDBConnection.connect(pojoCodecRegistry);

        //MongoClient mongoClient = MongoClients.create();
        //MongoDatabase database = mongoClient.getDatabase("CityDB");
        MongoCollection<Document> collection = database.getCollection("Inhabitants");

        List<String> names = Arrays.asList("Building 1", "Building 2", "Building 3", "Building 4", "Building 5");
        List<String> types = Arrays.asList("Residential", "Office", "Industrial", "Public", "Mixed");
        List<Integer> condignations = Arrays.asList(3,2,4,5,2);
        List<Boolean> hasBalcony = Arrays.asList(true, true, false, true, false);
        List<String> wallColors = Arrays.asList("White", "Beige", "Gray", "Brown", "Green");
        List<String> wallTypes = Arrays.asList("Brick", "Concrete", "Wooden", "Glass", "Metal");
        List<Integer> years = Arrays.asList(2010, 2015, 2020, 2025, 2030);
        List<Integer> buildingIds = Arrays.asList(1, 2, 3, 4, 5);


        List<Document> buildings = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            buildings.add(new Document("name", names.get(i))
                    .append("type", types.get(i))
                    .append("architecture", new Document("condignations", condignations.get(i))
                            .append("hasBalcony", hasBalcony.get(i))
                            .append("wallColor", wallColors.get(i))
                            .append("wallType", wallTypes.get(i)))
                    .append("year", years.get(i))
                    .append("buildingId", buildingIds.get(i)));
        }


        database.getCollection("Buildings").insertMany(buildings);
    }
}

