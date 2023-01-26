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

        List<String> names = Arrays.asList("Main Street 22", "Park Avenue 12", "Elm Street 79", "Oak Street 31", "Pine Street 14", "Cedar Boulevard 58", "Sunset Drive 91", "Ocean Avenue 108", "Mountain Road 1", "Valley Boulevard 8", "Hillside Drive 39", "Riverside Avenue 01", "Forest Street 50", "Desert Boulevard 69", "Prairie Avenue 08", "Lakeview Drive 87", "Sunrise Boulevard 16", "Sunset Street 25", "Parkview Avenue 110", "Pineview Drive 103", "Cedarwood Boulevard 12", "Oakwood Avenue 11", "Elmwood Street 170", "Maplewood Boulevard 19", "Birchwood Avenue 8", "Cherrywood Street 17", "Oakwood Drive 196");
        List<String> types = Arrays.asList("Residential", "Office", "Public", "Industrial", "Mixed", "Office", "Public", "Residential", "Industrial", "Mixed", "Residential", "Public", "Office", "Industrial", "Residential", "Public", "Mixed", "Residential", "Office", "Industrial", "Office", "Public", "Residential", "Mixed", "Office", "Public", "Residential", "Industrial", "Mixed");
        List<Integer> condignations = Arrays.asList(4, 2, 5, 2, 4, 1, 5, 2, 4, 1, 5, 2, 3, 4, 5, 1, 3, 2, 5, 3, 1, 2, 4, 5, 1, 3, 5, 2, 4, 1, 5);
        List<Boolean> hasBalcony = Arrays.asList(true, true, false, true, false, true, true, true, false, true, false, true, false, true, false, true, false, false, true, true, true, true, true, true, false, true, false, true, false, false);
        List<String> wallColors = Arrays.asList("White", "Beige", "Gray", "Brown", "Green", "Yellow", "Gray", "Brown", "Green", "Yellow", "Gray", "Brown", "Green", "Yellow", "Gray", "Brown", "Green", "Yellow", "Gray", "Red", "Green", "Orange", "Gray", "Brown", "Green", "Yellow", "Gray", "Brown", "Green", "Yellow");
        List<String> wallTypes = Arrays.asList("Brick", "Concrete", "Wooden", "Glass", "Metal", "Glass", "Metal", "Metal", "Metal", "Metal", "Metal", "Metal", "Metal", "Brick", "Metal", "Brick", "Wooden", "Wooden", "Metal", "Brick", "Metal", "Brick", "Brick", "Metal", "Brick", "Metal", "Brick", "Wooden", "Glass", "Glass");
        List<Integer> years = Arrays.asList(2012, 2010, 2008, 1999, 2009, 1991, 2000, 2002, 1986, 1985, 1995, 2000, 1998, 1993, 2011, 1990, 1964, 2009, 1996, 2010, 2005, 2007, 1999, 2004, 2009, 1988, 1996, 2005, 2002, 2005);
        List<Integer> buildingIds = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30);

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

