package org.example.collections.inhabitant;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;
import org.example.database.MongoDBConnection;

import javax.print.Doc;
import java.util.*;
import java.util.regex.Pattern;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class Inhabitants {
    private ObjectId id;
    private int inhabitantId;
    private PersonalInfo personalInfo;
    private String education;
    private String profession;
    private int districtId;
    private int buildingId;

    private static final Map<String, String> headersMap;
    static {
        Map<String, String> map = new HashMap<>();
        map.put("ID", "inhabitantId");
        map.put("Imię", "personalInfo.firstName");
        map.put("Nazwisko", "personalInfo.lastName");
        map.put("Rok urodzenia", "personalInfo.dateOfBirth");
        map.put("Płeć", "personalInfo.gender");
        map.put("Tytuł", "education");
        map.put("Profesja", "profession");
        headersMap = Collections.unmodifiableMap(map);
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public int getInhabitantId() {
        return inhabitantId;
    }

    public void setInhabitantId(int inhabitantId) {
        this.inhabitantId = inhabitantId;
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    public Inhabitants() {
        this.personalInfo = new PersonalInfo();
    }

    public Inhabitants(ObjectId id, int inhabitantId, PersonalInfo personalInfo, String education, String profession, int districtId, int buildingId) {
        this.id = id;
        this.inhabitantId = inhabitantId;
        this.personalInfo = personalInfo;
        this.education = education;
        this.profession = profession;
        this.districtId = districtId;
        this.buildingId = buildingId;
    }

    public List<Inhabitants> getAllInhabitants() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        MongoDatabase database = MongoDBConnection.connect(pojoCodecRegistry);
        MongoCollection<Inhabitants> collection = database.getCollection("Inhabitants", Inhabitants.class);
        FindIterable<Inhabitants> inhabitants = collection.find();

        List<Inhabitants> result = new ArrayList<>();

        for(Inhabitants inhabitant : inhabitants) {
            result.add(inhabitant);
        }
        return result;
    }

    public List<Inhabitants> getFilteredInhabitants(String fieldName, String fieldValue) {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        Document query = new Document();
        Document regQuery = new Document();

        MongoDatabase database = MongoDBConnection.connect(pojoCodecRegistry);
        MongoCollection<Inhabitants> collection = database.getCollection("Inhabitants", Inhabitants.class);

        if(headersMap.get(fieldName).equals("inhabitantId") || headersMap.get(fieldName).equals("personalInfo.dateOfBirth")) {
            query.append(headersMap.get(fieldName), Integer.valueOf(fieldValue));
        } else {
            regQuery.append("$regex", "(?)" + Pattern.quote(fieldValue));
            regQuery.append("$options", "i");

            query.append(headersMap.get(fieldName), regQuery);
        }

        FindIterable<Inhabitants> inhabitants = collection.find(query);

        List<Inhabitants> result = new ArrayList<>();
        for(Inhabitants inhabitant : inhabitants) {
            result.add(inhabitant);
        }
        return result;
    }

    public Inhabitants createInhabitantById(Integer fieldId) {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        MongoDatabase database = MongoDBConnection.connect(pojoCodecRegistry);
        MongoCollection<Inhabitants> collection = database.getCollection("Inhabitants", Inhabitants.class);

        return collection.find(Filters.eq("inhabitantId", fieldId)).first();
    }

    public void modifyInhabitantInDatabase() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        MongoDatabase database = MongoDBConnection.connect(pojoCodecRegistry);
        MongoCollection<Inhabitants> collection = database.getCollection("Inhabitants", Inhabitants.class);

        Document filter = new Document();
        filter.append("inhabitantId", this.getInhabitantId());
        Document updater = new Document();
        Document setter = new Document();
        setter.append("personalInfo.firstName", this.getPersonalInfo().getFirstName());
        setter.append("personalInfo.lastName", this.getPersonalInfo().getLastName());
        setter.append("personalInfo.dateOfBirth", this.getPersonalInfo().getDateOfBirth());
        setter.append("personalInfo.gender", this.getPersonalInfo().getGender());
        setter.append("education", this.getEducation());
        setter.append("profession", this.getProfession());
        setter.append("districtId", this.getDistrictId());
        setter.append("buildingId", this.getBuildingId());
        updater.append("$set", setter);

        collection.updateOne(filter, updater);

    }

    public static void deleteInhabitantFromDatabase(Integer fieldId) {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        MongoDatabase database = MongoDBConnection.connect(pojoCodecRegistry);
        MongoCollection<Inhabitants> collection = database.getCollection("Inhabitants", Inhabitants.class);

        collection.deleteOne(new Document("inhabitantId", fieldId));
    }

    public static Inhabitants getLastId() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        MongoDatabase database = MongoDBConnection.connect(pojoCodecRegistry);
        MongoCollection<Inhabitants> collection = database.getCollection("Inhabitants", Inhabitants.class);

        return collection.find().sort(new Document("inhabitantId", -1)).first();
    }

    public static void addInhabitant(Inhabitants inhabitant) {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        MongoDatabase database = MongoDBConnection.connect(pojoCodecRegistry);
        MongoCollection<Document> collectionDocument = database.getCollection("Inhabitants");

        Document newInhabitant = new Document();
        Document personalInfo = new Document();
        personalInfo.append("firstName", inhabitant.getPersonalInfo().getFirstName())
                .append("lastName", inhabitant.getPersonalInfo().getLastName())
                .append("gender", inhabitant.getPersonalInfo().getGender())
                .append("dateOfBirth", inhabitant.getPersonalInfo().getDateOfBirth());
        newInhabitant.append("inhabitantId", inhabitant.getInhabitantId())
                .append("personalInfo", personalInfo)
                .append("buildingId", inhabitant.getBuildingId())
                .append("districtId", inhabitant.getDistrictId())
                .append("education", inhabitant.getEducation())
                .append("profession", inhabitant.getProfession());

        collectionDocument.insertOne(newInhabitant);
    }

    public Inhabitants getInhabitantByName(String firstName, String lastName) {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        MongoDatabase database = MongoDBConnection.connect(pojoCodecRegistry);
        MongoCollection<Inhabitants> collection = database.getCollection("Inhabitants", Inhabitants.class);

        return collection.find(new Document().append("personalInfo.firstName", firstName).append("personalInfo.lastName", lastName)).first();

    }

    @Override
    public String toString() {
        return "Inhabitatns{" +
                "id=" + id +
                ", inhabitantId=" + inhabitantId +
                ", personalInfo=" + personalInfo +
                ", education='" + education + '\'' +
                ", profession='" + profession + '\'' +
                ", districtId=" + districtId +
                ", buildingId=" + buildingId +
                '}';
    }
}
