package org.example;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class BuildingHandler {
    public List<Building> getAllBuildings() {
        List<Building> result = new ArrayList<>();


        MongoDBConnection database = new MongoDBConnection();
        MongoCollection<Document> buildingsCollection = database.getCollection("Buildings");
        FindIterable<Document> buildingsFromCollection = buildingsCollection.find();

        for (Document doc :
                buildingsFromCollection) {
            Building building = new Building(doc);
            result.add(building);
        }
        return result;
    }

    public List<Building> getBuildingsByName(String fieldName, String name) {
        List<Building> result = new ArrayList<>();
        Document regQuery = new Document();
        Document query = new Document();

        MongoDBConnection database = new MongoDBConnection();
        MongoCollection<Document> buildingsCollection = database.getCollection("Buildings");

        if(fieldName.equals("building_id") || fieldName.equals("year")) {
            query.append(fieldName, Integer.valueOf(name));
        } else if (fieldName.equals("architecture.has_balcony")) {
            query.append(fieldName, Boolean.valueOf(name));
        } else {
            regQuery.append("$regex", "(?)" + Pattern.quote(name));
            regQuery.append("$options", "i");

            query.append(fieldName, regQuery);
        }

        FindIterable<Document> buildingsFromCollection = buildingsCollection.find(query);

        for (Document doc :
                buildingsFromCollection) {
            Building building = new Building(doc);
            result.add(building);
        }
        return result;
    }


}
