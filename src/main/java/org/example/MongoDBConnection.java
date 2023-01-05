package org.example;

import com.mongodb.client.*;
import org.bson.Document;

import javax.print.Doc;

import static com.mongodb.client.model.Filters.all;
import static com.mongodb.client.model.Filters.eq;

public class MongoDBConnection {
    private static final String uri = "mongodb+srv://cityAdmin:admin@citycluster.jfqpex3.mongodb.net/test";

    public MongoDBConnection() {
    }

    private MongoDatabase connect() {
        MongoClient mongoClient = MongoClients.create(uri);
        return mongoClient.getDatabase("CityDB");

    }

    public MongoCollection<Document> getCollection(String collection) {
        MongoDBConnection db = new MongoDBConnection();
        MongoDatabase dbConnection = db.connect();
        return dbConnection.getCollection(collection);

    }

}