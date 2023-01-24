package org.example.collections.district;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;
import org.example.collections.inhabitant.Inhabitants;
import org.example.database.MongoDBConnection;

import java.util.*;
import java.util.regex.Pattern;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class Districts {
    private ObjectId id;
    private int districtId;
    private String name;
    private int areaInKmSquare;
    private NumberOfBuildings numberOfBuildings;
    private int numberOfInhabitants;
    private Boolean hasPark;
    private ParkInfo parkInfo;
    private PublicTransport publicTransport;

    private static final Map<String, String> headersMap;
    static {
        Map<String, String> map = new HashMap<>();
        map.put("ID", "districtId");
        map.put("Nazwa", "name");
        map.put("Powierzchnia w km2", "areaInKmSquare");
        map.put("Budynki użytkowe", "numberOfBuildings.utilityBuildings");
        map.put("Budynki mieszkalne", "numberOfBuildings.residentialBuildings");
        map.put("Budynki przemysłowe", "numberOfBuildings.industrialBuildings");
        map.put("Nazwa Parku", "parkInfo.parkName");
        map.put("Liczba ławek", "parkInfo.numberOfBenches");
        map.put("Liczba fontann", "parkInfo.numberOfFountains");
        map.put("Powierzchnia parku w km2", "parkInfo.areaInKmSquare");
        map.put("Liczba wejść", "parkInfo.numberOfEntrances");
        map.put("Linie autobusowe", "publicTransport.busLines");
        map.put("Linie tramwajowe", "publicTransport.tramLines");
        map.put("Liczba mieszkańców", "numberOfInhabitants");
        headersMap = Collections.unmodifiableMap(map);
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAreaInKmSquare() {
        return areaInKmSquare;
    }

    public void setAreaInKmSquare(int areaInKmSquare) {
        this.areaInKmSquare = areaInKmSquare;
    }

    public NumberOfBuildings getNumberOfBuildings() {
        return numberOfBuildings;
    }

    public void setNumberOfBuildings(NumberOfBuildings numberOfBuildings) {
        this.numberOfBuildings = numberOfBuildings;
    }

    public int getNumberOfInhabitants() {
        return numberOfInhabitants;
    }

    public void setNumberOfInhabitants(int numberOfInhabitants) {
        this.numberOfInhabitants = numberOfInhabitants;
    }

    public Boolean getHasPark() {
        return hasPark;
    }

    public void setHasPark(Boolean hasPark) {
        this.hasPark = hasPark;
    }

    public ParkInfo getParkInfo() {
        return parkInfo;
    }

    public void setParkInfo(ParkInfo parkInfo) {
        this.parkInfo = parkInfo;
    }

    public PublicTransport getPublicTransport() {
        return publicTransport;
    }

    public void setPublicTransport(PublicTransport publicTransport) {
        this.publicTransport = publicTransport;
    }


    public Districts() {
    }

    public Districts(ObjectId id, int districtId, String name, int areaInKmSquare, NumberOfBuildings numberOfBuildings, int numberOfInhabitants, Boolean hasPark, ParkInfo parkInfo, PublicTransport publicTransport) {
        this.id = id;
        this.districtId = districtId;
        this.name = name;
        this.areaInKmSquare = areaInKmSquare;
        this.numberOfBuildings = numberOfBuildings;
        this.numberOfInhabitants = numberOfInhabitants;
        this.hasPark = hasPark;
        this.parkInfo = parkInfo;
        this.publicTransport = publicTransport;
    }

    public List<Districts> getAllDistricts() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        MongoDatabase database = MongoDBConnection.connect(pojoCodecRegistry);
        MongoCollection<Districts> collection = database.getCollection("Districts", Districts.class);
        FindIterable<Districts> districts = collection.find();

        List<Districts> result = new ArrayList<>();

        for(Districts district : districts) {
            result.add(district);
        }
        return result;
    }

    public List<Districts> getFilteredDistricts(String fieldName, String fieldValue) {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        Document query = new Document();
        Document regQuery = new Document();

        MongoDatabase database = MongoDBConnection.connect(pojoCodecRegistry);
        MongoCollection<Districts> collection = database.getCollection("Districts", Districts.class);

        if(headersMap.get(fieldName).equals("districtId")
                || headersMap.get(fieldName).equals("areaInKmSquare")
                || headersMap.get(fieldName).equals("numberOfBuildings.utilityBuildings")
                || headersMap.get(fieldName).equals("numberOfBuildings.residentialBuildings")
                || headersMap.get(fieldName).equals("numberOfBuildings.industrialBuildings")
                || headersMap.get(fieldName).equals("parkInfo.numberOfBenches")
                || headersMap.get(fieldName).equals("parkInfo.numberOfFountains")
                || headersMap.get(fieldName).equals("parkInfo.areaInKmSquare")
                || headersMap.get(fieldName).equals("parkInfo.numberOfEntrances")
                || headersMap.get(fieldName).equals("numberOfInhabitants")) {
            query.append(headersMap.get(fieldName), Integer.valueOf(fieldValue));
        } else if (headersMap.get(fieldName).equals("publicTransport.busLines") || headersMap.get(fieldName).equals("publicTransport.tramLines")) {
            List<Integer> values = new ArrayList<>();
            values.add(Integer.valueOf(fieldValue));
            regQuery.append("$in", values);
            query.append(headersMap.get(fieldName), regQuery);
        } else {
            regQuery.append("$regex", "(?)" + Pattern.quote(fieldValue));
            regQuery.append("$options", "i");

            query.append(headersMap.get(fieldName), regQuery);
        }

        FindIterable<Districts> districts = collection.find(query);

        List<Districts> result = new ArrayList<>();
        for(Districts district : districts) {
            result.add(district);
        }
        return result;
    }

    @Override
    public String toString() {
        return "Districts{" +
                "id=" + id +
                ", districtId=" + districtId +
                ", name='" + name + '\'' +
                ", areaInKmSquare=" + areaInKmSquare +
                ", numberOfBuildings=" + numberOfBuildings +
                ", numberOfInhabitants=" + numberOfInhabitants +
                ", hasPark=" + hasPark +
                ", parkInfo=" + parkInfo +
                ", publicTransport=" + publicTransport +
                '}';
    }
}
