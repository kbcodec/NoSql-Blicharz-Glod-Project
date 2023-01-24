package org.example.database;

import com.mongodb.client.*;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;

import javax.print.Doc;

import static com.mongodb.client.model.Filters.all;
import static com.mongodb.client.model.Filters.eq;

public class MongoDBConnection {
    private static final String uri = "mongodb+srv://cityAdmin:admin@citycluster.jfqpex3.mongodb.net/test";

    public MongoDBConnection() {
    }

    public static MongoDatabase connect(CodecRegistry pojoCodecRegistry) {
        MongoClient mongoClient = MongoClients.create(uri);
        return mongoClient.getDatabase("CityDB").withCodecRegistry(pojoCodecRegistry);
    }

}