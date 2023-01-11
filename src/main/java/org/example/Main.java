package org.example;

import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import com.mongodb.internal.operation.EstimatedDocumentCountOperation;
import org.bson.Document;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
       /* Building building = new Building();
        List<String> embeddedList = new ArrayList<>();
        embeddedList.add("architecture");
        FindIterable<Document> allBuildings = building.getAllBuildings();
        for (Document doc :
                allBuildings) {
            System.out.println(doc.toJson());
            System.out.println(doc.getEmbedded(embeddedList, Document.class).get("wall_type"));*/

        District dzielnica1 = new District();
        List<String> embeddedList = new ArrayList<>();
        embeddedList.add("number_of_buildings");
        FindIterable<Document> allDistricts = dzielnica1.getAllDistricts();
        for (Document doc :
                allDistricts) {
            System.out.println(doc.toJson());
            System.out.println(doc.getEmbedded(embeddedList, Document.class).get("utility_buildings"));
        }

        new MainPanel("PLAN MIASTA");

    }
}