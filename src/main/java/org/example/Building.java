package org.example;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.print.Doc;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class Building {
    private int buildingId;
    private String name;
    private String type;
    private String archWallType;
    private String archWallColor;
    private int condignations;
    private boolean hasBalcony;
    private int yearOfBuild;

    public Building(int buildingId, String name, String type, String archWallType, String archWallColor, int condignations, boolean hasBalcony, int yearOfBuild) {
        this.buildingId = buildingId;
        this.name = name;
        this.type = type;
        this.archWallType = archWallType;
        this.archWallColor = archWallColor;
        this.condignations = condignations;
        this.hasBalcony = hasBalcony;
        this.yearOfBuild = yearOfBuild;
    }

    public Building() {
    }

    public FindIterable<Document> getAllBuildings () {
        MongoDBConnection database = new MongoDBConnection();
        MongoCollection<Document> buildingsCollection = database.getCollection("Buildings");

        return buildingsCollection.find();
    }

    public void setName(String name) {
        this.name = name;
    }

    void showData(JTable jTable, DefaultTableModel dtm) {
        dtm.setRowCount(2);
        jTable.setValueAt("Nazwa", 0, 0);
        jTable.setValueAt(name, 0, 1);
        jTable.setValueAt("Bang", 1, 0);
        jTable.setValueAt("Joł", 1, 1);
    }
}


//
//    //COLLECTIONS FROM CTIYDB
//    MongoCollection<Document> buildingsCollection = database.getCollection("Buildings");
//    MongoCollection<Document> inhabitantsCollection = database.getCollection("Inhabitants");
//    MongoCollection<Document> districtsCollection = database.getCollection("Districts");
//
//    //FIND ALL DOCUMENTS FROM COLLECTIONS
//    FindIterable<Document> allBuildings = buildingsCollection.find();
//    FindIterable<Document> allInhabitants = inhabitantsCollection.find();
//    FindIterable<Document> allDistricts = districtsCollection.find();
//
//    //PRINT ALL DOCUMENTS
//    printAllDocuments(allBuildings);
//    printAllDocuments(allInhabitants);
//    printAllDocuments(allDistricts);
