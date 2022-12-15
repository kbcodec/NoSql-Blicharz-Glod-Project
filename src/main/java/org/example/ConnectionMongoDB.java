package org.example;

import com.mongodb.client.*;
import org.bson.Document;

public class ConnectionMongoDB {
    public static void main( String[] args ) {

        String uri = "mongodb+srv://cityadmin:adminpass123@citycluster.xbdfcyz.mongodb.net/?retryWrites=true&w=majority";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("cityProjectDB");
            MongoCollection<Document> collection = database.getCollection("testCollection");

            FindIterable<Document> iterDoc = collection.find();

            for (Document document : iterDoc) {
                System.out.println(document.toJson());
            }
        }
    }
}