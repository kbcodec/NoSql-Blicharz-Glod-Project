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

        List<String> names = new ArrayList<>(Arrays.asList("Old Town", "Downtown", "City Square", "North Bazaar", "East Hills", "New World", "Lower North Brand", "Upper Slirk", "West Limoad", "Feeloshis Grove"));
        List<Integer> areasInKmSquare = new ArrayList<>(Arrays.asList(3, 4, 1, 3, 3, 6, 7, 4, 9, 11));
        List<Integer> districtIds = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        List<Boolean> hasParks = new ArrayList<>(Arrays.asList(true, false, false, false, true, false, true, false, false, true));
        List<Integer> utilityBuildings = new ArrayList<>(Arrays.asList(6, 3, 4, 5, 6, 10, 12, 15, 11, 8));
        List<Integer> residentialBuildings = new ArrayList<>(Arrays.asList(4, 10, 5, 6, 9, 8, 9, 14, 12, 10));
        List<Integer> industrialBuildings = new ArrayList<>(Arrays.asList(4, 3, 5, 8, 10, 12, 8, 9, 14, 11));
        List<Integer> busLines = new ArrayList<>(Arrays.asList(100, 104, 120, 121, 122, 126, 129, 135, 145, 150, 152, 155, 160, 167, 171, 175, 179, 180));
        List<Integer> tramLines = new ArrayList<>(Arrays.asList(12, 13, 15, 16, 20, 22, 26, 29, 30, 32, 37, 39, 45, 50, 54));
        List<Integer> numberOfInhabitants = new ArrayList<>(Arrays.asList(10000, 12000, 2000, 7500, 3500, 2800, 4500, 14000, 18000, 5650));
        List<String> parkNames = Arrays.asList("Main Park", "","","","Central Park","", "Old Park","","", "Valley Park");
        List<Integer> numberOfFountains = Arrays.asList(2,0,0,0, 4,0, 0,0,0, 1);
        List<Integer> numberOfBenches = Arrays.asList(30,0,0,0, 45,0, 25,0,0, 33);
        List<Integer> parkAreaInKmSquare = Arrays.asList(1, 0, 0, 0, 2, 0, 1, 0, 0, 3);
        List<Integer> numberOfEntrances = Arrays.asList(4, 0,0,0,10,0, 6, 0,0,8);

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