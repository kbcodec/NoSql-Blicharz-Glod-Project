package org.example;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Inhabitants {

    private int inhabitant_id;
    private String firstName;
    private String lastName;
    private int BirthDate;
    private String gender;
    private String education;
    private String profession;
    private String districtID;
    private String buildingID;

    public Inhabitants(int inhabitant_id, String firstName, String lastName, int birthDate, String gender, String education, String profession, String districtID, String buildingID) {
        this.inhabitant_id = inhabitant_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.BirthDate = birthDate;
        this.gender = gender;
        this.education = education;
        this.profession = profession;
        this.districtID = districtID;
        this.buildingID = buildingID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(int birthDate) {
        BirthDate = birthDate;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Inhabitants(int inhabitant_id) {
        this.inhabitant_id = inhabitant_id;
    }

    public Inhabitants() {
    }

    public FindIterable<Document> getAllInhabitants() {
        MongoDBConnection database = new MongoDBConnection();
        MongoCollection<Document> inhabitantsCollection = database.getCollection("Inhabitants");
        return inhabitantsCollection.find();
    }

    void showData(JTable jTable, DefaultTableModel dtm) {
        dtm.setRowCount(2);
        jTable.setValueAt(firstName, 0, 0);
        jTable.setValueAt(firstName, 0, 1);
        jTable.setValueAt(firstName, 1, 0);
        jTable.setValueAt(firstName, 1, 1);
    }
}

