package org.example.collections.building;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
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

public class Buildings {
    private ObjectId id;
    private int buildingId;
    private String name;
    private String type;
    private Architecture architecture;
    private int year;
    
    private static final Map<String, String> headersMap;
    static {
        Map<String, String> map = new HashMap<>();
        map.put("ID", "buildingId");
        map.put("Nazwa", "name");
        map.put("Typ", "type");
        map.put("Rodzaj ścian", "architecture.wallType");
        map.put("Kolor ścian", "architecture.wallColor");
        map.put("Liczba kondygnacji", "architecture.condignations");
        map.put("Czy posiada balkon", "architecture.hasBalcony");
        map.put("Rok budowy", "year");
        headersMap = Collections.unmodifiableMap(map);
    }


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Architecture getArchitecture() {
        return architecture;
    }

    public void setArchitecture(Architecture architecture) {
        this.architecture = architecture;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Buildings() {
        this.architecture = new Architecture();
    }

    public Buildings(ObjectId id, int buildingId, String type, Architecture architecture, int year) {
        this.id = id;
        this.buildingId = buildingId;
        this.type = type;
        this.architecture = architecture;
        this.year = year;
    }

    public static void deleteBuildingFromDatabase(Integer fieldId) {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        MongoDatabase database = MongoDBConnection.connect(pojoCodecRegistry);
        MongoCollection<Buildings> collection = database.getCollection("Buildings", Buildings.class);

        collection.deleteOne(new Document("buildingId", fieldId));
    }

    public List<Buildings> getAllBuildings() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        MongoDatabase database = MongoDBConnection.connect(pojoCodecRegistry);
        MongoCollection<Buildings> collection = database.getCollection("Buildings", Buildings.class);
        FindIterable<Buildings> buildings = collection.find();

        List<Buildings> result = new ArrayList<>();

        for(Buildings building : buildings) {
            result.add(building);
        }
        return result;
    }

    public List<Buildings> getFilteredBuildings(String fieldName, String fieldValue) {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        Document query = new Document();
        Document regQuery = new Document();

        MongoDatabase database = MongoDBConnection.connect(pojoCodecRegistry);
        MongoCollection<Buildings> collection = database.getCollection("Buildings", Buildings.class);

        if(headersMap.get(fieldName).equals("buildingId") || headersMap.get(fieldName).equals("year") || headersMap.get(fieldName).equals("architecture.condignations")) {
            query.append(headersMap.get(fieldName), Integer.valueOf(fieldValue));
        } else if (headersMap.get(fieldName).equals("architecture.hasBalcony")) {
            query.append(headersMap.get(fieldName), Boolean.valueOf(fieldValue));
        } else {
            regQuery.append("$regex", "(?)" + Pattern.quote(fieldValue));
            regQuery.append("$options", "i");

            query.append(headersMap.get(fieldName), regQuery);
        }

        FindIterable<Buildings> buildings = collection.find(query);

        List<Buildings> result = new ArrayList<>();
        for(Buildings building : buildings) {
            result.add(building);
        }
        return result;
    }

    public Buildings createBuildingById(Integer fieldId) {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        MongoDatabase database = MongoDBConnection.connect(pojoCodecRegistry);
        MongoCollection<Buildings> collection = database.getCollection("Buildings", Buildings.class);

        return collection.find(Filters.eq("buildingId", fieldId)).first();
    }

    @Override
    public String toString() {
        return "Buildings{" +
                "id=" + id +
                ", building_id=" + buildingId +
                ", type='" + type + '\'' +
                ", architecture=" + architecture +
                ", year=" + year +
                '}';
    }

    public static Buildings getLastId() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        MongoDatabase database = MongoDBConnection.connect(pojoCodecRegistry);
        MongoCollection<Buildings> collection = database.getCollection("Buildings", Buildings.class);

        return collection.find().sort(new Document("buildingId", -1)).first();
    }

    public static void addBuilding(Buildings building) {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        MongoDatabase database = MongoDBConnection.connect(pojoCodecRegistry);
        MongoCollection<Document> collectionDocument = database.getCollection("Buildings");

        Document newBuilding = new Document();
        Document architecture = new Document();
        architecture.append("condignations", building.getArchitecture().getCondignations())
                .append("hasBalcony", building.getArchitecture().getHasBalcony())
                .append("wallColor", building.getArchitecture().getWallColor())
                .append("wallType", building.getArchitecture().getWallType());
        newBuilding.append("buildingId", building.getBuildingId())
                .append("name", building.getName())
                .append("architecture", architecture)
                .append("type", building.getType())
                .append("year", building.getYear());

        collectionDocument.insertOne(newBuilding);
    }

    public void modifyBuildingInDatabase() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        MongoDatabase database = MongoDBConnection.connect(pojoCodecRegistry);
        MongoCollection<Buildings> collection = database.getCollection("Buildings", Buildings.class);

        Document filter = new Document();
        filter.append("buildingId", this.getBuildingId());
        Document updater = new Document();
        Document setter = new Document();
        setter.append("name", this.getName());
        setter.append("type", this.getType());
        setter.append("year", this.getYear());
        setter.append("architecture.condignations", this.getArchitecture().getCondignations());
        setter.append("architecture.hasBalcony", this.getArchitecture().getHasBalcony());
        setter.append("architecture.wallType", this.getArchitecture().getWallType());
        setter.append("architecture.wallColor", this.getArchitecture().getWallColor());
        updater.append("$set", setter);

        collection.updateOne(filter, updater);
    }

    public static Buildings getBuildingForInhabitant(Inhabitants inhabitant) {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        MongoDatabase database = MongoDBConnection.connect(pojoCodecRegistry);
        MongoCollection<Buildings> collection = database.getCollection("Buildings", Buildings.class);

        List<Document> aggregateList = new ArrayList<>();
        Document lookup = new Document();
        lookup.append("from", "Inhabitants")
            .append("localField", "buildingId")
            .append("foreignField", "buildingId")
            .append("as", "inhabitants_info");
        Document match = new Document();
        match.append("$match", new Document("inhabitants_info.inhabitantId", inhabitant.getInhabitantId()));
        aggregateList.add(new Document("$lookup", lookup));
        aggregateList.add(match);

        return collection.aggregate(aggregateList).first();
    }
}
