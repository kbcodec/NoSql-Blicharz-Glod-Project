package org.example.collections.district;

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

public class DistrictGenerator {

    static public void Generator() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        MongoDatabase database = MongoDBConnection.connect(pojoCodecRegistry);

        //MongoClient mongoClient = MongoClients.create();
        //MongoDatabase database = mongoClient.getDatabase("CityDB");
        MongoCollection<Document> collection = database.getCollection("Districts");

        List<String> names = new ArrayList<>(Arrays.asList("District1", "District2", "District3", "District4", "District5"));
        List<Double> areasInKmSquare = new ArrayList<>(Arrays.asList(100.0, 200.0, 150.0, 250.0, 300.0));
        List<Integer> districtIds = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Boolean> hasParks = new ArrayList<>(Arrays.asList(true, false, true, false, true));
        List<Integer> utilityBuildings = new ArrayList<>(Arrays.asList(2, 3, 4, 5, 6));
        List<Integer> residentialBuildings = new ArrayList<>(Arrays.asList(4, 5, 6, 7, 8));
        List<Integer> industrialBuildings = new ArrayList<>(Arrays.asList(4, 3, 5, 8, 10));
        List<Integer> busLines = new ArrayList<>(Arrays.asList(100, 104, 120, 135, 145, 150, 152));
        List<Integer> tramLines = new ArrayList<>(Arrays.asList(12, 15, 16, 20, 22, 50, 54));
        List<Integer> numberOfInhabitants = new ArrayList<>(Arrays.asList(1000, 2000, 1500, 2500, 3000));
        List<String> parkNames = Arrays.asList("Park 1", "Park 2", "Park 3", "Park 4", "Park 5");
        List<Integer> numberOfFountains = Arrays.asList(5, 10, 15, 20, 25);
        List<Integer> numberOfBenches = Arrays.asList(10, 20, 30, 40, 50);
        List<Double> parkAreaInKmSquare = Arrays.asList(1.5, 2.0, 2.5, 3.0, 3.5);
        List<Integer> numberOfEntrances = Arrays.asList(2, 4, 6, 8, 10);

        List<Document> districts = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            busLines = new ArrayList<>();
            tramLines = new ArrayList<>();
            for (int j = (i * 2) + 1; j <= (i * 2) + 2; j++) {
                busLines.add(j);
                tramLines.add(j * 2 + 2);
            }

            Document district = new Document();
            district.append("name", names.get(i))
                    .append("areaInKmSquare", areasInKmSquare.get(i))
                    .append("districtId", districtIds.get(i))
                    .append("hasPark", hasParks.get(i));

            if(hasParks.get(i)) {
                Document parkInfo = new Document();
                parkInfo.append("parkName", parkNames.get(i))
                        .append("numberOfFountains", numberOfFountains.get(i))
                        .append("numberOfBenches", numberOfBenches.get(i))
                        .append("areaInKmSquare", parkAreaInKmSquare.get(i))
                        .append("numberOfEntrances", numberOfEntrances.get(i));

                district.append("parkInfo", parkInfo);
            }

            Document numberOfBuildings = new Document();
            numberOfBuildings.append("utilityBuildings", utilityBuildings.get(i))
                    .append("residentialBuildings", residentialBuildings.get(i))
                    .append("industrialBuildings", industrialBuildings.get(i));
            district.append("numberOfBuildings", numberOfBuildings);

            Document publicTransport = new Document();
            publicTransport.append("busLines", busLines)
                    .append("tramLines", tramLines);

            district.append("publicTransport", publicTransport)
                    .append("numberOfInhabitants", numberOfInhabitants.get(i));

            districts.add(district);


        }

        database.getCollection("Districts").insertMany(districts);
    }
}