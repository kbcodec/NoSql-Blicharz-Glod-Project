package org.example.collections.district;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;
import org.example.collections.building.Buildings;
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
        this.numberOfBuildings = new NumberOfBuildings();
        this.parkInfo = new ParkInfo();
        this.publicTransport = new PublicTransport();
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

    public static void deleteDistrictFromDatabase(Integer fieldId) {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        MongoDatabase database = MongoDBConnection.connect(pojoCodecRegistry);
        MongoCollection<Districts> collection = database.getCollection("Districts", Districts.class);

        collection.deleteOne(new Document("districtId", fieldId));
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

    public Districts createDistrictById(Integer fieldId) {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        MongoDatabase database = MongoDBConnection.connect(pojoCodecRegistry);
        MongoCollection<Districts> collection = database.getCollection("Districts", Districts.class);

        return collection.find(Filters.eq("districtId", fieldId)).first();
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

    public static Districts getLastId() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        MongoDatabase database = MongoDBConnection.connect(pojoCodecRegistry);
        MongoCollection<Districts> collection = database.getCollection("Districts", Districts.class);

        return collection.find().sort(new Document("districtId", -1)).first();
    }

    public static void addDistrict(Districts district) {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        MongoDatabase database = MongoDBConnection.connect(pojoCodecRegistry);
        MongoCollection<Document> collectionDocument = database.getCollection("Districts");

        Document newDistrict = new Document();
        newDistrict.append("name", district.getName())
                .append("areaInKmSquare", district.getAreaInKmSquare())
                .append("districtId", district.getDistrictId())
                .append("hasPark", district.getHasPark());

        if(district.getHasPark()) {
            Document parkInfo = new Document();
            parkInfo.append("parkName", district.getParkInfo().getParkName())
                    .append("numberOfFountains", district.getParkInfo().getNumberOfFountains())
                    .append("numberOfBenches", district.getParkInfo().getNumberOFBenches())
                    .append("areaInKmSquare", district.getParkInfo().getAreaInKmSquare())
                    .append("numberOfEntrances", district.getParkInfo().getNumberOfEntrances());

            newDistrict.append("parkInfo", parkInfo);
        }

        Document numberOfBuildings = new Document();
        numberOfBuildings.append("utilityBuildings", district.getNumberOfBuildings().getUtilityBuildings())
                .append("residentialBuildings", district.getNumberOfBuildings().getResidentialBuildings())
                .append("industrialBuildings", district.getNumberOfBuildings().getIndustrialBuildings());
        newDistrict.append("numberOfBuildings", numberOfBuildings);

        Document publicTransport = new Document();
        publicTransport.append("busLines", district.getPublicTransport().getBusLines())
                .append("tramLines", district.getPublicTransport().getTramLines());

        newDistrict.append("publicTransport", publicTransport)
                .append("numberOfInhabitants", district.getNumberOfInhabitants());

        collectionDocument.insertOne(newDistrict);
    }

    public void modifyDistrictInDatabase() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        MongoDatabase database = MongoDBConnection.connect(pojoCodecRegistry);
        MongoCollection<Districts> collection = database.getCollection("Districts", Districts.class);

        Document filter = new Document();
        filter.append("districtId", this.getDistrictId());
        Document updater = new Document();
        Document setter = new Document();
        Document unsetter = new Document();
        setter.append("name", this.getName());
        setter.append("areaInKmSquare", this.getAreaInKmSquare());
        setter.append("numberOfBuildings.utilityBuildings", this.getNumberOfBuildings().getUtilityBuildings());
        setter.append("numberOfBuildings.residentialBuildings", this.getNumberOfBuildings().getResidentialBuildings());
        setter.append("numberOfBuildings.industrialBuildings", this.getNumberOfBuildings().getIndustrialBuildings());
        setter.append("hasPark", this.getHasPark());
        if(this.getHasPark()) {
            setter.append("parkInfo.parkName", this.getParkInfo().getParkName());
            setter.append("parkInfo.numberOfFountains", this.getParkInfo().getNumberOfFountains());
            setter.append("parkInfo.numberOfBenches", this.getParkInfo().getNumberOFBenches());
            setter.append("parkInfo.numberOfEntrances", this.getParkInfo().getNumberOfEntrances());
            setter.append("parkInfo.areaInKmSquare", this.getParkInfo().getAreaInKmSquare());
            setter.append("districtId", this.getDistrictId());
            setter.append("numberOfInhabitants", this.getNumberOfInhabitants());
            updater.append("$set", setter);

        } else {
            unsetter.append("parkInfo.parkName", "");
            unsetter.append("parkInfo.numberOfFountains", "");
            unsetter.append("parkInfo.numberOfBenches", "");
            unsetter.append("parkInfo.numberOfEntrances", "");
            unsetter.append("parkInfo.areaInKmSquare", "");
            setter.append("districtId", this.getDistrictId());
            setter.append("numberOfInhabitants", this.getNumberOfInhabitants());
            updater.append("$set", setter);
            updater.append("$unset", unsetter);
        }

        collection.updateOne(filter, updater);

    }
}


//db.buildings.aggregate([
//        {
//        $lookup:
//        {
//        from: "inhabitants",
//        localField: "buildingId",
//        foreignField: "buildingId",
//        as: "inhabitants_info"
//        }
//        },
//        {
//        $match: { "inhabitants_info.inhabitantId": 1 }
//        }
//        ])